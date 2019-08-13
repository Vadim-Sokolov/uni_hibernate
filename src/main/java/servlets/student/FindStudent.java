package servlets.student;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;

@WebServlet("/findStudent")
public class FindStudent extends HttpServlet {

	private StudentDao studentDao = new StudentDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("studentId"));
		req.setAttribute("student", studentDao.findById(id));
		req.getRequestDispatcher("/student/showStudent.jsp").forward(req, resp);
	}
}
