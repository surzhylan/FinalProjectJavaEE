<%--
  Created by IntelliJ IDEA.
  User: aliyashakhuali
  Date: 17.01.2023
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
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
                    String emailError = request.getParameter("emailerror");
                    if(emailError!=null){
                %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        User Email is busy!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <%
                    }
                %>

                <%
                    String passwordError = request.getParameter("passworderror");
                    if(passwordError!=null){
                %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Passwords are not the same!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>

                <%
                    String success = request.getParameter("success");
                    if(success!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    User created successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                    }
                %>
                <form action="/register" method="post">
                    <div class="row">
                        <div class="col-12">
                            <label>Email:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="email" class="form-control" placeholder="Insert Email:" name="email">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Password:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="password" class="form-control" placeholder="Insert Password:" name="password">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Retype Password:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="password" class="form-control" placeholder="Repeat Password:" name="re_password">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>Full Name:</label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <input type="text" class="form-control" placeholder="Insert Full Name:" name="full_name">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button class="btn btn-success">Sign Up</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
