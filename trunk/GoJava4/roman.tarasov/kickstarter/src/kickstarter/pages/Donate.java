package kickstarter.pages;

import kickstarter.payment.Bank;
import kickstarter.repository.ProjectRepository;

public class Donate extends PageView {

	public Donate(Bank bank, ProjectRepository projects) {
		this.bank = bank;
		this.projects = projects;
	}

	public String getHeader() {
		String header = "";
		header += "\n=========================";
		header += "\n|       donate          |";
		header += "\n=========================";
		header += "\n";
		header += "\n------------------------";
		header += "\nOptions: donate in format <bankir:777:20> where login -bankir-, cardnumber -777-, pay -20- \n<p>- previous page  ";
		return header;
	}
}
