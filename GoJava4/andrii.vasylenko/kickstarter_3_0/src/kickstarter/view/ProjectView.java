package kickstarter.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectView implements View {

	@Override
	public Map<String, Object> getParameters(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("project", Integer.valueOf(request.getParameter("project")));
		result.put("category", Integer.valueOf(request.getParameter("category")));

		return result;
	}

	@Override
	public void forward(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/Project.jsp");
		request.setAttribute("project", data.get("project"));
		view.forward(request, response);
	}
}
