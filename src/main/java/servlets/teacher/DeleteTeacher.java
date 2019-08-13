package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;
import models.Teacher;

@WebServlet("/deleteTeacher")
public class DeleteTeacher extends HttpServlet {

	private TeacherDao teacherDao = new TeacherDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("teacherId"));
		Teacher teacherToDelete = teacherDao.findById(id);
		teacherDao.delete(teacherToDelete);
		req.setAttribute("deletedTeacher", teacherToDelete);
		req.getRequestDispatcher("/teacher/showDeleted.jsp").forward(req, resp);
	}
}