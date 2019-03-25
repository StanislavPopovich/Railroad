<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1><spring:message code="login_form"/></h1>
    <form:form cssClass="login_form" modelAttribute="user" method="POST" action="/railroad/login/process">
        <form:label path="userName"><spring:message code="username"/></form:label>
        <form:input type="text" cssClass="loginfiled" path="userName" autofocus="true"/>
        <form:errors path="userName" cssClass="error"/><br/>

        <form:label path="password"><spring:message code="password"/></form:label>
        <form:input type="password" cssClass="loginfiled" path="password"/>
        <form:errors path="password" cssClass="error"/><br/>
        <button  type="submit">Submit</button><br/>
    </form:form>
    <div>
        <a href="<c:url value="/railroad"/>"><button class="button"><spring:message code="start_page"/></button></a>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
