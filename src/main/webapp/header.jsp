<header class="d-flex flex-row justify-content-between align-items-center">
    <a href="index.jsp" class="text-dark"><i class="bi bi-house-door-fill" style="font-size: 3rem;"></i></a>
    <h1>Our House</h1>
    <c:if test="${user != null}" >
        <p class="text-dark small d-inline"><u>Hello, ${user.firstName}</u></p>
    </c:if>
    <c:if test="${user == null}" >
        <a href="signin.jsp" class="text-dark small">Sign In/Sign Up</a>
    </c:if>
</header>
<nav class="nav nav-pills justify-content-between">
    <a href="index.jsp" class="nav-link text-dark">Home</a>
    <c:if test="${user != null}" >
        <a href="members.jsp" class="nav-link text-dark">Members</a>
        <a href="chores.jsp" class="nav-link text-dark">Chores</a>
        <a href="bills.jsp" class="nav-link text-dark">Bills</a>
        <a href="signout.jsp" class="nav-link text-light bg-secondary">Sign Out</a>
    </c:if>

</nav>
<hr>
