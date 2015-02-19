package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesFromDB implements Categories{

	private static final String PASS_DB = "7575";
	private static final String NAME_DB = "postgres";
	private static final String JDBC_POSTGRESQL_PATH = "jdbc:postgresql://127.0.0.1:5432/kickstarter";

	public static void main(String[] args) {
		CategoriesFromDB cat = new CategoriesFromDB();
		System.out.println(cat.showAllCatecoriesInKickstarter());
		
		Projects projects = new ProjectsFromFile();
		
		System.out.println(cat.showAllProjectInCategory(1, projects));
		
		System.out.println(cat.showCatecoryName(2));
		
		System.out.println(Arrays.toString(cat.getKickCategories()));
		
	}
	
	@Override
	public void writeAllCatecories() {
	}

	@Override
	public String showAllCatecoriesInKickstarter() {
		String s = "";
		Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_POSTGRESQL_PATH, NAME_DB, PASS_DB);
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories");
            while (result.next()) {
                s += "Номер категории #" + result.getInt("id_category")
                        + "\t" + result.getString("name_category") + "\n";
            }
        } catch (Exception ex) {
            Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
		return s.substring(0, s.length() - 1);
	}

	@Override
	public String showAllProjectInCategory(int categoryId, Projects projects) {
		String s = "";
		Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_POSTGRESQL_PATH, NAME_DB, PASS_DB);
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM projects WHERE id_category =" + categoryId);
            while (result.next()) {
                s += "Номер проекта #" + result.getInt("id_project")
                        + "\t" + result.getString("name_project") + "\n";
            }
        } catch (Exception ex) {
            Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
		return s.substring(0, s.length() - 1);
	}

	@Override
	public String showCatecoryName(int categoryId) {
		String s = "";
		Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_POSTGRESQL_PATH, NAME_DB, PASS_DB);
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories WHERE id_category =" + categoryId);
            while (result.next()) {
                s += result.getString("name_category");
            }
        } catch (Exception ex) {
            Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
		return s;
	}


	@Override
	public int[] getKickCategories() {
		Connection connection = null;
		ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_POSTGRESQL_PATH, NAME_DB, PASS_DB);
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories");
            while (result.next()) {
            	array.add(result.getInt("id_category"));
            }
        } catch (Exception ex) {
            Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCType.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int[] a = new int[array.size()];
        int j = 0;
        for (Integer i : array){
        	a[j] = i;
        	j++;
        }
		return a;
	}

	@Override
	public int[] projectsContain(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCounterCategory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCounterCategory(int counterCategory) {
	}

	@Override
	public ArrayList<Category> getListCatecories() {
		return null;
	}

	@Override
	public void setListCatecories(ArrayList<Category> listCatecories) {
	}

}
