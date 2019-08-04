package servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FacultyDao;
import dao.GroupDao;
import models.Group;

@WebServlet("/saveGroup")
public class SaveGroup extends HttpServlet {

	private GroupDao groupDao = new GroupDao();
	private FacultyDao facultyDao = new FacultyDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Group groupToSave = new Group();
		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		groupToSave.setId(id);
		groupToSave.setName(req.getParameter("name"));
		groupToSave.setFaculty(facultyDao.findById(Integer.parseInt(req.getParameter("facultyId"))));
		Group savedGroup = groupDao.save(groupToSave);
		req.setAttribute("group", savedGroup);
		req.getRequestDispatcher("/group/showSaved.jsp").forward(req, resp);
	}
}
