<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="POST" modelAttribute="schedule">
    <form:label path="trainNumber"> Train number </form:label>
    <form:input id="number" type="number" path="trainNumber" autofocus="true"/><br/>
    <form:label path="stationName"> Station </form:label>
    <form:input id="station" type="text" path="stationName" autofocus="true"/><br/>
    <form:label path="data"> Station </form:label>
    <form:input id="station" type="date" path="data" autofocus="true"/><br/>
    <button id="button" type="submit">Add</button>
</form:form>
<c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
    <a href="<c:url value='/railroad/moderator/schedule'/>"><button>to schedule page</button></a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <a href="<c:url value='/railroad/admin/schedule'/>"><button>to schedule page</button></a>
</c:if>
</body>
</html>
