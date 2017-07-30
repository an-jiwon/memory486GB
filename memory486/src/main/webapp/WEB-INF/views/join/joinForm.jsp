<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEMORY 486GB</title>
<style type="text/css">
	#wrap {top:10%;}
</style>
<script type="text/javascript">
	$(function() {
		$('#check').click(function() {
			if (!frm.memberID.value) {
				alert("아이디 입력 후 아이디 체크를 해주세요!");
				frm.memberID.focus();
				return false;
			}
			location.href="idcheck?id=" + frm.memberID.value;
		});
		
		$('#plocation').click(function() {
			window.open("locationPop", "", "width=400, height=550, scrollbars=0, location=0");
		});
		
		$('#join').click(function() {
			if (frm.memberPass.value != frm.pass2.value) {
				alert("비밀번호가 맞지않습니다. 다시 확인해 주세요!")
				frm.pass2.focus();
				return false;
			} 
		});
	});
</script>
</head><body>
	
<div id="wrap" align="center">
	<div style="background-color:#f2dcdb; height:45px; width:45%; margin-bottom:30px;" align="center">
		<h3 style="color:#ff4136; padding-top: 10px; font:bold;" align="center">JOIN</h3>
	</div>
	
	<c:choose>
		<c:when test="${not empty msg }">
			<script type="text/javascript">alert("${msg}");</script>
		</c:when>
	</c:choose>
		
	<form action="join" name="frm" method="post">	
		<table class="form-group" style="aline:center;">
			<tr>
				<th>
					<label class="control-label" for="focusedInput">ID</label>
						<c:choose>
							<c:when test="${not empty memberID }">
								<input class="form-control" id="focusedInput" type="text" name="memberID" 
								required="required" autofocus="autofocus" value="${memberID }">
							</c:when>
							<c:when test="${empty memberID }">
								<input class="form-control" id="focusedInput" type="text" name="memberID" 
								required="required" autofocus="autofocus">
							</c:when>
						</c:choose>	
				</th>
				<th>
					<label class="control-label" for="focusedInput"></label>
					<input style="margin-top: 25px;" type="button" id="check" class="btn btn-danger btn-sm" value="ID CHECK">
				</th>
			</tr>	
	 		
	 		<tr>
	 			<th colspan="2">
			 		<label class="control-label" for="inputDefault">PASSWORD</label>
			 		<input type="password" class="form-control" id="inputDefault" name="memberPass" required="required" >
				</th>
			</tr>
				
			<tr>	
				<th colspan="2">
					<label class="control-label" for="inputDefault">PASSWORD CHECK</label>
			 		<input type="password" class="form-control" id="inputDefault" name="pass2" required="required" >
	 			</th>
			</tr>
		
			<tr>
				<th colspan="2">
					<label class="control-label" for="inputDefault">NAME</label>
	 				<input type="text" class="form-control" id="inputDefault" name="name" required="required" >
	 			</th>
	 		</tr>
	 		
	 		<tr>
				<th colspan="2">
					<label class="control-label" for="inputDefault">E-MAIL</label>
	 				<input type="email" class="form-control" id="inputDefault" name="email" required="required" placeholder="memory@memory.com">
	 			</th>
			</tr>
	
			<tr>
				<th colspan="2">
					<label class="control-label" for="inputDefault" >LOCATION</label>
					<input type="text" readonly="readonly" class="form-control input-sm" id="inputSmall" name="m_location" required="required" style="text-align: center;">
					<a class="btn btn-default" id="plocation" style="width:99%;" >SELECT YOUR LOCATION</a>
	 			</th>
			</tr>
		</table>
		
		<input type="submit" class="btn btn-danger" id="join" value="JOIN">
		<a href="loginMain" class="btn btn-danger" id="cancle">CANCLE</a>
		<input type="reset" class="btn btn-danger" id="cancle" value="RESET">
	</form>
</div>

</body></html>