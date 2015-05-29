package kickstarter.view;

import static kickstarter.control.State.*;

import kickstarter.control.State;
import kickstarter.view.printer.Printer;
import kickstarter.view.reader.Reader;

public class CategoriesView extends ConsoleView {

	public CategoriesView(Printer printer, Reader reader) {
		super(printer, reader);
	}

	@Override
	public State getDirection(int item) {
		if (item == 0) {
			return THE_END;
		}
		return PROJECTS;
	}

	@Override
	protected String getHead() {
		return "---CHOICE CATEGORY---";
	}

	@Override
	protected String getMenu() {
		StringBuilder result = new StringBuilder();

		result.append(0);
		result.append(" - ");
		result.append("EXIT");
		result.append("\r\n");

		return result.toString();
	}
}
