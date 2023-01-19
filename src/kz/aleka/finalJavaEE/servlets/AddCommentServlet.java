package kz.aleka.finalJavaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.aleka.finalJavaEE.db.DBManager;
import kz.aleka.finalJavaEE.model.Blog;
import kz.aleka.finalJavaEE.model.Comment;
import kz.aleka.finalJavaEE.model.User;

import java.io.IOException;

@WebServlet(value = "/addcomment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if(currentUser!=null){

            String commentText = req.getParameter("comment");
            Long blogId = Long.valueOf(req.getParameter("id"));
            Blog blog = DBManager.getBlog(blogId);

            if(blog!=null){
                Comment comment = new Comment();
                comment.setComment(commentText);
                comment.setUser(currentUser);
                comment.setBlog(blog);

                if(DBManager.addComment(comment)){
                    resp.sendRedirect("/blogdetails?id="+blogId);
                }
            }
        }else {
            resp.sendRedirect("/login");
        }

    }
}
