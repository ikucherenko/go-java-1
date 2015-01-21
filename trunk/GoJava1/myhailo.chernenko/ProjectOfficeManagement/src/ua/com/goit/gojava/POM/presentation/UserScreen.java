package ua.com.goit.gojava.POM.presentation;

import java.util.Scanner;

import ua.com.goit.gojava.POM.dataModel.DataObject;

public class UserScreen implements java.io.Closeable {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void close() {
		
		scanner.close();
		
	}
	
	public void showString(String info) {
		
		System.out.println(info);
		
	}
	
	public void showDataObject(DataObject obj) {
		
		System.out.println(obj.toString());
		
	}

	public void updateDataObject(DataObject obj) {
	
		showString("Input values of "+obj.getFieldsForUpdatePresentation()+", separated by '; ' and press Enter or press Enter for exit");
		
		String enteredString = getString();
		while (!enteredString.isEmpty()) {
			
			String[] enteredStringArray = enteredString.split("; ");
			
			String updateResult = obj.update(enteredStringArray);
			
			if (!updateResult.isEmpty()) {
				
				showString("Failed to update by reason: "+updateResult);
				
			}
			
			enteredString = getString();
		}
		
	}
	
	public String getString() {
		
		String info = "";
		
		if(scanner.hasNextLine()) {		
			info = scanner.nextLine();		
		}
		
		return info;
		
	}

}
