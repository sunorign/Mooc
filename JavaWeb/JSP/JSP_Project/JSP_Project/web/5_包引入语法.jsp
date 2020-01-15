<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/22
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,java.text.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
   public String getNow(){
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
       Date currentTime = new Date();
       return simpleDateFormat.format(currentTime);
   }
%>
当前时间：<%=getNow()%>
</body>
</html>
