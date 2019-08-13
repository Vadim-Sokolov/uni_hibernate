package servlets.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;

@WebServlet("/findCourse")
public class FindCourse extends HttpServlet {

	private CourseDao courseDao = new CourseDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("courseId"));
		req.setAttribute("course", courseDao.findById(id));
		req.getRequestDispatcher("/course/showCourse.jsp").forward(req, resp);
	}
}
