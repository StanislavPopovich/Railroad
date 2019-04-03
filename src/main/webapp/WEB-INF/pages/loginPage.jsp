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
<section class="main reg_main">
    <div class="container">
        <div class="reg_form">
            <h1><spring:message code="login_form"/></h1>
            <form:form cssClass="login_form" modelAttribute="user" method="POST" action="/railroad/login/process">
                <div class="wrapper_input">
                <form:label path="userName"><spring:message code="username"/></form:label>
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
                <button  type="submit"><spring:message code="submit"/></button>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/registration_valid_check.js"></script>
</body>
</html>
