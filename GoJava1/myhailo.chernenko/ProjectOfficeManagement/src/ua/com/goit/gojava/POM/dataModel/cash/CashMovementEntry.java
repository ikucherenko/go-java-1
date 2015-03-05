package ua.com.goit.gojava.POM.dataModel.cash;

import java.util.Date;

import ua.com.goit.gojava.POM.dataModel.common.Money;

public class CashMovementEntry {

	private long id;
	private Date date;
	private String description;
	private BankAccount bankAccount;
	private Money sum;
	
	public long getId() {
		
		return id;
		
	}
	
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public Date getDate() {
		
		return date;
		
	}
	
	public void setDate(Date date) {
		
		this.date = date;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}
	
	public void setDescription(String description) {
		
		this.description = description;
		
	}
	
	public Money getSum() {
		
		return sum;
		
	}
	
	public void setSum(Money sum) {
		
		this.sum = sum;
		
	}

}
