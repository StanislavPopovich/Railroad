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
    <div class="container">
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
        <c:if test="${isUser}">
            <div class="wrapper_form">
                <div class="way_form">
                    <div class="wrapper_select">
                        <form:select cssClass="select" id="start" type="text" path="departStation" >
                            <form:option value="0"><spring:message code="departStation"/></form:option>
                            <form:options items="${stations}"/>
                        </form:select>
                    </div>
                    <div class="wrapper_select">
                        <form:select cssClass="select" id="end" type="text" path="arrivalStation">
                            <form:option value="0"><spring:message code="arrivalStation"/></form:option>
                        </form:select>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="distanceForm">Distance, km</form:label>
                        <form:input id="distance" type="text" path="distanceForm"/>
                    </div>
                    <div class="wrapper_btn">
                        <button id="add_way_form" class="btn btn_blue" type="submit">Add</button>
                    </div>
                </div>
                <form:form  method="POST" modelAttribute="wayForm">
                    <form:hidden id="way_first_station" path="firstStation"/>
                    <form:hidden id="way_second_station" path="secondStation"/>
                </form:form>
            </div>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/wayHandler.js"></script>
</body>
</html>
