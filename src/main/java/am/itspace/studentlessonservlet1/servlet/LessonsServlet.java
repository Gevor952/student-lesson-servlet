package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.model.UserType;
import am.itspace.studentlessonservlet1.service.LessonsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/lessons")
public class LessonsServlet extends HttpServlet {

    LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(((User)req.getSession().getAttribute("user")).getUserType() == UserType.ADMIN) {
            List<Lessons> lessons = lessonsService.getAllLessons();
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
        }else if(((User)req.getSession().getAttribute("user")).getUserType() == UserType.USER) {
            List<Lessons> lessons = lessonsService.getLessonsByUserId(((User)req.getSession().getAttribute("user")).getId());
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
        }
    }
}
