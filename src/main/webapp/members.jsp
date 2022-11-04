<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Members" />
<c:if test="${user == null}" >
    <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
    <%@include file="head.jsp"%>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="d-flex flex-row">
                <div class="mx-auto w-100 px-3">
                    <h3 class="text-decoration-underline pb-3">Members of ${user.getHousehold().getHouseholdName()}</h3>
                    <c:if test="${memberDeleted == true}" >
                        <h4 class="py-3 text-success">Member Successfully Deleted</h4>
                        <c:remove var="memberDeleted" scope="session"/>
                    </c:if>
                    <c:if test="${memberToEdit != null}" >
                        <c:remove var="memberToEdit" scope="session"/>
                    </c:if>
                    <table class="table table-hover border">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                                <th>Permissions</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="member" items="${user.getHousehold().getMembers()}">
                            <tr>
                                <td>${member.firstName}</td>
                                <td>${member.lastName}</td>
                                <td>${member.username}</td>
                                <td>${member.permissions}</td>
                                <c:if test="${user.permissions.equals('admin')}" >
                                    <td>
                                        <form action="displayEditPageServlet" method="post">
                                            <input class="d-none" type="text" id="memberToEdit" name="memberToEdit" value="${member.id}">
                                            <input type="submit" value="Edit">
                                        </form>
                                    </td>
                                </c:if>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:if test="${user.permissions.equals('admin')}" >
                    <div class="mx-auto w-100 px-3">
                        <c:if test="${error != null}" >
                            <c:if test="${error == true}" >
                                <h3 class="text-danger">Failed to add member, please try again</h3>
                                <p>${message}</p>
                            </c:if>
                            <c:if test="${error == false}" >
                                <h3 class="text-success">Member was successfully added</h3>
                            </c:if>
                            <c:remove var="error" scope="session"/>
                        </c:if>
                        <h3 class="text-decoration-underline">Add a member</h3>
                        <form action="newMemberServlet" method="post">
                            <p class="text-danger">ALL fields except email are Required</p>
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
                                <label for="email" class="fw-bold">Email (Optional)</label>
                                <input type="email" class="form-control"
                                       name="email" id="email"
                                       pattern="\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}"
                                       title="Enter in format - abc@gmail.com"/>
                            </div>
                            <div class="form-group my-3">
                                <label for="uname" class="fw-bold">Personal Username</label>
                                <input type="text" class="form-control"
                                       name="uname" id="uname" maxlength="60"
                                       pattern="[a-zA-Z]{1,60}"
                                       title="Username is required, up to 60 characters, no spaces"
                                       required/>
                            </div>
                            <fieldset class="my-3">
                                <legend class="fw-bold">Permissions</legend>

                                <div class="form-check">
                                    <label for="admin" class="form-check-label">
                                        <input id="admin" class="form-check-input" type="radio" name="perms" value="admin" checked required>
                                        Same permissions as head of household
                                    </label>
                                </div>

                                <div class="form-check">
                                    <label for="user" class="form-check-label">
                                        <input id="user" class="form-check-input" type="radio" name="perms" value="user">
                                        Can only view/mark complete
                                    </label>
                                </div>
                            </fieldset>
                            <p class="text-decoration-underline">All members passwords are the same one that the head of household created</p>
                            <input class="btn btn-success" type="submit" value="Add Member"/>
                        </form>
                    </div>
                </c:if>

            </div>
            <%@include file="footer.jsp"%>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
