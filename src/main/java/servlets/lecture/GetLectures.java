package servlets.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LectureDao;

@WebServlet("/showAllLectures")
public class GetLectures extends HttpServlet {

	private LectureDao lectureDao = new LectureDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lectures", lectureDao.findAll());
		req.getRequestDispatcher("/lecture/showAllLectures.jsp").forward(req, resp);
	}
}
