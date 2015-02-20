package ua.home.kickstarter.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.home.kickstarter.content.Category;
import ua.home.kickstarter.model.CategoriesDao;
import ua.home.kickstarter.factory.DaoFactory;

public class CategoriesController {
	private DaoFactory daoFactory;

	public CategoriesController() {
		daoFactory = new DaoFactory();
	}

	public int getCategoriesSizeToView() {
		int size = -1;
		try(Connection con = daoFactory.getConnection()) {
			CategoriesDao categoriesDao = daoFactory.getCategoriesDao(con);
			size = (int) categoriesDao.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return size;
	}

	public List<Category> getDBCategoriesToView() {
		List<Category> list = null;
		try (Connection con = daoFactory.getConnection()){
			CategoriesDao categoriesDao = daoFactory.getCategoriesDao(con);
			list = categoriesDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getContent() {
		StringBuilder categoriesContent = new StringBuilder();
		for (Category category : getDBCategoriesToView()) {
			categoriesContent.append(category.getId()).append(" - ").append(category.getName()).append("\n");
		}
		return categoriesContent.toString();
	}
}
