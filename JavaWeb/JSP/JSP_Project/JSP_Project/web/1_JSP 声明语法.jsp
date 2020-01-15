<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/22
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    String str = "hello world!";

    String getStr() {
        return "hello world! 2";
    }
%>
<hr>
<% out.print(this.str);%>
<hr>
<%= this.getStr()%>
</body>
</html>
