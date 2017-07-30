<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFFF">
		<c:if test="${not empty msg }">
			<span class="err">${msg }</span>
		</c:if>
	<div class="loginMain">
		<form action="adminlogin" method="post">
			<table class = "logintable">
				<tr>
					<td colspan = "2"><br><br>Admin Login<br><br></td>
				</tr>
				<tr>
					<td>adminID</td>
					<td><input type="text" name="adminid" required="required" class="form-control" />></td>
				</tr>
				<tr>
					<td>adminPASS</td>
					<td><input type="password" name="adminpass" required="required" class="form-control" />></td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type="submit" value = "Login" class="btn btn-white">
					</td>
				<tr>				
			</table>	
		</form>
	</div>
</body>
</html>