<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../login/sessionCheck.jsp" %>
 <%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function ok() {
		if (confirm("커플 신청을 수락하시겠습니까?") == true){    //확인
			document.frm.submit();
		}else{   //취소
		    return false;
		}
	}
	
	function cancel() {
		if (confirm("커플 신청을 거절하시겠습니까?") == true){    //확인
			window.close();
		}else{   //취소
		    return false;
		}
	}
</script>
</head>
<body style="padding-top: 50px;">
<div align="center">
<form action="couplepositive" name="frm" method="post" onsubmit="return ok()">
	<input type="hidden" name="id" value="${id }"/>
	<table >
		
		<tr>
			<td >${coupleid }님으로부터 커플신청이 왔습니다.</td>
		</tr>
		<tr><td><br></td></tr>
		<tr>
			<td align="center"><input type="submit" value="수락" class="btn btn-danger">						
				<input type="button" value ="거절" onclick="cancel()" class="btn btn-default"></td>
		</tr>	
	</table>
</form>
</div>



</body>
</html>