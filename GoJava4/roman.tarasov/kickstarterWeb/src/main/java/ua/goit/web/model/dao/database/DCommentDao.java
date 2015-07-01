package ua.goit.web.model.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.goit.web.model.dao.Comment;
import ua.goit.web.model.dao.KickstarterException;

public class DCommentDao {
	private Connection conn;
	private static final int MAX_COMMENT_ID = 20000;

	public List<Comment> getCommentsByProjectID(int projectID)
			throws KickstarterException {
		List<Comment> commentsOfProject = new ArrayList<Comment>();
		Statement statement;
		try {
			conn = Pool.getDataSource().getConnection();
			statement = conn.createStatement();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT * FROM comments WHERE id_project=");
			sql.append(Integer.toString(projectID));
			ResultSet rs = statement.executeQuery(sql.toString());
			rs = statement.getResultSet();
			while (rs.next()) {
				Comment newComment = new Comment();
				newComment.setComment(rs.getString("comment"));
				newComment.setProjectID(projectID);
				newComment.setUserID(rs.getInt("id_user"));
				newComment.setCommentID(rs.getInt("id_comment"));
				commentsOfProject.add(newComment);
			}

		} catch (SQLException e) {
			commentsOfProject = null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (commentsOfProject == null) {
			throw new KickstarterException("comments not found");
		}
		return commentsOfProject;
	}

	public void addComment(Comment newComment) throws KickstarterException {
		Connection conn = null;
		int flagOfError = 0;

		try {
			conn = Pool.getDataSource().getConnection();
			Statement statement = conn.createStatement();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT ");
			sql.append("MAX ");
			sql.append("(id_comment) ");
			sql.append("AS ");
			sql.append("max_id ");
			sql.append("FROM ");
			sql.append("comments ");
			statement.executeQuery(sql.toString());
			ResultSet rs = statement.getResultSet();
			rs.next();
			int id = 1 + rs.getInt("max_id");
			if (id > MAX_COMMENT_ID) {
				throw new KickstarterException(
						"database of comments overloaded");
			}
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO comments (id_comment, id_project, id_user, comment) values(?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, newComment.getProjectID());
			ps.setInt(3, newComment.getUserID());
			ps.setString(4, newComment.getComment());
			ps.executeUpdate();

		} catch (SQLException e) {
			flagOfError = 1;

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				flagOfError += 2;
			}
		}
		if (flagOfError == 1) {
			throw new KickstarterException("insert comments in db has  error");
		}
		if (flagOfError == 2) {
			throw new KickstarterException("connection close has  error");
		}
		if (flagOfError == 3) {
			throw new KickstarterException("database error");
		}
	}
}
