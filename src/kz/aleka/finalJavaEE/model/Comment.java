package kz.aleka.finalJavaEE.model;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private Blog blog;
    private User user;
    private String comment;
    private Timestamp postDate;

    public Comment() {
    }

    public Comment(Long id, Blog blog, User user, String comment, Timestamp postDate) {
        this.id = id;
        this.blog = blog;
        this.user = user;
        this.comment = comment;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
