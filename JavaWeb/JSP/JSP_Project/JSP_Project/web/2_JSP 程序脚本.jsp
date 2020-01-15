<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/22
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--纯语言写法--%>
<% int i = 10;%>
<%
    if (i > 10) {
        out.print("i > 10");
    } else {
        out.print("i <= 10");
    }
%>
<hr>
<%--交叉写法--%>
<% if (i > 10) { %>
<span>i > 10</span>
<%} else { %>
<span>i <= 10</span>
<%} %>
</body>
</html>
