<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu">

            </div>
            <div class="content content_type-one">
                <div id="items"  class="items">

                </div>
            </div>
        </div>
        <input type="hidden" id="page" value="${page}"/>
        <input type="hidden" id="fromStation" value="${searchData.departStation}"/>
        <input type="hidden" id="toStation" value="${searchData.arrivalStation}"/>
        <input type="hidden" id="departDate" value="${searchData.departDate}"/>
        <input type="hidden" id="departReturnDate" value="${searchData.departReturnDate}"/>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/searchTrainsHandler.js"></script>
</body>
</html>
