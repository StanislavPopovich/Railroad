<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Registration</h1>
<form:form method="POST" modelAttribute="userForm" >
    <form:input type="text" path="userName" placeholder="Username"
                autofocus="true"></form:input>
    <form:input type="password" path="password"  placeholder="Password"></form:input>
    <form:input type="password" path="confirmPassword"
                placeholder="Confirm your password"></form:input>
    <button type="submit">Submit</button>
</form:form>

</body>
</html>