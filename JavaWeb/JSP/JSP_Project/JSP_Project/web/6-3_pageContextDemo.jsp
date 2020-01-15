<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/23
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
pageContext 的作用：
1、forward 方法来完成请求转发
2、include（模块化） 方法来完成包含关系
*3、pageContext 可以来获取其他内置对象
-->
<%
//    pageContext.forward("a.jsp?name=czx");
    pageContext.include("6-1_login.jsp");
%>
</body>
</html>
