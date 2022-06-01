<%--
  Created by IntelliJ IDEA.
  User: huashuo
  Date: 2022/6/1
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%-- todo 9: use c:out to print message fron request --%>
<c:out value="${requestScope.message}"/>

<%--todo 1: use c:url to set url in action="validate.jsp" --%>
<c:url var="url" value="validate.jsp"/>
<form action="${url}">
    Username : <input type="text" name="username"><br>
    Password : <input type="password" name="password"><br>
    <input type="submit" value="Login"/>
</form>

</body>
</html>
