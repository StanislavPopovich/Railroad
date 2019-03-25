<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit userEntity</title>
</head>
<body>
<div>
<form:form modelAttribute="userDto" action="/railroad/admin/all-users/edit/result" >
    <table>
        <tr>
            <td>
                <form:label path="userName">
                    <spring:message text="User Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="userName" readonly="true" size="8" disabled="true"/>
                <form:hidden path="userName"/>
                <form:hidden path="password"/>
            </td>
        </tr>
        <tr>
            <td>Roles:</td>
            <td>
                <form:select path="roles">
                    <form:option value="0" label="Select"/>
                    <form:options items="${roles}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Edit User"/>"/>
            </td>
    </table>
</form:form>
</div>
</body>
</html>
