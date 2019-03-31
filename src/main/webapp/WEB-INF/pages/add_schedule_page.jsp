<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="now" value = "<%= new java.util.Date()%>" />
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <form:form method="POST" modelAttribute="schedule">
            <form:label path="trainNumber"> Train number </form:label>
            <form:input id="number" type="number" path="trainNumber" autofocus="true"/><br/>
            <form:label path="stationName">Station</form:label>
            <form:select type="text" path="stationName">
                <form:option value="0" label="Select"/>
                <form:options items="${stations}"/>
            </form:select><br/>
            <h1>${now}</h1>
            <%--<fmt:parseDate value="${now}" pattern="EEE MMM dd HH:mm:ss zzz yyyy" var="myDate"/>--%>
            <fmt:formatDate  value="${now}" pattern="dd-MM-yyyy" type="date" var="Date"/>
            <h1>${Date}</h1>
            <form:label path="date"> Date </form:label>
            <form:input type="text" path="date" value="${Date}" /><br/>
            <button id="button" type="submit">Add</button>
        </form:form>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <a href="<c:url value='/railroad/moderator/schedule'/>"><button>to schedule page</button></a>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="<c:url value='/railroad/admin/schedule'/>"><button>to schedule page</button></a>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
