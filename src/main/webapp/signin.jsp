<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Sign In" />
<html lang="en">
<%@include file="head.jsp"%>
<body>
<div class="container">
    <%@include file="header.jsp"%>
    <div class="w-50 mx-auto">
        <h2 class="text-decoration-underline">Sign In</h2>
        <form action="signInServlet" method="post">
            <div class="form-group my-3">
                <label for="uname" class="fw-bold">Personal Username</label>
                <input type="text" class="form-control"
                       name="uname" id="uname" maxlength="60"
                       pattern="[a-zA-Z]{1,60}"
                       title="Username is required"
                       required/>
            </div>
            <div class="form-group my-3">
                <label for="password" class="fw-bold">Password</label>
                <input type="password" class="form-control"
                       name="password" id="password" maxlength="40"
                       title="password is required="
                       required/>
            </div>
            <input class="btn btn-success" type="submit" value="Submit"/>
            <br><br>
            <h4>OR</h4>
            <br>
            <a class="btn btn-primary p-3" href="signup.jsp">Create An Account</a>
        </form>
    </div>
    <%@include file="footer.jsp"%>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
