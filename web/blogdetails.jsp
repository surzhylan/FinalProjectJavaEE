<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.aleka.finalJavaEE.model.Blog" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="kz.aleka.finalJavaEE.model.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
    <%@include file="navbar.jsp"%>

    <div class="container">
        <div class="row mt-3">
            <div class="col-6 mx-auto">
                <%
                    Blog blog = (Blog) request.getAttribute("blog");
                    if(blog!=null){
                %>
                    <div class="card text-center">
                        <div class="card-header">
                            <h5 class="card-title"><%=blog.getTitle()%></h5>
                        </div>
                        <div class="card-body text-center">
                            <p class="card-text"><%=blog.getContent()%></p>
                            <a href="#" class="btn btn-primary">Buy Now</a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted by <strong><%=blog.getUser().getFullName()%></strong> at <strong><%=blog.getPostDate()%></strong>
                        </div>
                    </div>

                    <div class="row mt-4 mb-4">
                        <div class="col-12">
                            <%
                                if(currentUser!=null){
                            %>
                                <form action="/addcomment" method="post">
                                    <input type="hidden" name="id" value="<%=blog.getId()%>">
                                    <div class="mb-3">
                                        <textarea class="form-control" name="comment"></textarea>
                                        <div class="form-text">Write your comment</div>
                                    </div>
                                    <button type="submit" class="btn btn-success">Submit Comment</button>
                                </form>
                            <%
                                }
                            %>

                            <div class="list-group">
                                <%
                                    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                                    if(comments!=null){
                                        for(Comment comment : comments){
                                %>
                                    <a href="JavaScript:void(0)" class="list-group-item list-group-item-action mt-3">
                                        <div class="d-flex w-100 justify-content-between">
                                            <h5 class="mb-1"><%=comment.getUser().getFullName()%></h5>
                                            <small><%=comment.getPostDate()%></small>
                                        </div>
                                        <p class="mb-1"><%=comment.getComment()%></p>
                                    </a>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                <%

                    }
                %>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
