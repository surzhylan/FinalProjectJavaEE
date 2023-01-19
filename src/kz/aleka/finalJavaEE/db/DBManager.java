package kz.aleka.finalJavaEE.db;

import kz.aleka.finalJavaEE.model.Blog;
import kz.aleka.finalJavaEE.model.Comment;
import kz.aleka.finalJavaEE.model.User;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news",
                    "postgres", "0805");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addUser(User user){
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into users (email, password, full_name) values (?,?,?)");
            stmt.setString(1,user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3,user.getFullName());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows>0;
    }

    public static User getUser(String email){
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from users where email = ?");
            stmt.setString(1,email);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean addBlog(Blog blog){
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("" +
                    "insert into blogs (user_id, title, content, post_date) " +
                    "values (?, ?, ?, NOW())");
            stmt.setLong(1,blog.getUser().getId());
            stmt.setString(2,blog.getTitle());
            stmt.setString(3,blog.getContent());

            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows>0;
    }

    public static ArrayList<Blog> getAllBlogs(){
        ArrayList<Blog> blogs = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("" +
                    "select b.id, b.title, b.content, b.post_date, b.user_id, u.full_name, u.email " +
                    "from blogs b " +
                    "inner join users u on u.id = b.user_id " +
                    "order by b.post_date desc ");

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));

                blog.setUser(user);
                blogs.add(blog);

            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogs;
    }

    public static Blog getBlog(Long id){
        Blog blog = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("" +
                    "select  b.id, b.title, b.content, b.post_date, b.user_id, u.full_name, u.email " +
                    "from blogs b " +
                    "inner join users u on u.id = b.user_id " +
                    "where b.id = ?");

            stmt.setLong(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blog;
    }

    public static boolean addComment(Comment comment){
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("" +
                    "insert into comments (user_id, blog_id, comment, post_date) " +
                    "values (?, ?, ?, NOW())");

            stmt.setLong(1,comment.getUser().getId());
            stmt.setLong(2,comment.getBlog().getId());
            stmt.setString(3,comment.getComment());

            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows>0;
    }

    public static ArrayList<Comment> getAllComments(Long blogId){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("" +
                    "select c.id, c.user_id, c.blog_id, c.comment, c.post_date, u.full_name " +
                    "from comments c " +
                    "inner join users u on u.id = c.user_id " +
                    "where c.blog_id = ? " +
                    "order by c.post_date desc ");

            stmt.setLong(1,blogId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                comment.setUser(user);

                Blog blog = new Blog();
                blog.setId(resultSet.getLong("blog_id"));
                comment.setBlog(blog);

                comments.add(comment);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
