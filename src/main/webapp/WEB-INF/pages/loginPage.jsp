<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrap">
        <div class="wrap_modal_form">
            <div class="modal_form">
                <h1><spring:message code="loginForm"/></h1>
                <c:if test="${notExist}">
                    <div class="back_error">
                        <span><spring:message code="notExist"/></span>
                    </div>
                </c:if>
                <form:form cssClass="login_form" modelAttribute="user" method="POST" action="/railroad/login/process">
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
                    <button id="button" type="submit" disabled="true"><spring:message code="loginButton"/></button>
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
