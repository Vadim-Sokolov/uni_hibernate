package servlets.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import models.Course;

@WebServlet("/deleteCourse")
public class DeleteCourse extends HttpServlet {

	private CourseDao courseDao = new CourseDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("courseId"));
		Course courseToDelete = courseDao.findById(id);
		courseDao.delete(courseToDelete);
		req.setAttribute("deletedCourse", courseToDelete);
		req.getRequestDispatcher("/course/showDeleted.jsp").forward(req, resp);
	}
}
