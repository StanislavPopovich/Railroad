<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add route</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add route to trainEntity</h1>
<div>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <c:set var="role_path" value="/railroad/moderator/add-train"/>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <c:set var="role_path" value="/railroad/admin/add-train"/>
    </c:if>
    <form:form method="POST" modelAttribute="train" action="${role_path}">
        <form:hidden path="number"/>
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
