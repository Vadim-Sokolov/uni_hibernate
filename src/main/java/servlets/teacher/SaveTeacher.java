package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;
import dao.TeacherDao;
import models.Teacher;

@WebServlet("/saveTeacher")
public class SaveTeacher extends HttpServlet {

	private TeacherDao teacherDao = new TeacherDao();
	private FacultyDao facultyDao = new FacultyDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Teacher teacherToSave = new Teacher();
		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		teacherToSave.setId(id);
		teacherToSave.setFaculty(facultyDao.findById(Integer.parseInt(req.getParameter("facultyId"))));
		teacherToSave.setFirstName(req.getParameter("firstName"));
		teacherToSave.setLastName(req.getParameter("lastName"));
		Teacher savedTeacher = teacherDao.save(teacherToSave);
		req.setAttribute("teacher", savedTeacher);
		req.getRequestDispatcher("/teacher/showSaved.jsp").forward(req, resp);
	}
}
