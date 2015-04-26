/*
 * ����� �������� �� ���������������� ���������.
 * ����������� ����� ������
 * �������� ������� ������
 * ����� ��������� � ����������
 *  
 * 
 * 
 */

package com.sergiisavin;

import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		String inputData = null;
		double[] numbers = null;
		
		do{
			
			System.out.print("������� ��� ����� ����� ���� ������ (��� exit ��� ������ �� ���������): ");
			inputData = scanner.nextLine();
			  
			
			//�������� � ������ �������� � �������� ������� � ������
			inputData = inputData.trim();
			inputData = inputData.toLowerCase();
			
			//��� ������������ ��� exit - ����� �� ���������
			if(inputData.equals("exit")){
				System.out.println("����!");
				System.exit(0);
			}
			
			//�������� ������ ��������� �� �������� ������
			String[] elements = inputData.split(" ");
			
			//�������� ������������� ������ ��������� � ������ duble
			try{
				numbers = getNumbers(elements);
			}catch(NumberFormatException ex){
				System.out.println("������������ ������� ������...");
				System.out.println("������� ����� ��� ������� ����� ����� ������ (��������: 3 5 11.7 2e4 7): ");
				continue;
			}
			
			//���������� � ����� ����������
			System.out.println("���������� ����� ����� ������������ ����������: " + DistanceFinder.findDistance(numbers));
			
		//���� ������������ �� ����� exit	
		}while(true);
	}

	//����������� ������ ����� � ������ double
	//� ������ ������������� �������������� ������ NumberFormatException
	//���������� ������ double
	private static double[] getNumbers(String[] elements) {
		double[] numbers = new double[elements.length];
		for(int i = 0; i < elements.length; i++){
			numbers[i] = Double.parseDouble(elements[i]);
		}
		return numbers;
	}

}