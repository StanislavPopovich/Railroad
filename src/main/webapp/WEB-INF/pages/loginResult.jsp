<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>LoginResult</title>
</head>
<body>
<c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
    <meta http-equiv="refresh" content="1;http://localhost:8080/railroad/user" />
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
    <meta http-equiv="refresh" content="1;http://localhost:8080/railroad/user-moderator" />
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <meta http-equiv="refresh" content="1;http://localhost:8080/railroad/user-admin" />
</c:if>
</body>
</html>
