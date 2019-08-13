package servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;

@WebServlet("/saveGroupForm")
public class SaveGroupForm extends HttpServlet {

	private FacultyDao facultyDao = new FacultyDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("faculties", facultyDao.findAll());
		req.getRequestDispatcher("/group/saveGroup.jsp").forward(req, resp);
	}
}
