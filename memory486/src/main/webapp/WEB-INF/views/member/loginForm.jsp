<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

</style>
</head>
<body>
		<c:if test="${not empty msg }">
			<span class="err">${msg }</span>
		</c:if>
	<div class="loginMain">
		<form action="login" method="post" >
			<table>
				<tr>
					<td colspan = "2">Memory 486GB</td>
				</tr>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" required="required"></td>
				</tr>
				<tr>
					<td>PASS</td>
					<td><input type="password" name="password" required="required"></td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type="submit" value="확인">
						|
						<input type="button" value = "Join">
					</td>
				<tr>				
			</table>	
		</form>
	</div>
</body>
</html>