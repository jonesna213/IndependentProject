<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Delete Item" />
<c:if test="${user == null}" >
    <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
    <%@include file="head.jsp"%>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="w-50 mx-auto text-center">
                <c:if test="${choreToDelete != null}" >
                    <h3>Are you sure you want to delete the chore - "${choreToDelete.name}"?</h3>
                    <br>
                    <form action="deleteServlet" method="post">
                        <input class="btn btn-success mx-2 p-3" type="submit" name="choice" value="Delete Chore"/>
                        <input class="btn btn-danger mx-2 p-3" type="submit" name="choice" value="Dont Delete Chore"/>
                    </form>
                    <br>
                </c:if>
                <c:if test="${billToDelete != null}" >
                    <h3>Are you sure you want to delete the bill - "${billToDelete.title}"?</h3>
                    <br>
                    <form action="deleteServlet" method="post">
                        <input class="btn btn-success mx-2 p-3" type="submit" name="choice" value="Delete Bill"/>
                        <input class="btn btn-danger mx-2 p-3" type="submit" name="choice" value="Dont Delete Bill"/>
                    </form>
                    <br>
                </c:if>

            </div>
            <%@include file="footer.jsp"%>
        </div>
        <%@include file="bootstrapFiles.jsp"%>
    </body>
</html>
