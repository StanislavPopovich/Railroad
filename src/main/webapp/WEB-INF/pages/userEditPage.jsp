<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="modal_form">
            <form:form modelAttribute="userDto" action="/railroad/user/edit-user/result" >
                <div class="wrapper_input">
                    <form:label path="userName"><spring:message text="User Name"/></form:label>
                    <form:input path="userName" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="userName"/>
                    <form:hidden path="password"/>
                </div>
                <div class="wrapper_input">
                    <form:label path="userName">Roles:</form:label>
                    <form:select path="roles">
                        <form:option value="0" label="Select"/>
                        <form:options items="${roles}"/>
                    </form:select>
                </div>
                <button type="submit"><spring:message text="Edit User"/></button>
            </form:form>
        </div>

    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
