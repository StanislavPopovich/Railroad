<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
    <div class="header">
        <img src="/resources/img/logo.PNG" class="logo_header"/>
        <p class="header_words"> <spring:message code="header_words"/></p>
        <security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER')" var= "isUser"/>
        <c:if test="${not isUser}">
            <div class="log_reg_menu">
                <p class=""> <a href="/railroad/login" class="log_reg_page">
                    <spring:message code="login"/> </a></p>
                <p class=""> <a href="/railroad/registration" class="log_reg_page">
                    <spring:message code="register"/> </a></p>
            </div>

        </c:if>
    </div>
</body>
</html>
