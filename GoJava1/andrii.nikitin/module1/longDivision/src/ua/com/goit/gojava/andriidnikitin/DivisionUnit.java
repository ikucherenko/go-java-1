package ua.com.goit.gojava.andriidnikitin;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
//TODO: TRY 2/37
//SOLUTION: ADD TO DIVIDEND LIST BEFORE ADDING ZEROS
public class DivisionUnit {
	
	public static final double MAX_FRACTAL_DEPTH = 35;	
				
	static List<String> log = new ArrayList<String>();
				
	public static void main(String[] args) throws IOException{
		int dividend;
		int divider;	
		try(Scanner inputSource = new Scanner(System.in)){
			System.out.println ("Type input numbers:"
					+ "");
			System.out.println ("dividend = ");	
			dividend = getIntNumber(inputSource);
			System.out.println("divider = ");
			divider = getIntNumber(inputSource);
			longDivision(dividend, divider);
			visualise();
		}
		catch (IllegalArgumentException e){
			System.out.println("Wrong input!");
		}
	}
	
	public static String longDivision(int inputDividend, int inputDivider){
		if (inputDividend == 0){
			return divisionZeroDividendCase(inputDividend, inputDivider);
		}
		if (inputDivider == 0) {
			return divisionZeroDividerCase(inputDividend, inputDivider);
		}				
		else {
			return divisionRegularCase(inputDividend, inputDivider);
		}
	}
			
	private static String divisionZeroDividendCase(int inputDividend, 
			int inputDivider){
		if (inputDivider == 0) {
			log.add("0 / 0 = NaN");
			return "Not a Number";
		}
		else {
			log.add("0 / " 
			+ inputDivider + " = 0");
			return "0"; 	
		}
	}
	
	private static String divisionZeroDividerCase(int inputDividend, 
			int inputDivider){
		log.add(inputDividend + " / 0 = infinity");
		return "infinity";
	}
	
	private static String divisionRegularCase(int inputDividend, 
			int inputDivider){
		StringBuilder indentation = new StringBuilder();
		String result = "0";
		int dividendFractive = inputDividend % inputDivider;
		if (inputDividend - dividendFractive != 0) 
			result = Integer.valueOf(divisionNaturalPart(inputDividend, 
					inputDivider, indentation)).toString(); 
		else {
			visualiseStep(0, 0, 0, inputDivider, indentation);
		}
		if (dividendFractive != 0)
			result+= divisionFractivePart(dividendFractive, 
					inputDivider, indentation);
		else {
			result+= ".0";
			log.add(indentation + "0");
		}
		completeLog(inputDividend, inputDivider, result);
		return result;
	}	
	
	private static void completeLog(int inputDividend,
			int inputDivider, String result){
		log.set(0, " " + inputDividend);
		int maxLengthOf0and1LogRows = 
				Math.abs(log.get(0).length() - log.get(1).length());
		StringBuilder finalIndentantion = new StringBuilder();
		for (int i = 0; i < maxLengthOf0and1LogRows; i++) 
			finalIndentantion.append(' ');
		if (log.get(0).length() > log.get(1).length()){
			log.set(0, log.get(0) + "|_" + inputDivider);
			log.set(1, log.get(1) + finalIndentantion + '|' + result);
		}
		else{
			log.set(0, log.get(0) + finalIndentantion + "|_" + inputDivider);
			log.set(1, log.get(1) + '|' + result);
		}	
	}
	
	static int divisionNaturalPart(int dividend, int divider,
			StringBuilder indentation){
		int shiftLength = 0;
		int tempSubtrahend = 0;
		Stack<Integer> dividendDigits = new Stack<Integer>();
		int resultNatural = 0;
		while (dividend > 9){
			dividendDigits.push(dividend % 10);
			dividend = dividend / 10;
		}
		while (!dividendDigits.empty()){
			shiftLength = 0;
			if (dividend == 0) {
				indentation.append(' ');
				resultNatural = resultNatural * 10;				
			}
			while ((!dividendDigits.empty()) && (dividend < divider))
					dividend = dividend * 10 + (Integer)dividendDigits.pop(); 
			if ((dividendDigits.empty()) && (dividend < divider)) break;
			resultNatural = resultNatural * 10 + dividend / divider;
			tempSubtrahend = dividend % divider;
			visualiseStep(dividend, tempSubtrahend, shiftLength, divider, indentation);
			dividend = tempSubtrahend; 
		}
		return resultNatural;
	}
	
	public static String divisionFractivePart(int dividend, int divider,
			StringBuilder indentation){
		StringBuilder resultRow = new StringBuilder(); 
		boolean fractalIsSystematic = false;
		int periodisedElement = -1;
		int tempSubtrahend = 0;
		boolean pushZeroToResult = false;
		ArrayList<Integer> dividendVector = new ArrayList<Integer>();
		for (int i = 0; i < MAX_FRACTAL_DEPTH; i++){	
			if (dividend!= 0)
				while (dividend < divider){ 	
					if (pushZeroToResult){
						i++;
						resultRow.append('0');
					}
					dividend = dividend * 10;
					pushZeroToResult = true;					
				}
			else break;
			pushZeroToResult = false;
			if (dividendVector.contains(dividend)){
				fractalIsSystematic = true;	
				periodisedElement = dividendVector.indexOf(dividend);
				indentation.append(' ');
				log.add(indentation.toString() + dividend);
				break;
			}			
			dividendVector.add(dividend);
			resultRow.append(dividend / divider);
			tempSubtrahend = dividend % divider;
			visualiseStep(dividend, tempSubtrahend, 0, 0, indentation);
			dividend = tempSubtrahend;
		}
		String result = resultRow.toString();
		if (fractalIsSystematic) {
			if (periodisedElement == 0) result = "(" + result + ")";	
			else 
				result = result.substring(0, periodisedElement) 
				+"(" + result.substring(periodisedElement) + ")";
		}
		return "." + result;
	}
	
	public static void visualiseStep(int dividend, int subtrahend, int shift, int divider,
			StringBuilder indentation){	
		StringBuilder dividerShift = new StringBuilder();
		shift+= differenceOfLengths(dividend - subtrahend, subtrahend);
		subtrahend = dividend - subtrahend;
		int dividerShiftLength = differenceOfLengths(dividend, subtrahend);
			for (int i = 0; i < dividerShiftLength; i++)
				dividerShift.append(' ');
		log.add(" " + indentation.toString() + dividend);
		log.add(indentation.toString() + '-' + dividerShift + subtrahend);
		for (int i = 0; i < shift; i++) indentation.append(' ');
		indentation.append(dividerShift);
		dividerShift = new StringBuilder();
	}
	
	public static void visualise(){
		for (String x: log) System.out.println(x);
	}
	
	public static int differenceOfLengths(int arg0, int arg1){
		return Integer.valueOf(arg0).toString().length()
				-Integer.valueOf(arg1).toString().length();
	}
	
	public static int lengthOfNumber(int number){
		return Integer.valueOf(number).toString().length();
	}
	
	public static int getIntNumber(Scanner inputSource) 
			throws NumberFormatException{
		String input = inputSource.nextLine();
		return Integer.parseInt(input);
	}
	
}