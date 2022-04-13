<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2022/4/13
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@page import="com.zhaoxianglai.model.User" %>
<h1 align="center">User List</h1>
<%
    User user = (User) request.getAttribute("user");
%>
<table width=540 border=1 align=center>
    <caption>Users</caption>
    <tr>
        <td>UserName</td>
        <td>PassWord</td>
        <td>Email</td>
        <td>Gender</td>
        <td>BirthDate</td>
    </tr>
    <tr>
        <td><%=user.getUsername()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getGender()%></td>
        <td><%=user.getBirthdate()%></td>
    </tr>
</table>
<%@include file="footer.jsp"%>
