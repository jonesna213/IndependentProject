<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Bills" />
<c:if test="${user == null}" >
    <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
    <%@include file="head.jsp"%>
        <body>
            <div class="container">
                <%@include file="header.jsp"%>
                <div class="w-100 text-center">
                    <h3 class="text-decoration-underline mx-auto">Bills</h3>
                </div>
                <c:if test="${error != null}" >
                    <c:if test="${error == true}" >
                        <h4 class="text-danger">Failed to add bill, please try again</h4>
                        <p>${message}</p>
                    </c:if>
                    <c:if test="${error == false}" >
                        <h4 class="text-success">Bill was successfully added</h4>
                    </c:if>
                    <c:remove var="error" scope="session"/>
                    <c:remove var="message" scope="session"/>
                </c:if>
                <c:if test="${billDeleted == true}" >
                    <h4 class="py-3 text-success">Bill Successfully Deleted</h4>
                    <c:remove var="billDeleted" scope="session"/>
                </c:if>
                <div class="d-flex flex-row justify-content-between align-items-center py-3">
                    <c:if test="${user.getHousehold().getBills().size() != 0}" >
                        <form class="d-flex flex-row align-items-center" action="searchServlet" method="get">
                            <i class="bi bi-search fs-3"></i>
                            <input class="form-control mx-2" type="text" name="searchTerm" id="searchTerm" placeholder="Enter search term">
                            <select class="form-select me-2" name="searchType">
                                <option selected value="title">Title</option>
                                <option value="description">Description</option>
                                <option value="dueDate">Due Date</option>
                                <option value="amount">Amount</option>
                                <option value="frequency">Frequency</option>
                            </select>
                            <input class="d-none" type="text" name="returnType" value="bill">
                            <button type="submit" name="submit" class="btn btn-primary">Search</button>
                        </form>
                    </c:if>

                    <a class="btn btn-success rounded-pill p-2 px-3" href="addABill.jsp">Add a Bill</a>
                </div>
                <c:if test="${user.getHousehold().getBills().size() == 0}" >
                    <p>Your household currently does not have any bills. You can add one by clicking the "Add a Bill" button.</p>
                </c:if>
                <c:if test="${user.getHousehold().getBills().size() != 0}" >
                    <table class="table table-hover">
                        <c:if test="${search != true}" >
                            <thead>
                                <tr>
                                    <td>Title</td>
                                    <td>Description</td>
                                    <td>Due Date</td>
                                    <td>Amount</td>
                                    <td>Frequency</td>
                                    <td></td>
                                    <td>Delete</td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="bill" items="${user.getHousehold().getBills()}">
                            <tr>
                                <td>${bill.title}</td>
                                <td>${bill.description}</td>
                                <td>${bill.dueDate}</td>
                                <td>${bill.amount}</td>
                                <td>${bill.frequency}</td>
                                <c:if test="${user.permissions.equals('admin')}" >
                                    <td>
                                        <form action="displayEditPageServlet" method="post">
                                            <input class="d-none" type="text" id="billToEdit" name="billToEdit" value="${bill.id}">
                                            <input type="submit" value="Edit">
                                        </form>
                                    </td>
                                    <td>
                                        <form action="displayDeletePageServlet" method="post">
                                            <input class="d-none" type="text" id="billToDelete" name="billToDelete" value="${bill.id}">
                                            <button class="bg-transparent border-0" type="submit"><i class="bi bi-trash fs-3"></i></button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${search == true}" >
                            <c:if test="${results == null}" >
                                <p>No results found for your search.</p>
                            </c:if>
                            <c:if test="${results != null}" >
                                <thead>
                                <tr>
                                    <td>Title</td>
                                    <td>Description</td>
                                    <td>Due Date</td>
                                    <td>Amount</td>
                                    <td>Frequency</td>
                                    <td></td>
                                    <td>Delete</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="bill" items="${results}">
                                    <tr>
                                        <td>${bill.title}</td>
                                        <td>${bill.description}</td>
                                        <td>${bill.dueDate}</td>
                                        <td>${bill.amount}</td>
                                        <td>${bill.frequency}</td>
                                        <c:if test="${user.permissions.equals('admin')}" >
                                            <td>
                                                <form action="displayEditPageServlet" method="post">
                                                    <input class="d-none" type="text" id="billToEdit" name="billToEdit" value="${bill.id}">
                                                    <input type="submit" value="Edit">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="displayDeletePageServlet" method="post">
                                                    <input class="d-none" type="text" id="billToDelete" name="billToDelete" value="${bill.id}">
                                                    <button class="bg-transparent border-0" type="submit"><i class="bi bi-trash fs-3"></i></button>
                                                </form>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:remove var="search" scope="session"/>
                            <c:remove var="results" scope="session"/>
                        </c:if>
                        </tbody>
                    </table>
                </c:if>
                <%@include file="footer.jsp"%>
            </div>
            <%@include file="bootstrapFiles.jsp"%>
    </body>
</html>
