<html>
<body>
<%
    //将请求转发到cookie中去验证用户cookie信息
    request.getRequestDispatcher("/cookie").forward(request, response);
%>
</body>
</html>
