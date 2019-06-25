<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="CSS/style.css">
</head>
<body>

<%
	String msg = (String)request.getAttribute("msg");
%>

<form action="auth" method="POST">
<u><h2>Master Login</h2></u>

<h4>ログイン情報を入力してください</h4>
<h3><%
if(msg!=null){%>
	<%=msg %>
<% }
%></h3>
<table class="center" border="0">

	<tr>
		<td>メールアドレス</td>
		<td><input type="text" name="studentId"></td>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr class="center">
		<td colspan="2"><input type="submit" value="ログイン" style="background-color:aqua"></td>
	</tr>


</table>

</form>
<p>©Yahoo!</p>
</body>

</html>