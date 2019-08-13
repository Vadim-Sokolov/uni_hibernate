package servlets.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;

@WebServlet("/deleteCourseForm")
public class DeleteCourseForm extends HttpServlet {

	private CourseDao courseDao = new CourseDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("courses", courseDao.findAll());
		req.getRequestDispatcher("/course/deleteCourse.jsp").forward(req, resp);
	}
}
