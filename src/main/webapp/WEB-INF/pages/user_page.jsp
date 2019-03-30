<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div>
    <jsp:include page="header.jsp"/>
    <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
        <h1>This is user page</h1>
    </c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
    <h1>This is moderator page</h1>
    <p class=""> <a href="<c:url value='/railroad/moderator/all-stations'/>" class="log_reg_page">
        <spring:message code="all_stations"/> </a></p>
    <p class=""> <a href="<c:url value='/railroad/moderator/all-ways'/>" class="log_reg_page">
        All ways</a></p>
    <p class=""> <a href="<c:url value='/railroad/moderator/all-trains'/>" class="log_reg_page">
        All trains</a></p>
    <p class=""> <a href="<c:url value='/railroad/moderator/schedule'/>" class="log_reg_page">
        schedule page</a></p>
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <h1>This is admin page</h1>
    <p class=""> <a href="<c:url value='/railroad/admin/all-stations'/>" class="log_reg_page">
        <spring:message code="all_stations"/> </a></p>
    <p class=""> <a href="<c:url value='/railroad/admin/all-ways'/>" class="log_reg_page">
        All ways</a></p>
    <p class=""> <a href="<c:url value='/railroad/admin/all-trains'/>" class="log_reg_page">
        All trains</a></p>
    <p class=""> <a href="<c:url value='/railroad/admin/all-users'/>" class="log_reg_page">
        All users</a></p>
    <p class=""> <a href="<c:url value='/railroad/admin/schedule'/>" class="log_reg_page">
        schedule page</a></p>
</c:if>
    <form:form id="logoutForm" method="post" action="${contextPath}/logout"
               onclick="document.forms['logoutForm'].submit()"><button>Logout</button></form:form>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
