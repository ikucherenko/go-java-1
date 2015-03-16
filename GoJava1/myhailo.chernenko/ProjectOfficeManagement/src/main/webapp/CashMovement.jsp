<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="ua.com.goit.gojava.POM.dataModel.common.*,ua.com.goit.gojava.POM.dataModel.cash.*,
			ua.com.goit.gojava.POM.services.*,
			java.text.SimpleDateFormat,
			java.util.Currency"
						
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script type="text/javascript">
	
	var bankAccountCurrencies = {};
	<% 
		BankAccountService bankAccountService = (BankAccountService) ApplicationContextProvider.getApplicationContext().getBean("BankAccountService");
		for(BankAccount bankAccount: (bankAccountService).retrieveAll() ) {
			out.println("bankAccountCurrencies['"+bankAccount.getId()
			                +"'] = '"+bankAccount.getCurrency().getCurrencyCode()+"';") ;
		}
	%>
	
	function validateData(obj){
		
		if ((obj.name == "AddNew")||(obj.name == "Edit") ) {
			
			var date = this.CashMovementTable.date.value;
			var isDate = /[0-9]{4,4}-[0-9]{1,2}-[0-9]{1,2}/;
		    if (date == null || date == "" || !isDate.test(date) ) {
		        alert("You must fill Date field by the template 'yyyy-mm-dd'!");
		        return false;
		    }
			
		    var rate = this.CashMovementTable.sum.value;
			//var isSum = /[0-9]{0,10}[.]{0,1}[0-9]{0,2}/;
		    if (isNaN(rate)) {
		        alert("You must fill Sum field!");
		        return false;
		    }
		    
		}
	}
	
	function setCurrency(){
		
		var bankAccountId = this.CashMovementTable.bankAccount.value;
		this.CashMovementTable.currency.value = bankAccountCurrencies[bankAccountId];	
		
	}
	
	</script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="design.css">
		<title>Project Office Management System</title>
	</head>
	<body>
		<% pageContext.setAttribute("bankAccountService", ApplicationContextProvider.getApplicationContext().getBean("BankAccountService")); %>
		<% pageContext.setAttribute("cashMovementService", ApplicationContextProvider.getApplicationContext().getBean("CashMovementService")); %>
		
		
		<div class="pageHeader">Записи о движении денег</div>
		
		<form name="CashMovementFilter"
	    	action="CashMovementWebController"
	    	method=POST>
	    
			<div class="filter">
				фильтр по банковскому счету: 
				<select name="bankAccountFilter" onchange="this.form.submit()" >
			  		<%
			  			
			  			Object baFilter = (BankAccount) request.getSession(false).getAttribute("bankAccountFilterValue");
			  			out.println("<option "+( (baFilter == null)? "selected":"" )+" value=\"\" > Отображать все </option>");		
					
			  			for(BankAccount bankAccount: bankAccountService.retrieveAll()) {
							out.println("<option "
										+  ((bankAccount.equals(baFilter))? "selected":"")
										+" value =\""+bankAccount.getId() +"\">"
										+  bankAccount.getName()+"</option>");
						};
					%>
				</select>
			</div>
		</form>
		
		<form name="CashMovementTable"
	    	action="CashMovementWebController"
	    	method=POST>
	    	
	    	<c:if test="${errorMessage != null}">
				<table class = "errorTable">
	    			<tr>
	    				<td>${errorMessage}</td>
	    				<c:set var="errorMessage" scope="session" value = "${null}" />
	    			</tr>
	    		</table>
	    	</c:if>

			<table class = "table">
	    		<tr class="tableHeader">
	    			<th>Идентификатор</th>
	    			<th>Дата</th>
	    			<th>Банковский счет</th>
	    			<th>Валюта</th>
	    			<th>Сумма</th>
	    		</tr>
	    		<c:if test="${pageScope.cashMovementService != null}" >
		   			<c:choose>
		   				<c:when test="${sessionScope.bankAccountFilterValue != null}" >
			    			<c:set var="cashMovementEntries" scope="page" value = "${cashMovementService.retrieveAll(bankAccountFilterValue)}" />
			    		</c:when>
			    		<c:otherwise>
				   			<c:set var="cashMovementEntries" scope="page" value = "${cashMovementService.retrieveAll()}" />
			    		</c:otherwise>
		    		</c:choose>
		   			<c:forEach var="currentEntry" items="${cashMovementEntries}">
		   				<tr class="tableRow">
		   					<td>${currentEntry.getId()}</td>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${currentEntry.getDate()}" />
							</td>
							<td>${currentEntry.getBankAccount().getName()}</td>
							<td>${currentEntry.getCurrency().getCurrencyCode()}</td>
							<td class="numericColumn">${currentEntry.getSum().getValue()}</td>
							<td>
								<button class = "defaultButton" type="submit" name="DellCurrent" 
										value="${currentEntry.getId()}">Удалить</button>
								<button class = "defaultButton" type="submit" name="EditCurrent" 
										value="${currentEntry.getId()}">Редактировать</button>
							</td>	
						</tr>	
		   			</c:forEach>	
		    	</c:if>
		    	<tr class="tableRow">
		    		<c:choose>
		    			<c:when test="${sessionScope.currentEntryForEdit != null}" >
		    				
		    				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${currentEntryForEdit.getDate()}" var = "stringDate" />
		    				<td>${currentEntryForEdit.getId()}</td>
			    			<td><input name="date" value= "${stringDate}" />  </td>
			    			<td><select name="bankAccount" onchange="setCurrency();" >
							  	<option selected value="${currentEntryForEdit.getBankAccount().getId()}" >
							  							${currentEntryForEdit.getBankAccount().getName()}
							  				</option>
			   						<%
			   							//BankAccountDAO bankAccountDAO = new BankAccountDAO();
				   						for(BankAccount bankAccount: bankAccountService.retrieveAll()) {
											out.println("<option "
													+" value =\""+bankAccount.getId() +"\">"
													+  bankAccount.getName()+"</option>");
										};
									 %>
								</select>
							</td>
			    			<td><input name="currency" value= "${currentEntryForEdit.getCurrency().getCurrencyCode()}" readonly />
							</td>
			    			<td class="numericColumn"> <input name="sum" value = "${currentEntryForEdit.getSum().getValue()}"> </td>
			    			<td>
			    				<input class = "defaultButton" type="submit" name="Edit" value="Записать изменения">
			    				<input class = "defaultButton" type="submit" name="UndoEdit" value="Отменить">
			    			</td>
		    			</c:when>
			    		<c:otherwise >
				   			<td></td>
			    			<td><input name="date" value ="" ></td>
			    			<td><select name="bankAccount" onchange="setCurrency();">
			    				<option disabled selected value=""> Выберите счет  </option>
							  		<%
			   							//BankAccountDAO bankAccountDAO = new BankAccountDAO();
				   						for(BankAccount bankAccount: bankAccountService.retrieveAll()) {
											out.println("<option "
													+" value =\""+bankAccount.getId() +"\">"
													+  bankAccount.getName()+"</option>");
										};
									 %>
								</select>
							</td>
			    			<td><input name="currency" value= "" readonly />
							<td class="numericColumn"> <input name="sum" value = "0"> </td>
			    			<td><input class = "defaultButton" type="submit" name="AddNew" 
			    					onclick="return validateData(this);"
									value="Создать новый">
							</td>
			    		</c:otherwise>
			    	</c:choose>
		 		</tr>
		   </table>
	    </form>
	</body>
</html>