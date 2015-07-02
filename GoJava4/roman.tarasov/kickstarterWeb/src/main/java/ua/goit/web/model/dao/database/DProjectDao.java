package ua.goit.web.model.dao.database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.goit.web.model.dao.KickstarterException;
import ua.goit.web.model.dao.Project;

public class DProjectDao {
	private Connection conn;

	public List<Project> sortProjectsByCategoryID(int categoryID)
			throws KickstarterException {
		List<Project> sorted = new ArrayList<Project>();
		Statement statement;
		try {
			conn = Pool.getDataSource().getConnection();
			statement = conn.createStatement();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT ");
			sql.append("id_project, ");
			sql.append("id_category, ");
			sql.append("name, ");
			sql.append("short_description, ");
			sql.append("description, ");
			sql.append("pledged, ");
			sql.append("amount, ");
			sql.append("days_to_go, ");
			sql.append("link, ");
			sql.append("history,");
			sql.append("invest_options ");
			sql.append("FROM projects ");
			sql.append("WHERE id_category=");
			sql.append(Integer.toString(categoryID));

			ResultSet rs = statement.executeQuery(sql.toString());

			if (!rs.next()) {
				throw new KickstarterException("the category was not found");
			}
			do {
				sorted.add(newProject(rs));
			} while (rs.next());

		} catch (SQLException | KickstarterException e) {
			sorted = null;

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (sorted == null) {
			throw new KickstarterException(
					"the category or projects was not found");
		}
		return sorted;
	}

	private Project newProject(ResultSet rs) throws SQLException {
		Project project = new Project();
		project.setID(rs.getInt(1));
		project.setCategoryID(rs.getInt(2));
		project.setName(rs.getString(3));
		project.setShortDescription(rs.getString(4));
		project.setDescription(rs.getString(5));
		project.setPledged(rs.getDouble(6));
		Array amount = rs.getArray(7);
		Double[] a = (Double[]) amount.getArray();
		project.setAmount(a);
		project.setDaysToGo(rs.getInt(8));
		project.setLinkToVideo(rs.getString(9));
		project.setHistory(rs.getString(10));
		Array investOptions = rs.getArray(11);
		String[] str = (String[]) investOptions.getArray();
		project.setInvestmentOptions(str);
		return project;
	}

	public Project getProjectById(int ID) throws KickstarterException {
		Project project = null;
		Statement statement;
		try {
			conn = Pool.getDataSource().getConnection();
			statement = conn.createStatement();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT ");
			sql.append("id_project, ");
			sql.append("id_category, ");
			sql.append("name, ");
			sql.append("short_description, ");
			sql.append("description, ");
			sql.append("pledged, ");
			sql.append("amount, ");
			sql.append("days_to_go, ");
			sql.append("link, ");
			sql.append("history, ");
			sql.append("invest_options ");
			sql.append("FROM projects ");
			sql.append("WHERE id_project=");
			sql.append(Integer.toString(ID));

			ResultSet rs = statement.executeQuery(sql.toString());
			rs.next();
			project = newProject(rs);
		} catch (SQLException e) {
			project = null;

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (project == null) {
			throw new KickstarterException("the project was not found");
		}
		return project;
	}


	public void updateProject(Project project) throws KickstarterException {
		StringBuffer sql = new StringBuffer();
		int error = 0;
		try {
			sql.append("UPDATE projects set ");
			sql.append("id_project=? , ");
			sql.append("id_category=? , ");
			sql.append("name=? , ");
			sql.append("short_description=? , ");
			sql.append("description=? , ");
			sql.append("pledged=? , ");
			sql.append("amount=? , ");
			sql.append("days_to_go=? , ");
			sql.append("link=? , ");
			sql.append("history=? , ");
			sql.append("invest_options=? ");
			sql.append("WHERE id_project=");
			sql.append(Integer.toString(project.getID()));
			conn = Pool.getDataSource().getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql
					.toString());
			fillStatement(preparedStatement, project, conn);
			preparedStatement.executeUpdate();
		} catch (KickstarterException | SQLException e) {
			error = 1;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				error += 2;
			}
			if (error == 1) {
				throw new KickstarterException("update project has error");
			}
			if (error == 2) {
				throw new KickstarterException(
						"connection close after update project  has error");
			}
			if (error == 3) {
				throw new KickstarterException("database operations  has error");
			}
		}
	}

	 void fillStatement(PreparedStatement ps, Project project,
			Connection connection) throws KickstarterException {
		try {
			ps.setInt(1, project.getID());
			ps.setInt(2, project.getCategoryID());
			ps.setString(3, project.getName());
			ps.setString(4, project.getShortDescription());
			ps.setString(5, project.getDescription());
			ps.setDouble(6, project.getPledged());

			Array sqlArray = connection.createArrayOf("float8",
					project.getAmount());
			ps.setArray(7, sqlArray);
			ps.setInt(8, project.getDaysToGo());
			ps.setString(9, project.getLinkToVideo());
			ps.setString(10, project.getHistory());

			sqlArray = connection.createArrayOf("varchar",
					project.getInvestmentOptions());
			ps.setArray(11, sqlArray);
			//ps.executeUpdate();
		} catch (SQLException e) {
			throw new KickstarterException("update project has error", e);
		}

	}
}
