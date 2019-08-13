package servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import dao.StudentDao;
import models.Student;

@WebServlet("/saveStudent")
public class SaveStudent extends HttpServlet {

	private StudentDao studentDao = new StudentDao();
	private GroupDao groupDao = new GroupDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Student studentToSave = new Student();
		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		studentToSave.setId(id);
		studentToSave.setFirstName(req.getParameter("firstName"));
		studentToSave.setLastName(req.getParameter("lastName"));
		studentToSave.setStudentCardNumber(req.getParameter("studentCardNumber"));
		studentToSave.setGroup(groupDao.findById(Integer.parseInt(req.getParameter("groupId"))));
		req.setAttribute("student", studentDao.save(studentToSave));
		req.getRequestDispatcher("/student/showSaved.jsp").forward(req, resp);
	}
}
