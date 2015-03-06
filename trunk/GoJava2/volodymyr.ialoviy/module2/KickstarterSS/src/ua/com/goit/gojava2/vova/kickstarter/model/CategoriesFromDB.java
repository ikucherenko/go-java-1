package ua.com.goit.gojava2.vova.kickstarter.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFromDB implements Categories{
	private Connection connection;
	public CategoriesFromDB(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		ResultSet result;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM categories ORDER BY id_category");
			while (result.next()) {
			    categories.add(new Category(result.getInt("id_category"), result.getString("name_category")));
			}
		} catch (SQLException e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		return categories;
	}

	@Override
	public String showCatecoryName(int categoryId) {
		Category category = getCategories().get(categoryId - 1);
		String name = category.getCategoryName();
		return name;
	}

	@Override
	public int[] getKickCategories() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		List<Category> category = getCategories();
		for (Category cat : category){
			array.add(cat.getCategoryID());
		}

		int[] a = new int[array.size()];//TODO DELETE, INT[] = lIST
        int j = 0;
        for (Integer i : array){
        	a[j] = i;
        	j++;
        }
		return a;
	}
}