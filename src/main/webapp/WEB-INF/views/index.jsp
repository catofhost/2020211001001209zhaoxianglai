<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2022/4/13
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@include file="header.jsp"%>
<h1>
    <%= "Welcome to my home page!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet-week01</a>
<br/>
<a href="hello">Student Info Servlet-week02</a>
<br/>
<a href="register">Register-getParameter-week03</a>
<br/>
<a href="config">Config parameter-week04</a>
<br/>
<a href="register.jsp">Register JDBC -week04</a>
<br/>
<a href="login.jsp">Login -week05</a>
<form method="get" target="_blank" action="search">
    <input type="text" name="txt" size=30/>
    <select name="search">
        <option value="baidu">Baidu</option>
        <option value="bing">Bing</option>
        <option value="google">Google</option>
    </select>
    <input type="submit" value="Search"/>
</form>
<%@include file="footer.jsp"%>
