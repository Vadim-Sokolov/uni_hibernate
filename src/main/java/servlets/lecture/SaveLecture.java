package servlets.lecture;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuditoriumDao;
import dao.CourseDao;
import dao.GroupDao;
import dao.LectureDao;
import dao.TeacherDao;
import models.Lecture;

@WebServlet("/saveLecture")
public class SaveLecture extends HttpServlet {

	private LectureDao lectureDao = new LectureDao();
	private AuditoriumDao auditoriumDao = new AuditoriumDao();
	private CourseDao courseDao = new CourseDao();
	private GroupDao groupDao = new GroupDao();
	private TeacherDao teacherDao = new TeacherDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Lecture lectureToSave = new Lecture();
		if (req.getParameter("id").contentEquals("")) {
			lectureToSave.setId(null);
		} else {
			lectureToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		lectureToSave.setAuditorium(auditoriumDao.findById(Integer.parseInt(req.getParameter("auditoriumId"))));
		lectureToSave.setCourse(courseDao.findById(Integer.parseInt(req.getParameter("courseId"))));
		lectureToSave.setGroup(groupDao.findById(Integer.parseInt(req.getParameter("groupId"))));
		lectureToSave.setTeacher(teacherDao.findById(Integer.parseInt(req.getParameter("teacherId"))));
		lectureToSave.setTime(LocalTime.parse(req.getParameter("time")));
		Lecture savedLecture = lectureDao.save(lectureToSave);
		req.setAttribute("lecture", savedLecture);
		req.getRequestDispatcher("/lecture/showSaved.jsp").forward(req, resp);
	}
}
