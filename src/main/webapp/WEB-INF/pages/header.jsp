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
    <div class="container">
        <a class="logo" href="<c:url value='/railroad'/>">
            <img src="/resources/img/new_logo.svg"/>
        </a>

        <p class="header_words"><spring:message code="header_words"/></p>
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER')" var="isUser"/>
        <c:if test="${isUser}">
            <div class="log_reg_menu">
                <a href="<c:url value='/railroad/user'/>" class="log_reg_page">${pageContext.request.remoteUser}</a>
                <form:form cssClass="logout" method="post" action="${contextPath}/logout"
                           onclick="document.forms['logoutForm'].submit()">
                    <button><spring:message code="logoutButton"/></button>
                </form:form>
            </div>

            <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
                <div class="main_menu">
                    <ul class="menu">
                        <li><a href="<c:url value='/railroad/user/schedule'/>" class="log_reg_page">
                            <spring:message code="scheduler"/></a></li>
                        <li><a href="<c:url value='/railroad/ticket/all'/>" class="log_reg_page">
                            <spring:message code="all_orders"/></a></li>
                        <li><a href="<c:url value='/railroad/user/passenger/all'/>" class="log_reg_page">
                            <spring:message code="all_passengers"/></a></li>
                        <li><a href="<c:url value='/railroad'/>" class="log_reg_page">
                            <spring:message code="find_train"/></a></li>
                    </ul>
                    </ul>
                </div>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
                <div class="main_menu">
                    <ul class="menu">
                        <li><a href="<c:url value='/railroad/trains'/>" class="log_reg_page">
                            <spring:message code="all_trains"/></a></li>
                        <li><a href="<c:url value='/railroad/user/schedule'/>" class="log_reg_page">
                            <spring:message code="all_schedules"/></a></li>
                        <%--<li><a href="<c:url value='/railroad/'/>" class="log_reg_page">
                            <spring:message code="all_tickets"/></a></li>
                        <li><a href="<c:url value='/railroad/user/all-passengers'/>" class="log_reg_page">
                            <spring:message code="all_passengers"/></a></li>--%>
                    </ul>
                </div>

            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <div class="main_menu">
                     <ul class="menu">
                         <li><a href="<c:url value='/railroad/train/all'/>" class="log_reg_page">
                             <spring:message code="all_orders"/></a></li>
                        <li><a href="<c:url value='/railroad/user'/>" class="log_reg_page">
                            <spring:message code="all_users"/></a></li>
                         <%--<li><a href="<c:url value='/railroad/user/schedule'/>" class="log_reg_page">
                             <spring:message code="all_schedules"/></a></li>
                         <li><a href="<c:url value='/railroad/user/all-tickets'/>" class="log_reg_page">
                             <spring:message code="all_tickets"/></a></li>
                         <li><a href="<c:url value='/railroad/user/all-passengers'/>" class="log_reg_page">
                             <spring:message code="all_passengers"/></a></li>--%>
                     </ul>
                </div>
            </c:if>
        </c:if>
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
