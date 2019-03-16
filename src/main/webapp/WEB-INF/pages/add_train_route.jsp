<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add route</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add route to train</h1>
<div>
    <form:form method="POST" modelAttribute="train" action="/railroad/moderator/add_train">
        <form:hidden path="number"/>
        <form:hidden path="startStation"/>
        <form:hidden path="endStation"/>
        <form:hidden path="seats"/>
        <form:label path="stations"> Route </form:label>
        <form:select type="text" path="stations">
            <form:option value="0" label="Select"/>
            <form:options items="${routes}"/>
        </form:select><br/>
        <button id="button" type="submit">Add</button>
    </form:form>
</div>
</body>
</html>
