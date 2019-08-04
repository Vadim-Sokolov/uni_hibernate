package servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import models.Group;

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet {

	private GroupDao groupDao = new GroupDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("groupId"));
		Group groupToDelete = groupDao.findById(id);
		groupDao.delete(groupToDelete);
		req.setAttribute("deletedGroup", groupToDelete);
		req.getRequestDispatcher("/group/showDeleted.jsp").forward(req, resp);
	}
}
