<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2022/4/13
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>
    Login
</h1>
<form method="post" action="login">
    Username:<input name="username" type="text" size="20"><br>
    Password:<input name="password" type="password" size="20"><br>
    <input name="login" type="submit" size="10" value="Login">
</form>
<%
    if (!(request.getAttribute("message") == null)){
        //error
        out.println(request.getAttribute("message"));
    }
%>
<%@include file="footer.jsp"%>