package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.exception.NotFindLessonsException;
import am.itspace.studentlessonservlet1.service.LessonsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/deleteLessons")
public class DeleteLessonsServlet extends HttpServlet {

    LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if(lessonsService.getLessonById(id) == null) {
            throw new NotFindLessonsException("id is not found");
        }
        else {
            lessonsService.delete(id);
        }

        resp.sendRedirect("/");
    }
}
