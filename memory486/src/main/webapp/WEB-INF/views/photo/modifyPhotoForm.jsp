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
	function updatePhotoForm(p_no) {
		window.open("updatePhotoForm?p_no="+p_no,"",
				"width=600 height=700");
		
	}
	
	function deletePhoto(p_no) {
		if (confirm("사진을 삭제하시겠습니까?") == true){    //확인
			location.href="deletePhoto?p_no="+p_no;
		}else{   //취소
		    return false;
		}
	}
</script>
</head>
<body style="text-align: center; padding-top: 80px;" >
	<button class="btn btn-danger" onclick="updatePhotoForm(${p_no})">수정</button>
	<button class="btn btn-default" onclick="deletePhoto(${p_no})">삭제</button>
</body>
</html>