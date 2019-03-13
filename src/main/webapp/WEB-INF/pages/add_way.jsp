<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add way</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add station</h1>
<div>
    <form:form method="POST" modelAttribute="way">
        <form:label path="firstStation"> Start station </form:label>
        <form:select type="text" path="firstStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>
        <form:label path="secondStation"> End station</form:label>
        <form:select type="text" path="secondStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>
        <form:label path="distance"> Distance </form:label>
        <form:input path="distance"></form:input><br/>
        <button id="button" type="submit">Add</button>
    </form:form>
</div>
</body>
</html>
