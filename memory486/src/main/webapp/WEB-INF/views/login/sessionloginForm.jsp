<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEMORY 486GB</title>
<style type="text/css">
	#wrap { padding-top: 10%;}
	table {background-color: #f2dcdb; width: 300px; height:250px; text-align: center;}
	@font-face {
		font-family: "NanumBrush";
		src:url("fonts/NanumBrush.ttf") format("truetype");
	}
	.font {font-family: NanumBrush;
	font-size:20px;}
</style>
</head>
<body>

<div id="wrap" align="center">
		<c:if test="${not empty msg }">
			<script type="text/javascript">
				alert("${msg}");
			</script>
		</c:if>
		<c:if test="${not empty id }">
		<script type="text/javascript">
			opener.location.reload();	
			window.close();
		</script>
		</c:if>
	<div class="loginMain">
		<form action="sessionlogin" method="post">
			<table>
				<tr>
					<td class="font" colspan = "2" style="font-size:30px; color: white;">Memory 486GB</td>
				</tr>
				<tr>
					<td style="color: #B9062F; padding-left: 20px;">ID</td>
					<td><input type="text" name="memberID" required="required"></td>
				</tr>
				<tr>
					<td style="color: #B9062F;  padding-left: 20px;">PASS</td>
					<td><input type="password" name="memberPass" required="required"></td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type="submit" class="btn btn-danger" value="LogIn">
						|
						<a href="joinForm" class="btn btn-danger">JOIN</a>
					</td>
				<tr>				
			</table>	
		</form>
	</div>
</div>

</body>
</html>