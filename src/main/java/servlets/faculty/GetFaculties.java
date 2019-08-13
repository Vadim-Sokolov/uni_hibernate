package servlets.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;

@WebServlet("/showAllFaculties")
public class GetFaculties extends HttpServlet {

	private FacultyDao facultyDao = new FacultyDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("faculties", facultyDao.findAll());
		req.getRequestDispatcher("/faculty/showAllFaculties.jsp").forward(req, resp);
	}
}
