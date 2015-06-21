package servlet;

import static model.eModels.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelStrategy;
import model.iModel;
import dao.pool.KickstarterException;

/**
 * Servlet implementation class DetailedProject
 */

public class DetailedProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		iModel model = ModelStrategy.getInstance().getModel(PROJECT_M);
		try {
			model.doGet(request, response);
		} catch (KickstarterException e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("Error.jsp")
					.forward(request, response);
		}
	}
}
