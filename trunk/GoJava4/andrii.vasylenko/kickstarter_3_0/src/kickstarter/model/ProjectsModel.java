package kickstarter.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import kickstarter.model.dao.DAO;

public class ProjectsModel implements Model {
	private DAO dao;

	@Override
	public void init(DAO dao) {
		this.dao = dao;
	}

	@Override
	public Map<String, Object> getData(Map<String, Object> parameters) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();

		int categoryId = (int) parameters.get("category");
		result.put("projects", dao.getProjects(categoryId));

		return result;
	}
}
