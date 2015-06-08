package ua.com.gojava4.kickstarter.view.pages;

import java.util.List;

import ua.com.gojava4.kickstarter.control.DataIOTypeStorage;
import ua.com.gojava4.kickstarter.control.ExitProgramException;
import ua.com.gojava4.kickstarter.dao.Dao;
import ua.com.gojava4.kickstarter.entities.Category;
import ua.com.gojava4.kickstarter.entities.Quote;
import ua.com.gojava4.kickstarter.view.Reader;
import ua.com.gojava4.kickstarter.view.Writer;

public class CategoriesPage implements Page {

	private List<Category> allCategories;
	private DataIOTypeStorage dataIOTypeStorage;

	public CategoriesPage(Reader reader, Writer writer, Dao genericDao) {
		
	}

	public CategoriesPage(DataIOTypeStorage dataIOTypeStorage) {
		this.dataIOTypeStorage = dataIOTypeStorage;
		this.allCategories = dataIOTypeStorage.getGenericDao().getAllCategories();
	}

	@Override
	public void showPage() {
		showRandomQuote();
		showAllCategoriesList();
	}

	@Override
	public Page getNextPage() throws ExitProgramException {
		String userInput = dataIOTypeStorage.getReader().readUserInput();
		try {
			int categoryId = Integer.parseInt(userInput);
			for (Category category : allCategories) {
				if (category.getId() == categoryId) {
					return new ProjectsPage(dataIOTypeStorage, categoryId);
				}
			}
		} catch (NumberFormatException e) {
			if (userInput.toLowerCase().equals("exit")
					|| userInput.toLowerCase().equals("quit")
					|| userInput.toLowerCase().equals("q")) {
				throw new ExitProgramException();
			}
		}
		showErrorDescription();
		return this;
	}

	private void showErrorDescription() {
		dataIOTypeStorage.getWriter().print("Error input. Threre is no such category!\n"
				+ "To exit print q\n");
	}

	private void showAllCategoriesList() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n  Categories\n");
		for (Category category : allCategories) {
			sb.append("<" + category.getId() + "> " + category.getName() + "\n");
		}
		dataIOTypeStorage.getWriter().println(sb.toString());
	}

	private void showRandomQuote() {
		dataIOTypeStorage.getWriter().println();
		Quote quote = dataIOTypeStorage.getGenericDao().getRandomQuote();
		dataIOTypeStorage.getWriter().println(quote.getQuoteString());
	}

}