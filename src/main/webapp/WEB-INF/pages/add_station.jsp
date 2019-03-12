<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add station</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h1>Add station</h1>
<div>
    <form:form method="POST" modelAttribute="station">
    <form:label path="name"> Station </form:label>
    <form:input id="name" type="text" path="name" autofocus="true"></form:input><br/>
    <button id="button" type="submit">Add</button>
    </form:form>
</div>
</body>
</html>
