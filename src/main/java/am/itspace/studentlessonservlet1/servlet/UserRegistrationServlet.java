package am.itspace.studentlessonservlet1.servlet;

import am.itspace.studentlessonservlet1.model.User;
import am.itspace.studentlessonservlet1.model.UserType;
import am.itspace.studentlessonservlet1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class UserRegistrationServlet extends HttpServlet {

    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserType userType = UserType.valueOf(req.getParameter("userType"));
        if(userService.grtByEmail(email) == null){
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(userType)
                    .build();
            userService.add(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }else {
            req.setAttribute("msg", "email is used");
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        }
    }
}
