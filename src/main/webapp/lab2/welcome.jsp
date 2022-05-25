<%--
  Created by IntelliJ IDEA.
  User: huashuo
  Date: 2022/5/25
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome,
    <%--todo 9 : use jsp:useBean to access the same instance of login bean from request scope--%>
    <jsp:useBean id="login" class="com.zhaoxianglai.lab2.Login" scope="request"/>
    <%--todo 10 : use jsp:getProperty to display username --%>
    <jsp:getProperty name="login" property="username"/>
</h2>
</body>
</html>

