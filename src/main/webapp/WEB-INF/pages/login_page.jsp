<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <h1>Login</h1>
    <form:form cssClass="login_form" modelAttribute="user" method="POST" action="/railroad/login/process">
        <form:label path="userName">Username</form:label>
        <form:input type="text" cssClass="loginfiled" path="userName" autofocus="true"></form:input>
        <form:errors path="userName" cssClass="error"/>

        <form:label path="password">Password</form:label>
        <form:input type="password" cssClass="loginfiled" path="password"></form:input>
        <form:errors path="password" cssClass="error"/>
        <button  type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
