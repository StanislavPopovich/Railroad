<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="back_message">
        <div class="message message_1">
            <span><spring:message code="daoException"/></span>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
