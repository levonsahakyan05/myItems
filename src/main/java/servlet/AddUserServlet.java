package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class AddUserServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/registerUser.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(userManager.getUserByEmail(email)!= null){
            req.setAttribute("msg","User already exists");
            req.getRequestDispatcher("/WEB-INF/registerUser.jsp").forward(req,resp);
        }else{
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userManager.Register(user);
            resp.sendRedirect("/login");
        }

    }


}
