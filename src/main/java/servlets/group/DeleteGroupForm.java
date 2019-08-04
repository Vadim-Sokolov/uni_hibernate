package servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;

@WebServlet("/deleteGroupForm")
public class DeleteGroupForm extends HttpServlet {

	private GroupDao groupDao = new GroupDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("groups", groupDao.findAll());
		req.getRequestDispatcher("/group/deleteGroup.jsp").forward(req, resp);
	}
}
