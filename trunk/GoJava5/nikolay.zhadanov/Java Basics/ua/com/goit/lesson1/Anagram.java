package ua.com.goit.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Lesson 1 - Task 3
 * �� ���� ����������� ���������� ���������� ������ ����, ����������� ���������.
 * ���� ������ ������� �� ���� ��������� ("����� �������") ��� ���� ������� ������� ���� �����������.
 * ��������� ������� � �������. 
 * ������ "���� ���� ����" => "���� ���� ����"
 */
public class Anagram {
	
	public static final String SPACE = " ";
	
	public static void main(String[] args)
	{
		System.out.println(makeAnagram());
	}
	
	private static String[] getWords() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String[] words = reader.readLine().split(SPACE);
		
		return words;
	}
	
	private static String makeAnagram()
	{
		String result = "";
		String[] words;
		
		try
		{
			words = getWords();
		}
		catch (Exception e)
		{
			System.out.println("��������� ����������: " + e.getClass().getSimpleName());
			return result;
		}
		
		//Quick & dirty
		for(String word : words)
		{
			String reversed = new StringBuilder(word).reverse().toString();
			result += reversed + SPACE;
		}
		
		return result.substring(0, result.length() - 1);
	}
}
