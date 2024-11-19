package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.Students;
import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.model.UserType;
import am.itspace.studentlessonservlet1.service.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    StudentsService studentsService = new StudentsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(((User)req.getSession().getAttribute("user")).getUserType() == UserType.ADMIN){
            List<Students> students = studentsService.getAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
        }else if(((User)req.getSession().getAttribute("user")).getUserType() == UserType.USER){
            List<Students> students = studentsService.getAllByUserId(((User)req.getSession().getAttribute("user")).getId());
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
        }

    }
}
