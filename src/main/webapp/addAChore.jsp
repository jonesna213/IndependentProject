<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add a Chore" />
<c:if test="${user == null}" >
    <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
<%@include file="head.jsp"%>
<body>
<div class="container">
    <%@include file="header.jsp"%>
    <div class="w-75 mx-auto">
        <h2 class="text-decoration-underline">Add a Chore</h2>
        <c:if test="${error != null}" >
            <c:if test="${error == true}" >
                <h3 class="text-danger">Failed to add chore, please try again</h3>
                <p>${message}</p>
            </c:if>
            <c:if test="${error == false}" >
                <h3 class="text-success">Chore successfully added</h3>
            </c:if>
            <c:remove var="error" scope="session"/>
        </c:if>
        <form action="addChoreServlet" method="post">
            <p class="text-danger">ALL fields except description are Required</p>
            <div class="form-group my-3">
                <label for="name" class="fw-bold">Name</label>
                <input type="text" class="form-control"
                       name="name" id="name"
                       required/>
            </div>
            <div class="form-group my-3">
                <label for="description" class="fw-bold">Description/Directions</label>
                <p><em>(Optional)</em></p>
                <textarea name="description" class="form-control" id="description"></textarea>
            </div>
            <div class="form-group my-3">
                <label for="completeBy" class="fw-bold">Complete by</label>
                <p><em>If this occurs weekly or monthly type the date accordingly (1st or Monday) otherwise format like mm/dd/yyyy</em></p>
                <input type="text" class="form-control"
                       name="completeBy" id="completeBy">
            </div>
            <fieldset class="my-3">
                <legend class="fw-bold">Frequency</legend>

                <div class="form-check">
                    <label for="once" class="form-check-label">
                        <input id="once" class="form-check-input" type="radio" name="frequency" value="once" checked required>
                        Once
                    </label>
                </div>

                <div class="form-check">
                    <label for="weekly" class="form-check-label">
                        <input id="weekly" class="form-check-input" type="radio" name="frequency" value="weekly">
                        Weekly
                    </label>
                </div>

                <div class="form-check">
                    <label for="monthly" class="form-check-label">
                        <input id="monthly" class="form-check-input" type="radio" name="frequency" value="monthly">
                        Monthly
                    </label>
                </div>
            </fieldset>
            <div class="mx-auto">
                <input class="btn btn-success" type="submit" value="Add Chore"/>
            </div>
        </form>
    </div>
    <%@include file="footer.jsp"%>
</div>
<%@include file="bootstrapFiles.jsp"%>
</body>
</html>
