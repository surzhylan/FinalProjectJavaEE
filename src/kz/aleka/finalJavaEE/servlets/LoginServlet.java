package kz.aleka.finalJavaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.aleka.finalJavaEE.db.DBManager;
import kz.aleka.finalJavaEE.model.User;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/login?emailerror";

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = DBManager.getUser(email);

        if(user!=null){
            redirect = "/login?passworderror";

            if(user.getPassword().equals(password)){
                req.getSession().setAttribute("currentUser",user);
                redirect = "/profile";
            }
        }
        resp.sendRedirect(redirect);
    }
}
