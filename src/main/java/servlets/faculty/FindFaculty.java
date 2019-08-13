package servlets.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;

@WebServlet("/findFaculty")
public class FindFaculty extends HttpServlet {

	private FacultyDao facultyDao = new FacultyDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("facultyId"));
		req.setAttribute("faculty", facultyDao.findById(id));
		req.getRequestDispatcher("/faculty/showFaculty.jsp").forward(req, resp);
	}
}
