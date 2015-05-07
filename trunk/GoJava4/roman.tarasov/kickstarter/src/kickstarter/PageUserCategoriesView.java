
package kickstarter;

public class PageUserCategoriesView extends Page {
	CategoryList list;
	
	PageUserCategoriesView( CategoryList list) {
		this.list = list;
	}

	void printCategories() {
		list.printList(ui);
	}
	
@Override
	public Page getNextPage() {
		ui.display("=========================");
		ui.display("|     Categories        |");
		ui.display("=========================");
		printCategories();
		ui.display("------------------------");
		ui.display("Select Category:");

		while (true) {
			ui.display(" e- exit to Login Page");
			String stringFromUI = ui.inputString();
			if (stringFromUI.equals("e")) {
				ui.display("exit");
				return pages[PAGE_LOGIN];
			}
			try {
				int parsed = Integer.parseInt(stringFromUI);
				categoryToUserProjectsView = list.get(parsed);
				return pages[PAGE_USER_PROJECTS_VIEW];
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				//throw new IllegalArgumentException();
				ui.display("input correct command, please");
				continue;
			}
		
			
			
		}
	}
}