package servlets.auditorium;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;

@WebServlet("/deleteAuditoriumForm")
public class DeleteAuditoriumForm extends HttpServlet {

	private AuditoriumDao auditoriumDao;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("auditoriums", auditoriumDao.findAll());
		req.getRequestDispatcher("/auditorium/deleteAuditorium.jsp").forward(req, resp);
	}
}
