<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Delete Chore" />
<c:if test="${user == null}" >
  <c:redirect url = "index.jsp"/>
</c:if>
<html lang="en">
<%@include file="head.jsp"%>
<body>
<div class="container">
  <%@include file="header.jsp"%>
  <div class="w-50 mx-auto text-center">
    <h3>Are you sure you want to delete the chore - "${choreToDelete.name}"?</h3>
    <br>
    <form action="deleteChoreServlet" method="post">
      <input class="btn btn-success mx-2 p-3" type="submit" name="choice" value="Delete Chore"/>
      <input class="btn btn-danger mx-2 p-3" type="submit" name="choice" value="Dont Delete Chore"/>
    </form>
    <br>
  </div>
  <%@include file="footer.jsp"%>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
