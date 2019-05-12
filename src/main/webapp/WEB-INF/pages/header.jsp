<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<header class="header">
    <div class="wrap wrapper-flex">
        <a class="logo" href="<c:url value='/railroad'/>">
            <img src="/resources/img/new_logo.svg"/>
            <p><spring:message code="headerWords"/></p>
        </a>
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER')" var="isUser"/>
        <c:if test="${isUser}">
            <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
                <ul class="header_menu">

                    <li><a href="<c:url value='/railroad/user/schedule-view'/>" class="log_reg_page">
                        <spring:message code="schedule"/></a></li>
                    <li><a href="<c:url value='/railroad/user'/>" class="log_reg_page">
                        <spring:message code="allOrders"/></a></li>
                    <li><a href="<c:url value='/railroad/user/passenger/all'/>" class="log_reg_page">
                        <spring:message code="allPassengers"/></a></li>
                    <li><a href="<c:url value='/railroad'/>" class="log_reg_page">
                        <spring:message code="findTrain"/></a></li>

                </ul>
            </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <ul class="header_menu">
                <li><a href="<c:url value='/railroad/schedule'/>" class="log_reg_page">
                    <spring:message code="schedule"/></a></li>
                <li><a href="<c:url value='/railroad/train/all'/>" class="log_reg_page">
                <spring:message code="trains"/></a></li>
                <li><a href="<c:url value='/railroad/train/passengers'/>" class="log_reg_page">
                    <spring:message code="passengers"/></a></li>

            </ul>

        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">

        </c:if>
        <div class="reg_menu">
            <a href="<c:url value='/railroad/user'/>" class="log_reg_page">${pageContext.request.remoteUser}</a>
            <form:form cssClass="logout" method="post" action="${contextPath}/logout"
                       onclick="document.forms['logoutForm'].submit()">
                <button><spring:message code="logoutButton"/></button>
            </form:form>
        </div>
        </c:if>
        <c:if test="${not isUser}">
            <div class="reg_menu">
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
