package go_java_4.vadya_zakusylo.kickstarter;

import java.util.Random;

public abstract class Quote {
	String[] quotes;

	public String chooseQuote() {
		initArrayQuotes();
		int randomIndex = chooseRandomIndex();
		return printQuote(randomIndex);
	}

	public abstract void initArrayQuotes();

	private int chooseRandomIndex() {
		if (quotes.length > 0) {
			return new Random().nextInt(quotes.length);
		} else {
			return -1;
		}
	}

	private String printQuote(int randomIndex) {
		if (randomIndex >= 0) {
			return quotes[randomIndex];
		} else {
			return "";
		}
	}
}
