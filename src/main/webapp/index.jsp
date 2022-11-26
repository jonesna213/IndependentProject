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
                <c:if test="${user != null}" >
                    <div class="col-10 col-lg-5 px-5 px-lg-0">
                        <h3>How to use Our House?</h3>
                        <p>If you would like to add other members to
                            your household visit the members tab.
                            If you would like to see/add chores
                            or bills visit the corresponding tabs.</p>
                    </div>
                </c:if>
                <c:if test="${user == null}" >
                    <a class="btn btn-success col-10 col-lg-5" id="signUpButton" href="signup.jsp">Sign Up Now</a>
                </c:if>
                <div class="col-12 col-lg-5 py-1 px-5">
                    <h3>Why use Our House?</h3>
                    <p>Our house is to help anyone really, but it is mainly aimed towards college students or friends that
                        are living together. The purpose is so that all the bills and chores that need to be done around the
                        house are all in one spot for everyone to see.</p>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
        <%@include file="bootstrapFiles.jsp"%>
    </body>
</html>
