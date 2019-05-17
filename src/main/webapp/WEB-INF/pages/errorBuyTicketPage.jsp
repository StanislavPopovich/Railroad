<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <c:if test="${trainsDepartTimeError}">
        <div class="back_message">
            <div class="message message_1">
               <span><spring:message code="trailAlreadyLeft"/></span>
            </div>
        </div>
    </c:if>
    <c:if test="${trainsTicketError}">
        <div class="back_message">
            <div class="message message_2">
                <span><spring:message code="ticketsError"/></span>
            </div>
        </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
