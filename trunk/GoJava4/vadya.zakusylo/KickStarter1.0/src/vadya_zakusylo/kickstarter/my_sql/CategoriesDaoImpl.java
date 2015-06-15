package vadya_zakusylo.kickstarter.my_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vadya_zakusylo.kickstarter.model.Category;
import vadya_zakusylo.kickstarter.model.CategoryImpl;
import vadya_zakusylo.kickstarter.model.dao.CategoriesDao;

public class CategoriesDaoImpl implements CategoriesDao {
	private Connection connection;
	private List<Category> categories = new ArrayList<Category>();

	public CategoriesDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Category> getCategoriesList() {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectCategories());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categories.add(new CategoryImpl(resultSet.getString("category")));
			}
		} catch (SQLException e) {
			System.out.println("Can't connect to table \"Categories\"");
		}
		return categories;
	}

	private String selectCategories() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id_category, category ");
		sql.append("from category;");
		return sql.toString();
	}
}
