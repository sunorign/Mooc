<%--
  Created by IntelliJ IDEA.
  User: zexinChen
  Date: 2019/12/22
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.czx.db.DBUtil,com.czx.bean.Emp" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--获取用户名密码，并且需要去调用 DBUtil 当中的方法来判断是否存在指定的信息
1.如果返回 true，显示正确的页面
2.如果返回 false，显示错误的页面
request：获取请求信息，包括请求信息
getParameter(String name): 可以通过一个控件的name 来获取控件的值
out： 输出对象，输出指定的信息
--%>
<%
    String account = request.getParameter("account");
    String password = request.getParameter("password");
    Emp emp = new Emp(account, null, password, null);
    boolean flag = DBUtil.selectEmpByAccountAndPassword(emp);
    Map<String, Emp> empMap = DBUtil.map;
    if (flag) {%>
<%--response 对象的使用--%>
<h3 align="center">响应的字符编码集：<%= response.getCharacterEncoding()%>
</h3>
<h3 align="center">欢迎来到认识管理系统的首页</h3>
<hr>
<table>
    <tr>
        <td>
            账号：
        </td>
        <td>
            员工姓名：
        </td>
        <td>
            邮箱：
        </td>
    </tr>
    <%
        for (String key : empMap.keySet()) {
            Emp e = empMap.get(key);%>
    <tr>
        <td>
            <%=e.getAccount()%>
        </td>
        <td>
            <%=e.getName()%>
        </td>
        <td>
            <%=e.getEmail()%>
        </td>
    </tr>
    <%}%>
</table>
<%
    } else {
        out.print("登录错误！");
    }
%>
</body>
</html>
