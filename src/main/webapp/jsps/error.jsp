<%@ page import="com.biz.std.util.HostLink" %>
<%--
  Created by IntelliJ IDEA.
  User: Yin
  Date: 2017/9/14
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    HostLink hostLink = new HostLink();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>操作失败</h1>
<a href="http://<%=hostLink.getHostIp()%>:<%=hostLink.getHostPost()%>/std/">点击返回</a>
</body>
</html>
