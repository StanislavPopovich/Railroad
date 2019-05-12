<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add way</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrap">
        <div class="wrap_modal_form">
            <div class="modal_form modal_type_two">
                <h1><spring:message code="addNewWay"/></h1>
                <c:if test="${exist}">
                    <div class="back_error">
                        <spring:message code="existWay"/>
                    </div>
                </c:if>
                <c:if test="${incorrectSelect}">
                    <div class="back_error">
                        <spring:message code="selectStationError"/>
                    </div>
                </c:if>
                <form:form method="POST" modelAttribute="wayForm">
                    <div class="wrapper_dropdown">
                        <small><spring:message code="from"/></small>
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                            <input id="firstStation" class="js-dropdown_value dropdownCheck" type="text"
                                   name="firstStation">
                            <div class="error"><form:errors path="firstStation"/></div>
                            <ul id="from_stations"></ul>
                        </div>
                    </div>
                    <div class="wrapper_dropdown">
                        <small><spring:message code="to"/></small>
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                            <input id="secondStation" class="js-dropdown_value dropdownCheck" type="text"
                                   name="secondStation">
                            <div class="error"><form:errors path="secondStation"/></div>
                            <ul id="to_stations">
                            </ul>
                        </div>
                    </div>
                    <button class="btn btn_blue" id="button" type="submit" disabled="true">
                        <spring:message code="addWayButton"/></button>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
<script type="text/javascript" src="/resources/js/addWayHandler.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
</body>
</html>
