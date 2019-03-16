<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add train</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add train</h1>
<div>
    <form:form method="POST" modelAttribute="train" action="/railroad/moderator/add_train/add_train_route">
        <form:label path="number"> Number </form:label>
        <form:input type="text" path="number"></form:input><br/>

        <form:label path="startStation"> Start station</form:label>
        <form:select type="text" path="startStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>

        <form:label path="endStation"> End station</form:label>
        <form:select type="text" path="endStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>

        <form:label path="seats"> Seats </form:label>
        <form:input path="seats"></form:input><br/>

        <button id="button" type="submit">Add route</button>
    </form:form>
</div>
</body>
</html>
