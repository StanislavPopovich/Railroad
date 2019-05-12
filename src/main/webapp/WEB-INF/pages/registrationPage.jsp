<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrap">
        <div class="wrap_modal_form">
            <div class="modal_form">
                <h1><spring:message code="registrationForm"/></h1>
                <c:if test="${exist}">
                    <div class="back_error">
                        <span><spring:message code="exist"/></span>
                    </div>
                </c:if>
                <form:form method="POST" modelAttribute="userForm" >
                    <div class="wrapper_input">
                       <small><spring:message code="username"/></small>
                        <div class="wrapper_input_in">
                            <form:input id="username" type="text" path="userName" autofocus="true"
                                        onkeyup="checkUsername(); return false;"/>
                        </div>
                        <div class="error" id="error_name"><form:errors path="userName"/></div>
                    </div>
                    <div class="wrapper_input">
                        <small><spring:message code="password"/></small>
                        <div class="wrapper_input_in">
                            <form:input id="password" type="password" path="password"
                                        onkeyup="checkPassword(); return false;"/>
                        </div>
                        <div class="error" id="error_password"><form:errors path="password"/></div>
                    </div>
                    <div class="wrapper_input">
                        <small><spring:message code="confirmPassword"/></small>
                        <div class="wrapper_input_in">
                            <form:input id="passwordConfirm" type="password" path="confirmPassword"
                                        onkeyup="checkPasswordConfirm(); return false;"/>
                        </div>
                        <div class="error" id="error_password_confirm"><form:errors path="confirmPassword"/></div>
                    </div>
                    <button id="button" type="submit" disabled="true"><spring:message code="submit"/></button>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/registrationValidCheck.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
</body>
</html>