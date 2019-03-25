<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add stationEntity</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h1>Add stationEntity</h1>
<div>
    <form:form method="POST" modelAttribute="station">
    <form:label path="name"> Station </form:label>
    <form:input id="name" type="text" path="name" autofocus="true"/><br/>
    <button id="button" type="submit">Add</button>
    </form:form>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <a href="<c:url value='/railroad/moderator/all-stations'/>"><button>to all stations page</button></a>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <a href="<c:url value='/railroad/admin/all-stations'/>"><button>to all stations page</button></a>
    </c:if>
</div>
</body>
</html>
