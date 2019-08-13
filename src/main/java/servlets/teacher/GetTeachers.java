package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;

@WebServlet("/showAllTeachers")
public class GetTeachers extends HttpServlet {

	private TeacherDao teacherDao = new TeacherDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("teachers", teacherDao.findAll());
		req.getRequestDispatcher("/teacher/showAllTeachers.jsp").forward(req, resp);
	}
}
