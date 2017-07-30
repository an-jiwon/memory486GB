<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to MEMORY 486GB</title>
<style type="text/css">
	#wrap { padding-top: 10%;}
	#loginMain {background-color: #f2dcdb; width: 300px; height:200px; text-align: center;}
	#line { border: 1px solid white; width : 295px; height: 195px; display: inline-block; margin-top: 2px;}
	#logPage {background-color: #f2dcdb; width: 300px; height:250px; text-align: center;}
	#line2 {border: 1px solid white; width : 295px; height: 244px; 
			display: inline-block; margin-top: 2px;}
	table {text-align: center; height: 234px; width:280px; }
	
	@font-face {
		font-family: "NanumBrush";
		src:url("fonts/NanumBrush.ttf") format("truetype");
	}
	.font {font-family: NanumBrush;
	font-size:20px;}
</style>
<script type="text/javascript">
	$(function() {
		$('#loginUp').click(function() {
			$('#loginMain').slideUp('slow');
		});
		$('#loginUp').click(function() {
			$('div:hidden:first').fadeIn(1000);
		});
	});
</script>
</head><body>

<div id="wrap" align="center">
	<c:if test="${not empty msg }">
			<script type="text/javascript">
				alert("${msg}");
			</script>
		</c:if>
	<div id="loginMain">
		<div id="line" align="center">
			<div style="display: inline-block; margin-top: 20px; ">
				<h3 class="font" style="font-size: 30px; color: white; margin-bottom: 35px;" align="center">MEMORY 486GB</h3>
				<a id="loginUp" class="btn btn-danger">LOGIN</a>
				<a href="joinForm" class="btn btn-danger">JOIN</a>
			</div>
		</div>
	</div>
	
	
	
	<div id="logPage" style="display: none;">
		<div id="line2">
			<form action="login" method="post">
				<table>
					<tr>
						<td class="font" colspan = "2" style="font-size:30px; color: white; padding-top: 20px; ">Memory 486GB</td>
					</tr>
					<tr>
						<td style="color: #B9062F; padding-left: 20px;">ID</td>
						<td><input type="text" name="memberID" required="required" style="border: 0px; border-bottom :1px solid #B9062F; background-color: #f2dcdb; text-align: center;"></td>
					</tr>
					<tr>
						<td style="color: #B9062F;  padding-left: 20px;">PASS</td>
						<td><input type="password" name="memberPass" required="required" style="border: 0px; border-bottom :1px solid #B9062F; background-color: #f2dcdb; text-align: center;"></td>
					</tr>
					<tr>
						<td colspan = "2">
							<input type="submit" class="btn btn-danger" value="LogIn">
						</td>
					<tr>				
				</table>	
			</form>
		</div>
	</div>
	


</div>


</body></html>