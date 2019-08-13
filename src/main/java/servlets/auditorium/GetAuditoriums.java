package servlets.auditorium;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;

@WebServlet("/showAllAuditoriums")
public class GetAuditoriums extends HttpServlet {

	private AuditoriumDao auditoriumDao = new AuditoriumDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("auditoriums", auditoriumDao.findAll());
		req.getRequestDispatcher("/auditorium/showAllAuditoriums.jsp").forward(req, resp);
	}
}
