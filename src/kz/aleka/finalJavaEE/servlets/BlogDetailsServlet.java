package kz.aleka.finalJavaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.aleka.finalJavaEE.db.DBManager;
import kz.aleka.finalJavaEE.model.Blog;
import kz.aleka.finalJavaEE.model.Comment;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/blogdetails")
public class BlogDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long blogId = Long.valueOf(req.getParameter("id"));
        Blog blog = DBManager.getBlog(blogId);
        req.setAttribute("blog",blog);

        if(blog!=null){
            ArrayList<Comment> comments = DBManager.getAllComments(blogId);
            req.setAttribute("comments",comments);
        }
        req.getRequestDispatcher("/blogdetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
