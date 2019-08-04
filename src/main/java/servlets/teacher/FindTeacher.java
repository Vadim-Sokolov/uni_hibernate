package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;

@WebServlet("/findTeacher")
public class FindTeacher extends HttpServlet {

	private TeacherDao teacherDao = new TeacherDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("teacherId"));
		req.setAttribute("teacher", teacherDao.findById(id));
		req.getRequestDispatcher("/teacher/showTeacher.jsp").forward(req, resp);
	}
}
