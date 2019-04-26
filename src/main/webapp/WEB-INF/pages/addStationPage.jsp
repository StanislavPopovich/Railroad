<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Add station</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
        <c:if test="${isUser}">
            <div class="wrapper_form">
                <form:form method="POST" modelAttribute="way">
                    <div class="wrapper_input">
                        <form:label path="firstStation">New station </form:label>
                        <form:input id="name" type="text" path="firstStation" autofocus="true"/>
                    </div>
                    <div class="wrapper_select">
                        <form:select cssClass="select" type="text" path="secondStation">
                            <form:option value="0">Second station</form:option>
                            <form:options items="${stations}"/>
                        </form:select>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="distance"> Distance, km </form:label>
                        <form:input type="text" path="distance"/>
                    </div>
                    <div class="wrapper_btn">
                        <button id="button" class="btn btn_blue" type="submit">Add</button>
                    </div>
                </form:form>
            </div>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
