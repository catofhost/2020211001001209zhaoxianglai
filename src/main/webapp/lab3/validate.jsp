<%--
  Created by IntelliJ IDEA.
  User: huashuo
  Date: 2022/6/1
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>

<%--
if(request.getParameter("username").equals("admin") && request.getParameter("password").equals("admin")){
    String url="welcome.jsp?username="+request.getParameter("username");
    response.sendRedirect(url);
}else{
    request.setAttribute("message","Username Password Error");
    request.getRequestDispatcher("login.jsp").include(request,response);
}
--%>
<%--todo 2: use c:choose ,c:when c:otherwise to validate username is 'admin' and  password is 'admin'--%>
<c:choose>
    <c:when test="${param.username.equals('zhaoxianglai-2020211001001209-class12') && param.password.equals('123456')}">
        <%--todo 3: when username == admin use c:url and c:param to make url = "welcome.jsp?username=admin"--%>
        <c:url var="url1" value="welcome.jsp" scope="request">
            <c:param name="username" value="zhaoxianglai-2020211001001209-class12"/>
        </c:url>
        <%-- todo 4.use c:redirect to url= welcome.jsp?username=admin--%>
        <c:redirect url="${url1}"/>
    </c:when>
    <%-- todo 5. use c:otherwise --%>
    <c:otherwise>
        <%-- todo 6:use c:set to set a attribute name message="username password error" in request  --%>
        <c:set var="message" value="username password error" scope="request"/>
        <%--todo 7:use c:import to include login.jsp --%>
        <c:import url="login.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
