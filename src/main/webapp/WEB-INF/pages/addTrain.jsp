<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add trainEntity</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <form id="start_station">
            <form:select cssClass="select" id="start" type="text" path="startStation">
                <form:option value="0" label="Select start station"/>
                <form:options items="${stations}"/>
            </form:select>
        </form>
        <form id="end_station">
            <form:select cssClass="select" id="end" type="text" path="endStation">
                <form:option value="0" label="Select destination station"/>
            </form:select>
        </form>
        <form:form method="POST" modelAttribute="train" action="/railroad/user">

            <form:select cssClass="select" id="routes"  type="text" path="stations" >
                <form:option  value="0" label="Select route"/>
            </form:select><br/>

            <form:label path="number"> Number </form:label>
            <form:input type="text" path="number"/><br/>


            <form:label path="seats"> Seats </form:label>
            <form:input path="seats"/><br/>
            <button id="button" type="submit">Add train</button>
        </form:form>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <a href="<c:url value='/railroad/moderator/all-trains'/>"><button>to all trains page</button></a>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="<c:url value='/railroad/admin/all-trains'/>"><button>to all trains page</button></a>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
