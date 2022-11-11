<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Sign Up" />
<html lang="en">
    <%@include file="head.jsp"%>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="w-50 mx-auto">
                <c:if test="${success != null}" >
                    <c:if test="${success == true}" >
                        <h3 class="text-success">Account was successfully created, you can now <a href="signin.jsp">sign in</a> to access your account</h3>
                    </c:if>
                    <c:if test="${success == false}" >
                        <h3 class="text-danger">Failed to create account.
                            Please resubmit with the correct information.</h3>
                        <p>${message}</p>
                    </c:if>
                    <c:remove var="success" scope="session"/>
                </c:if>
                <h2 class="text-decoration-underline">Create an Account</h2>
                <p>When creating an account have the "head of household" create the account</p>
                <form action="createAccountServlet" method="post">
                    <p class="text-danger">ALL fields are Required</p>
                    <div class="form-group my-3">
                        <label for="householdName" class="fw-bold">Household Name</label>
                        <input type="text" class="form-control"
                               name="householdName" id="householdName" maxlength="40"
                               pattern="[a-zA-Z]{1,40}"
                               title="Household Name name is required, up to 40 characters"
                               required/>
                        <p>Make it easy on yourself for signing in and make it a single word name.</p>
                    </div>
                    <div class="form-group my-3">
                        <label for="fname" class="fw-bold">First Name</label>
                        <input type="text" class="form-control"
                               name="fname" id="fname" maxlength="40"
                               pattern="[a-zA-Z]{1,40}"
                               title="First name is required, up to 40 characters"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="lname" class="fw-bold">Last Name</label>
                        <input type="text" class="form-control"
                               name="lname" id="lname" maxlength="60"
                               pattern="[a-zA-Z]{1,60}"
                               title="Last name is required, up to 60 characters"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="email" class="fw-bold">Email</label>
                        <input type="email" class="form-control"
                               name="email" id="email"
                               pattern="\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}"
                               title="Email is required, enter in format - abc@gmail.com"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="uname" class="fw-bold">Personal Username</label>
                        <input type="text" class="form-control"
                               name="uname" id="uname" maxlength="60"
                               pattern="[a-zA-Z]{1,60}"
                               title="Username is required, up to 60 characters, no spaces"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="password" class="fw-bold">Password</label>
                        <input type="password" class="form-control"
                               name="password" id="password" maxlength="40"
                               title="password is required, up to 40 characters"
                               required aria-describedby="passwordHelpBlock"/>
                        <div id="passwordHelpBlock" class="form-text">
                            This will be the password that everyone in your household uses to log in.
                        </div>
                    </div>


                    <input class="btn btn-success" type="submit" value="Submit"/>
                </form>
            </div>
            <%@include file="footer.jsp"%>
        </div>
        <%@include file="bootstrapFiles.jsp"%>
    </body>
</html>
