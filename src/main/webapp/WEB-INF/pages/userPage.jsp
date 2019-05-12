<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>User page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div class="wrap wrapper-flex">
            <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
                <div id="search_menu" class="left_menu type_black">
                    <div>
                        <div class="title">All orders</div>
                        <div id="upcoming" class="btn btn_blue">Upcoming trips</div>
                        <div id="completed" class="btn btn_blue">Completed trips</div>
                    </div>
                </div>
                <div class="content content_type-train">
                    <div id="items" class="items">

                    </div>
                </div>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
                <meta http-equiv="refresh" content="0.0000001;http://localhost:8081/railroad/user/schedule-view" />
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <meta http-equiv="refresh" content="0.0000001;http://localhost:8081/railroad/admin/users" />
            </c:if>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/allOrdersHandler.js"></script>
</body>
</html>
