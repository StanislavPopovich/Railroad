<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title"><spring:message code="adminMenu"/></div>
                    <button class="btn btn_blue" id="btn_edit_role" type="submit" disabled="true">
                        <spring:message code="editRoleBtn"/></button>
                    <button class="btn btn_blue" id="btn_remove" type="submit" disabled="true">
                        <spring:message code="removeBtn"/></button>
                </div>
            </div>
            <div class="content content_type-users">
                <div class="users_caption">
                    <div><spring:message code="loginCaption"/></div>
                    <div><spring:message code="roleCaption"/></div>
                </div>
                <div id="items" class="items">

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
<script type="text/javascript" src="/resources/js/adminHandler.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
</body>
</html>
