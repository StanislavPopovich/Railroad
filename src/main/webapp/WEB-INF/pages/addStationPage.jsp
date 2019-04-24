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
        <form:form method="POST" modelAttribute="way">
            <form:label path="firstStation"> First Station </form:label>
            <form:input id="name" type="text" path="firstStation" autofocus="true"/><br/>
            <form:select cssClass="select" type="text" path="secondStation">
                <form:option value="0">Second Station</form:option>
                <form:options items="${stations}"/>
            </form:select>
            <form:label path="distance"> Distance </form:label>
            <form:input type="text" path="distance"/><br/>
            <button id="button" type="submit">Add</button>
        </form:form>
        </c:if>
</section>

</body>
</html>
