package servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;

@WebServlet("/saveStudentForm")
public class SaveStudentForm extends HttpServlet {

	private GroupDao groupDao = new GroupDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("groups", groupDao.findAll());
		req.getRequestDispatcher("/student/saveStudent.jsp").forward(req, resp);
	}
}
