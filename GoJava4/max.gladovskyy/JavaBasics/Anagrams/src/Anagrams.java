import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Anagrams {

	public static void main(String[] args) {
		String userString = readLine();
		System.out.println(reversString(userString));
	}

	private static String reversString(String userString) {
		String[] userStringSplited = userString.split(" ");
		String userStringReversed = new String();
		
		for (String word: userStringSplited) {
			userStringReversed = userStringReversed + reverseWord(word) + " ";
		}
		return userStringReversed;
	}
	
	private static String readLine() {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return consoleReader.readLine();
		} catch (IOException e) {
			System.out.println("You entered incorrect data (words separated with space is needed)");
		}
		return null;
	}

	private static String reverseWord(String word) {
		char[] wordChars = word.toCharArray();
		char[] result = new char[wordChars.length];
		
		for (int i=0; i<wordChars.length; i++) {
			result[wordChars.length-1-i] = checkIsCorrect(wordChars[i]);
		}
		return new String(result);
	}
	
	private static Character checkIsCorrect (Character letter) {
		if (Character.isLetter(letter)) {
			 return letter;
		} else {
			System.err.println(letter + " is not a letter symbol in your string. "
					+ "Please enter string with only letters and spaces");
			System.exit(0);
		}
		return letter;
	}

}
