<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>User_page</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1>This is user page</h1>
    <form id="logoutForm" method="post" action="${contextPath}/logout">
    </form>
    <h2> <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
</div>
</body>
</html>
