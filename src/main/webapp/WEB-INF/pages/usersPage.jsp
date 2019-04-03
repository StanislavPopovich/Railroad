<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Users</h1>
<div>
    <table>
        <tr>
            <th width="100">User Name</th>
            <th width="100">Roles</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userName}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role" varStatus="status">
                        ${role}
                        <c:if test="${!status.last}">
                            /
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="<c:url value='/railroad/admin/all-users/edit/${user.userName}'/>">Edit Role</a></td>
                <td><a href="<c:url value='/railroad/admin/all-users/delete/${user.userName}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/railroad/admin/'/>"><button>to admin page</button></a>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
