package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.service.LessonsService;
import am.itspace.studentlessonservlet1.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;


@WebServlet("/editeLessons")
public class EditeLessonsServlet extends HttpServlet {

    LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Lessons lesson = lessonsService.getLessonById(id);
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("editeLessons.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String lName = req.getParameter("name");
        Time time = new Time(DateUtil.timeForDate(req.getParameter("time")).getTime());
        double price = Double.parseDouble(req.getParameter("price"));
        lessonsService.update(new Lessons(id, time, lName, price));
        resp.sendRedirect("lessons");
    }

}
