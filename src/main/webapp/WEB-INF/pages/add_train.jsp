<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add trainEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add trainEntity</h1>
<div>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <c:set var="role_path" value="/railroad/moderator/add-train/add-train-route"/>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <c:set var="role_path" value="/railroad/admin/add-train/add-train-route"/>
    </c:if>
    <form:form method="POST" modelAttribute="train" action="${role_path}">
        <form:label path="number"> Number </form:label>
        <form:input type="text" path="number"/><br/>

        <form:label path="stations"> Start station</form:label>
        <form:select type="text" path="stations">
            <form:option value="4" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>

        <form:label path="stations"> End station</form:label>
        <form:select type="text" path="stations">
            <form:option value="4" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>

        <form:label path="seats"> Seats </form:label>
        <form:input path="seats"/><br/>
        <button id="button" type="submit">Add route</button>
    </form:form>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <a href="<c:url value='/railroad/moderator/all-trains'/>"><button>to all trains page</button></a>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <a href="<c:url value='/railroad/admin/all-trains'/>"><button>to all trains page</button></a>
    </c:if>
</div>
</body>
</html>
