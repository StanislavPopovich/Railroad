<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1>Log in</h1>
    <form:form modelAttribute="user" method="POST" action="/railroad/login/process">
        <form:input type="text" path="userName" placeholder="Username"
                    autofocus="true"></form:input>
        <form:input type="password" path="password"  placeholder="Password"></form:input>
        <button  type="submit">Submit</button>
    </form:form>
</body>
</html>
