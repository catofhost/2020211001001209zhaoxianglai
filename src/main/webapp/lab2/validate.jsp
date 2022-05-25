<%--
  Created by IntelliJ IDEA.
  User: huashuo
  Date: 2022/5/25
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
<%--Todo 1: Use <jsp:useBean> to create a Login bean instance in request scope --%>
<jsp:useBean id="login" class="com.zhaoxianglai.lab2.Login" scope="request"/>
<%--Todo 2: Use <jsp:setProperty> to set  beans' property username and password--%>
<jsp:setProperty name="login" property="username" value="zhaoxianglai-2020211001001209-Class12"/>
<jsp:setProperty name="login" property="password" value="123456"/>
<%
    //todo 3: use if check username is admin and ppassword is admin
    if(request.getParameter("username").equals(login.getUsername()) && request.getParameter("password").equals(login.getPassword())){
        session.setAttribute("login",login);
%>
<%--todo 4: use jsp:forward to welcome.jsp page--%>
<jsp:forward page="welcome.jsp"/><!-- forward would not through filter-->
<%--todo 5: else part{ --%>
<%// todo 6: print username or password error message
}else{
    out.write("Username or Password Error !!!");
%>
<%--todo 7: use jsp:include login.jsp page --%>
<jsp:include page="login.jsp"/>
<%--todo 8: close else --%>
<%
    }
%>
</body>
</html>
