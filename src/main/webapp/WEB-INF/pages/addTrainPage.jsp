<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add train</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title"><spring:message code="trainsMenu"/></div>
                    <div class="wrapper_dropdown">
                        <small><spring:message code="from"/></small>
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                            <input id="depart_station" class="js-dropdown_value dropdownCheck" type="text"
                                   name="departStation">
                            <ul id="from_stations"></ul>
                        </div>
                    </div>
                    <div class="wrapper_dropdown">
                        <small><spring:message code="to"/></small>
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                            <input id="arrival_station" class="js-dropdown_value dropdownCheck" type="text"
                                   name="arrivalStation">
                            <ul id="to_stations">
                            </ul>
                        </div>
                    </div>

                    <div id="add_train" class="btn btn_blue not_active"><spring:message code="addButton"/></div>

                    <div class="line"></div>
                    <div id="add_new_station" class="btn btn_blue "><spring:message code="addStationButton"/></div>
                </div>
            </div>
            <div class="content content_type-one">
                <div id="routes" class="items">

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript" src="/resources/js/addTrainHandler.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
</body>
</html>
