<%--
  Created by IntelliJ IDEA.
  User: name
  Date: 2018/11/14
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Basic ValidateBox - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="用户注册" style="width:600px;padding:10px 60px 20px 60px">
    <form action="/user" method="post">
        <input type="hidden" name="oper" value="register"/>
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td><input name="uname" class="easyui-validatebox textbox" data-options="required:true"
                           missingMessage="用户名必填">
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="pwd" class="easyui-validatebox textbox" data-options="required:true"
                           missingMessage="密码必填">
                </td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    男：<input type="radio" name="sex" value="1" checked="checked"/>
                    女：<input type="radio" name="sex" value="0"/>
                </td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input name="age" value="" class="easyui-validatebox textbox"></td>
            </tr>
            <tr>
                <td>出生年月:</td>
                <td><input name="birth" value="" class="easyui-datebox textbox"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="注册"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<style scoped="scoped">
    .textbox {
        height: 20px;
        margin: 0;
        padding: 0 2px;
        box-sizing: content-box;
    }
</style>

</body>
</html>