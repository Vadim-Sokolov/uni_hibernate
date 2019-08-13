package servlets.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;

@WebServlet("/showAllCourses")
public class GetCourses extends HttpServlet {

	private CourseDao courseDao = new CourseDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("courses", courseDao.findAll());
		req.getRequestDispatcher("/course/showAllCourses.jsp").forward(req, resp);
	}
}
