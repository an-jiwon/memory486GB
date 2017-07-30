<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("대표사진 업로드가 완료되었습니다.");
		opener.location.reload();
		window.close();
	</script>
</c:if>
<c:if test="${result <= 0 }">
	<script type="text/javascript">
		alert("대표사진 업로드 중 오류가 발생하였습니다.");
		history.go(-1);
	</script>
</c:if>
</body>
</html>