package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.exception.NotFindStudentsException;
import am.itspace.studentlessonservlet1.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudents")
public class DeleteStudentsServlet extends HttpServlet {

    StudentsService studentsService = new StudentsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        if (studentsService.getById(id) == null) {
            throw new NotFindStudentsException("Student not found");
        } else {
            studentsService.delete(id);
        }

        resp.sendRedirect("students");
    }
}
