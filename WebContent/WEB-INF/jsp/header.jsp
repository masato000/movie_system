<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="movie.beans.LoginInfoBeans" %>
<%
////////////////////////////////////////
//セッションからログイン情報を取得
LoginInfoBeans loginInfo=(LoginInfoBeans)session.getAttribute("loginInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/style.css">
</head>
<header>
	<a href="top"><img style="width:70px;heigt=70px;"src="img/rogo1.png"></a>
	<%=loginInfo.getCourse()%>
	<%=loginInfo.getGrade()%>年
	<%=loginInfo.getClassName()%>クラス
	ようこそ<%=loginInfo.getName()%>さん
	<a href="logout">ログアウト</a>
</header>