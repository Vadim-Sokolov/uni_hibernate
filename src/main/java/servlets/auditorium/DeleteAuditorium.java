package servlets.auditorium;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;
import models.Auditorium;

@WebServlet("/deleteAuditorium")
public class DeleteAuditorium extends HttpServlet {

	private AuditoriumDao auditoriumDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("auditoriumId"));
		Auditorium aduditoriumToDelete = auditoriumDao.findById(id);
		auditoriumDao.delete(aduditoriumToDelete);
		req.setAttribute("deletedAuditorium", aduditoriumToDelete);
		req.getRequestDispatcher("/auditorium/showDeleted.jsp").forward(req, resp);
	}
}
