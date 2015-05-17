package kickstarter.mvc;

import kickstarter.pages.Page;
import kickstarter.ui.UserInterface;

public class View {
	Model model;
	Page page;
	private UserInterface ui;

	public View(Model model, UserInterface ui) {
		this.model = model;
		this.ui = ui;
	}

	public void print() {
		page = model.getPage();
		page.print();
	}
}
