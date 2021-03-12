<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>JSP= HTML + JAVA도 가능하다.</h1>
<%
String myName = "최수연";
%>
<h2>나의 이름은 <%= myName %> 입니다.</h2>
</body>
</html>