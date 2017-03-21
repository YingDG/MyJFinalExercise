<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>MyJFinal</title>
</head>
<body>
<h1 align="center">Hello JFinal</h1>

<!--在页面表单中采用modelName.attrName形式为作为表单域的name-->
<form action="hello/save" method="get">
    <input name="u.username" value="ab" type="text"></br>
    <input name="u.age" value="12" type="text"></br>
    <input value="提交" type="submit">
</form>

</body>
</html>