<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Home" />
<html lang="en">
    <%@include file="head.jsp"%>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="row">
                <div class="col-12 col-lg-5 py-1 px-5">
                    <h3>Welcome to Our House</h3>
                    <p>This application is so that everyone in your household can have all the bills and chores all in one
                        place for everyone to see. You can easily create an account and start setting up your household now.</p>
                </div>
                <div class="col-12"></div>
                <div class="col-12 col-lg-6"></div>
                <a class="btn btn-success col-10 col-lg-5" id="signUpButton" href="signup.jsp">Sign Up Now</a>
                <div class="col-12 col-lg-5 py-1 px-5">
                    <h3>Why use Our House?</h3>
                    <p>Our house is to help anyone really, but it is mainly aimed towards college students or friends that
                        are living together. The purpose is so that all the bills and chores that need to be done around the
                        house are all in one spot for everyone to see.</p>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
