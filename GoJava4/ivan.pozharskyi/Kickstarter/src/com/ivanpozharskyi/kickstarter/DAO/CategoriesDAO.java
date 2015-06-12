package com.ivanpozharskyi.kickstarter.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ivanpozharskyi.kickstarter.entity.Categories;
import com.ivanpozharskyi.kickstarter.entity.Category;


public class CategoriesDAO implements Categories {
private Connection con;
	
	public void addCategory(String name) throws SQLException{
		con = ConnectionManager.getConnection();
		Statement statement;
		statement = con.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS categories (id int(20) AUTO_INCREMENT, "
				+ "name text, PRIMARY KEY(id))");
		PreparedStatement preparedStatement;
		String query = "INSERT INTO categories (name)"
				+ "VALUES(?)";
	
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.execute();
	}
	public void dropCategories() throws SQLException{
		con = ConnectionManager.getConnection();
		Statement statement = con.createStatement();
		statement.execute("DROP TABLE categories");
		
	}
	public void deleteCategory(int id) throws SQLException{
		con = ConnectionManager.getConnection();
		String query = "DELETE FROM categories WHERE id = ?"; 
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
	
	}
	@Override
	public Category getCategory(int id) throws SQLException{
		Category category = null;
		con = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM categories WHERE id = ?";
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
	
		while(resultSet.next()){
			category = new Category(resultSet.getInt("id"), resultSet.getString("name"));
			
		}
		return category;
	}
	public int getSize() throws SQLException{
		
		con = ConnectionManager.getConnection();
		int size;
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM categories");
		while(resultSet.next()){
			 return size = resultSet.getInt("count(*)");
		}
		throw new RuntimeException("NO Categories, try again");
	}
	public Set<Category> getAll() throws SQLException{
		Set<Category> categories = new LinkedHashSet<Category>();
		
		for(int i=1; i<getSize(); i++){
			categories.add(getCategory(i));
		}
		
		return categories;
	}
//	public static void main(String[] args) throws SQLException {
//		categoriesDAO categoriesDAO = new categoriesDAO();
// 
//		System.out.println(categoriesDAO.getAll());
//		
//		
//	}
	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
 
}
