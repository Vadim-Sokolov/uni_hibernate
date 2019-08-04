package servlets.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;
import models.Faculty;

@WebServlet("/deleteFaculty")
public class DeleteFaculty extends HttpServlet {

	private FacultyDao facultyDao = new FacultyDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("facultyId"));
		Faculty facultyToDelete = facultyDao.findById(id);
		facultyDao.delete(facultyToDelete);
		req.setAttribute("deletedFaculty", facultyToDelete);
		req.getRequestDispatcher("/faculty/showDeleted.jsp").forward(req, resp);
	}
}
