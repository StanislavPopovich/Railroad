<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add wayEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
    <c:if test="${isUser}">
        <form:form method="POST" modelAttribute="way">
            <div id="start_station">
                <form:select cssClass="select" id="start" type="text" path="firstStation">
                    <form:option value="0"><spring:message code="departStation"/></form:option>
                    <form:options items="${stations}"/>
                </form:select>
            </div>

                <form:select cssClass="select" id="end" type="text" path="secondStation">
                    <form:option value="0"><spring:message code="arrivalStation"/></form:option>
                </form:select>

        <form:label path="distance"> Distance </form:label>
            <form:input path="distance"/><br/>
        <button id="button" type="submit">Add</button>
        </form:form>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
