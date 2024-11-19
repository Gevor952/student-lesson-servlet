package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.service.LessonsService;
import am.itspace.studentlessonservlet1.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addLessons")
public class AddLessonsServlet extends HttpServlet {

    LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLessons.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String duration = req.getParameter("time");
        if(lessonsService.getByLessonName(name, ((User) req.getSession().getAttribute("user")).getId()) == null) {
            double price = Double.parseDouble(req.getParameter("price"));
            lessonsService.add(Lessons.builder()
                    .lecturerName(name)
                    .duration(DateUtil.timeForDate(duration))
                    .price(price)
                    .user((User) req.getSession().getAttribute("user"))
                    .build());
            resp.sendRedirect("/lessons");
        }else {
            req.setAttribute("msg", "this lesson already exists");
            req.getRequestDispatcher("/WEB-INF/addLessons.jsp").forward(req, resp);

        }

    }
}
