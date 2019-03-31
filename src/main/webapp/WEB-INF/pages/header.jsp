<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<header class="header">
    <div class="container">
        <a class="logo" href="<c:url value='/railroad'/>">
            <img src="/resources/img/new_logo.svg"/>
        </a>

        <p class="header_words"><spring:message code="header_words"/></p>
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER')" var="isUser"/>
        <c:if test="${not isUser}">
            <div class="log_reg_menu">
                <a href="<c:url value='/railroad/login'/>">
                    <spring:message code="login"/>
                </a>
                <a href="<c:url value='/railroad/registration'/>">
                    <spring:message code="register"/>
                </a>
            </div>
        </c:if>
    </div>
</header>
