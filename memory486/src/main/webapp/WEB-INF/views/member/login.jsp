<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String id = (String)session.getAttribute("id"); %>
	안녕난로그인이야
	${id }<p>
	<button onclick="location.href='updateFormMember?id=${id}'">정보수정</button>
	<button onclick="location.href='photoListMain?id=${id}'" >photo</button>
	<button onclick="location.href='calendarMain?id=${id}'" >calendar</button>
</body>
</html>