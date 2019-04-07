<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Add stationEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">

    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
    <c:if test="${isUser}">
        <form:form method="POST" modelAttribute="station">
            <form:label path="name"> Station </form:label>
            <form:input id="name" type="text" path="name" autofocus="true"/><br/>
            <button id="button" type="submit">Add</button>
        </form:form>
        </c:if>
</section>

</body>
</html>
