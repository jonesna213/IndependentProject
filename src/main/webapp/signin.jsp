<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Sign In" />
<html lang="en">
<%@include file="head.jsp"%>
<body>
<div class="container">
    <%@include file="header.jsp"%>
    <div class="w-50 mx-auto">
        <h2 class="text-decoration-underline">Sign In</h2>
        <c:if test="${error == true && user == null}" >
            <h3 class="text-danger">Error signing in, please enter the correct info and try again</h3>
            <c:remove var="error" scope="session"/>
        </c:if>
        <c:if test="${user != null}" >
            <h3 class="text-success">Hello, ${user.firstName} You have successfully signed in.</h3>
            <c:remove var="error" scope="session"/>
        </c:if>

        <form action="signInServlet" method="post">
            <div class="form-group my-3">
                <label for="householdName" class="fw-bold">Household Name</label>
                <input type="text" class="form-control"
                       name="householdName" id="householdName" maxlength="40"
                       pattern="[a-zA-Z]{1,40}"
                       title="Household Name name is required"
                       required/>
            </div>
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
                       title="password is required"
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
<%@include file="bootstrapFiles.jsp"%>
</body>
</html>
