<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>User page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">

    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <div class="content_table">
            <table class="table">
                <tr>
                    <th>User Name</th>
                    <th>Roles</th>
                    <th class="th_edit"></th>
                    <th class="th_delete"></th>
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
                        <td class="btn btn_edit"><a href="<c:url value='/railroad/user/edit-user/${user.userName}'/>">Edit</a></td>
                        <td class="btn btn_delete"><a href="<c:url value='/railroad/user/delete-user/${user.userName}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
