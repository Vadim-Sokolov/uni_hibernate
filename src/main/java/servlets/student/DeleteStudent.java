package servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import models.Student;

@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {

	private StudentDao studentDao = new StudentDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("studentId"));
		Student studentToDelete = studentDao.findById(id);
		studentDao.delete(studentToDelete);
		req.setAttribute("deletedStudent", studentToDelete);
		req.getRequestDispatcher("/student/showDeleted.jsp").forward(req, resp);
	}
}