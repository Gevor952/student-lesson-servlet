package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.Students;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.service.LessonsService;
import am.itspace.studentlessonservlet1.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addStudents")
public class AddStudentsServlet extends HttpServlet {

    LessonsService lessonsService = new LessonsService();
    StudentsService studentsService = new StudentsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lessons> lessons = lessonsService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudents.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("lessons"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Lessons lessons =lessonsService.getLessonById(lessonId);
        if(studentsService.getAllByEmail(email, ((User)req.getSession().getAttribute("user")).getId())) {
            studentsService.add(Students.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .lesson(lessons)
                    .user((User) req.getSession().getAttribute("user"))
                    .build());
            resp.sendRedirect("/students");
        } else {
            List<Lessons> lessons1 = lessonsService.getAllLessons();
            req.setAttribute("lessons", lessons1);
            req.setAttribute("msg", "This email already exists");
            req.getRequestDispatcher("/WEB-INF/addStudents.jsp").forward(req, resp);
        }

    }
}
