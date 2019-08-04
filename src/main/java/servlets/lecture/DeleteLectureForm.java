package servlets.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LectureDao;

@WebServlet("/deleteLectureForm")
public class DeleteLectureForm extends HttpServlet {

	private LectureDao lectureDao = new LectureDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lectures", lectureDao.findAll());
		req.getRequestDispatcher("/lecture/deleteLecture.jsp").forward(req, resp);
	}
}
