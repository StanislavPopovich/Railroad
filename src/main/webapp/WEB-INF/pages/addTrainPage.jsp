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
        <h2 class="h2">Add train</h2>
    <div class="container">
        <div class="select_stations">
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
        </div>
        <div id="wrapper_routes" class="wrapper_routes">
            <div class="items">
                 <div id="routes" class="routes"></div>
             </div>
            <div id="fields_button" class="fields_button">

            </div>
            <div id="buttons" class="admin_panel">

            </div>
        </div>




        </div>
        <div class="admin_panel">
            </div>
    </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/trainHandler.js"></script>
</body>
</html>
