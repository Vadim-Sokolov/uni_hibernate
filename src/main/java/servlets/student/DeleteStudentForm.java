package servlets.student;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;

@WebServlet("/deleteStudentForm")
public class DeleteStudentForm extends HttpServlet {

	private StudentDao studentDao = new StudentDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("students", studentDao.findAll());
		req.getRequestDispatcher("/student/deleteStudent.jsp").forward(req, resp);
	}
}
