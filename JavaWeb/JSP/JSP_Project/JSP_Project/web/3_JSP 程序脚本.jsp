<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/22
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    int totalCount = 0;//成员变量
%>
<%
    int localCount = 0;//脚本变量
    totalCount++;
    localCount++;
%>
<%
    out.print(totalCount);
    out.print("</br>");
    out.print(localCount);
%>
</body>
</html>
