package servlets.auditorium;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;
import models.Auditorium;

@WebServlet("/saveAuditorium")
public class SaveAuditorium extends HttpServlet {

	private AuditoriumDao auditoriumDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Auditorium auditoriumToSave = new Auditorium();
		if (req.getParameter("id").contentEquals("")) {
			auditoriumToSave.setId(null);
		} else {
			auditoriumToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		auditoriumToSave.setName(req.getParameter("name"));
		auditoriumToSave.setCapacity(Integer.parseInt(req.getParameter("capacity")));
		Auditorium savedAuditorium = auditoriumDao.save(auditoriumToSave);
		req.setAttribute("auditorium", savedAuditorium);
		req.getRequestDispatcher("/auditorium/showSaved.jsp").forward(req, resp);
	}
}
