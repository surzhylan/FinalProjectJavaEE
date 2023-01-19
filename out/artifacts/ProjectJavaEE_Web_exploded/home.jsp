<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.aleka.finalJavaEE.model.Blog" %>
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
            <div class="col-12 d-flex flex-wrap gap-4">
                <%
                    ArrayList<Blog> blogs = (ArrayList<Blog>) request.getAttribute("blogs");
                    if(blogs!=null){
                        for(Blog blog : blogs){
                %>
                    <div class="card" style="width: 18rem;">
                        <div class="card-header">
                            <h5 class="card-title"><%=blog.getTitle()%></h5>
                        </div>
                        <div class="card-body text-center">
                            <p class="card-text"><%=blog.getContent()%></p>
                            <a href="/blogdetails?id=<%=blog.getId()%>" class="btn btn-primary">Buy Now</a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted by <strong><%=blog.getUser().getFullName()%></strong> at <strong><%=blog.getPostDate()%></strong>
                        </div>
                    </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
