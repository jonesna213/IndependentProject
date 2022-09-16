<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Our House - Sign Up</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <c:import url="header.jsp"/>
            <div class="w-50 mx-auto">
                <c:if test="${success != null}" >
                    <c:if test="${success == true}" >
                        <h3 class="text-success">Account was successfully created, you can now login to access your account</h3>
                    </c:if>
                    <c:if test="${success == false}" >
                        <h3 class="text-danger">Failed to create account.
                            Please resubmit with the correct information.</h3>
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
                    </div>
                    <div class="form-group my-3">
                        <label for="fname" class="fw-bold">First Name</label>
                        <input type="text" class="form-control"
                               name="first" id="fname" maxlength="40"
                               pattern="[a-zA-Z]{1,40}"
                               title="First name is required, up to 40 characters"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="lname" class="fw-bold">Last Name</label>
                        <input type="text" class="form-control"
                               name="last" id="lname" maxlength="60"
                               pattern="[a-zA-Z]{1,60}"
                               title="Last name is required, up to 60 characters"
                               required/>
                    </div>
                    <div class="form-group my-3">
                        <label for="email" class="fw-bold">Email address</label>
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
            <c:import url="footer.jsp"/>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
