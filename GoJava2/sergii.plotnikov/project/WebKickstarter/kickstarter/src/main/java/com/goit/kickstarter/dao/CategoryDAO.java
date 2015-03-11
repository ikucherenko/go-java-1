package com.goit.kickstarter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.goit.kickstarter.model.Category;

@Component
public class CategoryDAO extends AbstractDAO{
	
	public Category getCategory(int id) {
		Category cat = null;
		try (Connection connection = getConnection()){
			String query = "SELECT * FROM categories WHERE id =?";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				cat = new Category(rs.getString("category_name"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

	public void createCategory(Category category) {
		try (Connection connection = getConnection()){
			String query = "INSERT into categories(category_name)"
					+"VALUES(?);";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, category.getTitle());
			
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCategory(Category category) {
		try (Connection connection = getConnection()){
			String query = "UPDATE categories SET category_name=? "
					+ "WHERE category_name=?";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, category.getTitle());
			
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCategory(int id) {
		try (Connection connection = getConnection()){
			String query = "DELETE FROM categories WHERE id=?";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getLength() {
		int count=0;
		try (Connection connection = getConnection()){			 
			String query = "SELECT * FROM categories";
			
			PreparedStatement stmt = connection.prepareStatement(query);
						
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return count;
	}
	
	public List<Category> getCategories() {
		List<Category> list = new ArrayList<Category>();
		try (Connection connection = getConnection()){			 
			String query = "SELECT * FROM categories";
			
			PreparedStatement stmt = connection.prepareStatement(query);
						
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				list.add(new Category(rs.getString("category_name"), rs.getInt("id")));
			}
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return list;
	}
}
