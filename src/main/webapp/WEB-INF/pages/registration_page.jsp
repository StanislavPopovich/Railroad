<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1><spring:message code="registration_form"/></h1>
    <c:if test="${exist}">
        <h2><spring:message code="exist"/></h2><br/>
    </c:if>
    <form:form method="POST" modelAttribute="userForm" >
        <form:label path="userName"> <spring:message code="username"/> </form:label>
        <form:input id="username" type="text" path="userName" autofocus="true"
                    onkeyup="javascript: checkUsername(); return false;"></form:input><br/>
        <form:errors path="userName"/><br/>

        <form:label path="password"><spring:message code="password"/></form:label>
        <form:input id="password" type="password" path="password"
                    onkeyup="javascript: checkPassword(); return false;"></form:input><br/>
        <form:errors path="password"/><br/>

        <form:label path="confirmPassword"><spring:message code="confirmPassword"/></form:label>
        <form:input id="passwordConfirm" type="password" path="confirmPassword"
                    onkeyup="javascript: checkPasswordConfirm(); return false;"></form:input><br/>
        <button id="button" type="submit" disabled="true"><spring:message code="submit"/></button>
    </form:form>
</div>
<script type="text/javascript" src="/resources/js/registration_valid_check.js"></script>

</body>
</html>