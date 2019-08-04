package servlets.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LectureDao;
import models.Lecture;

@WebServlet("/deleteLecture")
public class DeleteLecture extends HttpServlet {

	private LectureDao lectureDao = new LectureDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("lectureId"));
		Lecture lectureToDelete = lectureDao.findById(id);
		lectureDao.delete(lectureToDelete);
		req.setAttribute("deletedLecture", lectureToDelete);
		req.getRequestDispatcher("/lecture/showDeleted.jsp").forward(req, resp);
	}
}