<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1>Registration</h1>
    <c:if test="${exist}">
        <h2><spring:message code="exist"/></h2><br/>
    </c:if>
    <form:form method="POST" modelAttribute="userForm" >
        <form:label path="userName"> <spring:message code="username"/> </form:label>
        <form:input type="text" path="userName" autofocus="true"></form:input><br/>
        <form:errors path="userName"/><br/>

        <form:label path="password"><spring:message code="password"/></form:label>
        <form:input type="password" path="password"></form:input><br/>
        <form:errors path="password"/><br/>

        <form:label path="password"><spring:message code="confirmPassword"/></form:label>
        <form:input type="password" path="confirmPassword"></form:input><br/>
        <button type="submit"><spring:message code="submit"/></button>
    </form:form>
</div>


</body>
</html>