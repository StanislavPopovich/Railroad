<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Moderator page</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1>This is moderator page</h1>
    <p class=""> <a href="/railroad/moderator/all_stations" class="log_reg_page">
        <spring:message code="all_stations"/> </a></p>
    <form id="logoutForm" method="post" action="${contextPath}/logout">
    </form>
    <h2> <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
</div>
</body>
</html>
