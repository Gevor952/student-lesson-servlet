package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.exception.NotFindStudentsException;
import am.itspace.studentlessonservlet1.model.Lessons;
import am.itspace.studentlessonservlet1.model.Students;
import am.itspace.studentlessonservlet1.service.LessonsService;
import am.itspace.studentlessonservlet1.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editeStudents")
public class EditeStudentsServlet extends HttpServlet {

    StudentsService studentsService = new StudentsService();
    LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Students students = studentsService.getById(id);
        List<Lessons> lessons = lessonsService.getAllLessons();
        req.setAttribute("students", students);
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/editeStudents.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));
        Lessons lesson = lessonsService.getLessonById(Integer.parseInt(req.getParameter("lessons")));
        if(studentsService.getById(id) == null) {
            throw new NotFindStudentsException("Student not found");
        }else {
            studentsService.update(Students.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .email(req.getParameter("email"))
                    .age(age)
                    .lesson(lesson)
                    .build());
        }

        resp.sendRedirect("/students");
    }
}
