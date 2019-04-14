<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add trainEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
    <c:if test="${isUser}">
    <div class="container">
        <div class="form_add_train">
            <form id="start_station">
                <form:select cssClass="select" id="start" type="text" path="departStation">
                    <form:option value="0"><spring:message code="departStation"/></form:option>
                    <form:options items="${stations}"/>
                </form:select>
            </form>
            <form id="end_station">
                <form:select cssClass="select" id="end" type="text" path="arrivalStation">
                    <form:option value="0"><spring:message code="arrivalStation"/></form:option>
                </form:select>
            </form>
            <form:form method="POST" cssClass="select_route" modelAttribute="trainForm">
                <form:select cssClass="select" id="routes"  type="text" path="stations" >
                    <form:option value="0"><spring:message code="selectRoute"/></form:option>
                </form:select>

                <div class="wrapper_input">
                    <form:label path="number"> Number </form:label>
                    <form:input type="text" path="number"/>
                </div>
                <div class="wrapper_input">
                    <form:label path="seats"> Seats </form:label>
                    <form:input path="seats"/>
                </div>
                <button id="button" type="submit">Add train</button>
            </form:form>

        </div>
        <div class="admin_panel">
            <a href="<c:url value="/railroad/station/add"/>"><button>Add new station</button></a>
            <a href="<c:url value="/railroad/way/add"/>"> <button>Add new way</button></a>
        </div>
    </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
