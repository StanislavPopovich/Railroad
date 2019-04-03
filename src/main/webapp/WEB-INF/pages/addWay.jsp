<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add wayEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Add stationEntity</h1>
<div>
    <form:form method="POST" modelAttribute="way">
        <form:label path="firstStation"> Start stationEntity </form:label>
        <form:select type="text" path="firstStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>
        <form:label path="secondStation"> End stationEntity</form:label>
        <form:select type="text" path="secondStation">
            <form:option value="0" label="Select"/>
            <form:options items="${stations}"/>
        </form:select><br/>
        <form:label path="distance"> Distance </form:label>
        <form:input path="distance"/><br/>
        <button id="button" type="submit">Add</button>
    </form:form>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <a href="<c:url value='/railroad/moderator/all-ways'/>"><button>to all ways page</button></a>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <a href="<c:url value='/railroad/admin/all-ways'/>"><button>to all ways page</button></a>
    </c:if>
</div>
</body>
</html>
