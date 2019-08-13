package servlets.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;
import dao.CourseDao;
import dao.GroupDao;
import dao.TeacherDao;

@WebServlet("/saveLectureForm")
public class SaveLectureForm extends HttpServlet {

	private AuditoriumDao auditoriumDao = new AuditoriumDao();
	private CourseDao courseDao = new CourseDao();
	private GroupDao groupDao = new GroupDao();
	private TeacherDao teacherDao = new TeacherDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("auditoriums", auditoriumDao.findAll());
		req.setAttribute("courses", courseDao.findAll());
		req.setAttribute("groups", groupDao.findAll());
		req.setAttribute("teachers", teacherDao.findAll());
		req.getRequestDispatcher("/lecture/saveLecture.jsp").forward(req, resp);
	}
}
