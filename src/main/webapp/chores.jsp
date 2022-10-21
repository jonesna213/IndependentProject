<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Chores" />
<c:if test="${user == null}" >
    <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
    <%@include file="head.jsp"%>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="w-100 text-center">
                <h3 class="text-decoration-underline mx-auto">Chores</h3>
            </div>
            <c:if test="${error != null}" >
                <c:if test="${error == true}" >
                    <h4 class="text-danger">Failed to add chore, please try again</h4>
                    <p>${message}</p>
                </c:if>
                <c:if test="${error == false}" >
                    <h4 class="text-success">Chore was successfully added</h4>
                </c:if>
                <c:remove var="error" scope="session"/>
            </c:if>
            <c:if test="${choreDeleted == true}" >
                <h4 class="py-3 text-success">Chore Successfully Deleted</h4>
                <c:remove var="choreDeleted" scope="session"/>
            </c:if>

            <div class="d-flex flex-row justify-content-between align-items-center py-3">
                <c:if test="${user.getHousehold().getChores().size() != 0}" >
                    <form class="d-flex flex-row align-items-center" action="searchChoresServet" method="get">
                        <i class="bi bi-search fs-3"></i>
                        <input class="form-control mx-2" type="text" name="searchParam" id="searchParam" placeholder="Enter search term">
                        <select class="form-select me-2" name="searchBy">
                            <option selected value="name">Name</option>
                            <option value="description">Description</option>
                            <option value="completeBy">Complete by</option>
                            <option value="frequency">Frequency</option>
                        </select>
                        <button type="submit" name="submit" class="btn btn-primary">Search</button>
                    </form>
                </c:if>

                <a class="btn btn-success rounded-pill p-2 px-3" href="addAChore.jsp">Add a Chore</a>
            </div>
            <c:if test="${user.getHousehold().getChores().size() == 0}" >
                <p>Your household currently does not have any chores. You can add one by clicking the "Add a Chore" button.</p>
            </c:if>
            <c:if test="${user.getHousehold().getChores().size() != 0}" >
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Description</td>
                            <td>Complete by</td>
                            <td>Frequency</td>
                            <td></td>
                            <td>Delete</td>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="chore" items="${user.getHousehold().getChores()}">
                        <tr>
                            <td>${chore.name}</td>
                            <td>${chore.description}</td>
                            <td>${chore.completeBy}</td>
                            <td>${chore.frequency}</td>
                            <c:if test="${user.permissions.equals('admin')}" >
                                <td>
                                    <form action="displayEditChorePageServlet" method="post">
                                        <input class="d-none" type="text" id="choreToEdit" name="choreToEdit" value="${chore.id}">
                                        <input type="submit" value="Edit">
                                    </form>
                                </td>
                                <td>
                                    <form action="displayDeleteChorePageServlet" method="post">
                                        <input class="d-none" type="text" id="choreToDelete" name="choreToDelete" value="${chore.id}">
                                        <button class="bg-transparent border-0" type="submit"><i class="bi bi-trash fs-3"></i></button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <%@include file="footer.jsp"%>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
