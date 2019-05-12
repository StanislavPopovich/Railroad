<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title"><spring:message code="scheduleMenu"/></div>
                    <div id="btn_view" class="btn btn_blue"><spring:message code="viewSchedule"/></div>
                    <div id="btn_add_train" class="btn btn_blue"><spring:message code="addTrain"/></div>
                    <div id="btn_edit" class="btn btn_blue"><spring:message code="edit"/></div>
                    <div id="btn_remove_train" class="btn btn_blue"><spring:message code="removeTrain"/></div>
                </div>
            </div>
            <div class="content content_type-one">
                <div id="items" class="items">

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
<script type="text/javascript" src="/resources/js/scheduleHandler.js"></script>
</body>
</html>
