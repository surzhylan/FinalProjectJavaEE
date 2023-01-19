package kz.aleka.finalJavaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.aleka.finalJavaEE.db.DBManager;
import kz.aleka.finalJavaEE.model.Blog;
import kz.aleka.finalJavaEE.model.User;

import java.io.IOException;

@WebServlet(value = "/addblog")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            req.getRequestDispatcher("/addblog.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if(currentUser!=null){

            String title = req.getParameter("title");
            String content = req.getParameter("content");

            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(currentUser);

            DBManager.addBlog(blog);

            resp.sendRedirect("/addblog");
        }else {
            resp.sendRedirect("/login");
        }

    }
}
