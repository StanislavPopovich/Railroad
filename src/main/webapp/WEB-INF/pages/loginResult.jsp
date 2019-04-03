<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>LoginResult</title>
</head>
<body>
<c:if test="${not empty pageContext.request.userPrincipal}">
    <meta http-equiv="refresh" content="1;http://localhost:8080/railroad/user" />
</c:if>
</body>
</html>
