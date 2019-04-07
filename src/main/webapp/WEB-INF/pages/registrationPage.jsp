<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main reg_main">
    <div class="container">
        <div class="modal_form">
            <h1><spring:message code="registration_form"/></h1>
            <c:if test="${exist}">
                <h2><spring:message code="exist"/></h2>
            </c:if>
            <form:form method="POST" modelAttribute="userForm" >
                <div class="wrapper_input">
                    <form:label path="userName"> <spring:message code="username"/> </form:label>
                    <form:input id="username" type="text" path="userName" autofocus="true"
                                onkeyup="javascript: checkUsername(); return false;"/>
                    <div class="error" id="error_name"><form:errors path="userName"/></div>
                </div>
                <div class="wrapper_input">
                    <form:label path="password"><spring:message code="password"/></form:label>
                    <form:input id="password" type="password" path="password"
                                onkeyup="javascript: checkPassword(); return false;"/>
                    <div class="error" id="error_password"><form:errors path="password"/></div>
                </div>
                <div class="wrapper_input">
                    <form:label path="confirmPassword"><spring:message code="confirmPassword"/></form:label>
                    <form:input id="passwordConfirm" type="password" path="confirmPassword"
                                onkeyup="javascript: checkPasswordConfirm(); return false;"/>
                    <div class="error" id="error_password_confirm"><form:errors path="confirmPassword"/></div>
                </div>
                <button id="button" type="submit" disabled="true"><spring:message code="submit"/></button>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/registration_valid_check.js"></script>
</body>
</html>