<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <%String id = (String) session.getAttribute("id"); %> --%>
<script type="text/javascript">
	function couple(id_B) {
		location.href="coupleRequest?id=${id}&ID_B="+id_B;
		opener.parent.location.reload();
	}
	function cancel() {
		window.close();
	}
	
</script>
</head>
<body style="margin-top: 40px;">
<div align="center">
<c:if test="${idchk<1}">
	존재하지 않는 회원입니다.
	<form><table>
	<tr><th style="padding-right: 7px;">COUPLE ID</th><td><input type="text" name="id" 
		required="required">&nbsp;&nbsp;
		<input type="submit" class="btn btn-danger" value="확인"></td></tr>
</table></form>
</c:if>
<c:if test="${ID_B == id }">
		본인에겐 커플신청을 할 수 없습니다.
		<form><table>
		<tr><th style="padding-right: 7px;">COUPLE ID</th><td><input type="text" name="id" 
			required="required">&nbsp;&nbsp;
			<input type="submit" class="btn btn-danger" value="확인"></td></tr>
	</table></form>
</c:if>
<c:if test="${ID_B != id }">
	<c:if test="${idchk==1 && couplechk ==1 }">
		이미 커플이 맺어진 회원입니다.
		<form><table>
		<tr><th style="padding-right: 7px;">COUPLE ID</th><td><input type="text" name="id" 
			required="required">&nbsp;&nbsp;
			<input type="submit" class="btn btn-danger" value="확인"></td></tr>
	</table></form>
	</c:if>
	<c:if test="${idchk==1 && couplechk == 0  }">
		${ID_B }님께 커플 신청을 하시겠습니까?<p>
		<button class="btn btn-danger" onclick="couple('${ID_B}')">확인</button>
		<button class="btn btn-default" onclick="cancel()">취소</button>
	</c:if>
</c:if>
</div>
</body>
</html>