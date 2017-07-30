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
	function updateProfileForm(pro_no) {
		window.open("updateProfileForm?pro_no="+pro_no,"",
				"width=600 height=700");
		
	}
	
	function deleteProfile(pro_no) {
		if (confirm("사진을 삭제하시겠습니까?") == true){    //확인
			location.href="deleteProfile?pro_no="+pro_no;
		}else{   //취소
		    return false;
		}
	}
</script>
</head>
<body style="text-align: center; padding-top: 80px;" >
	<button class="btn btn-danger" onclick="updateProfileForm(${pro_no})">수정</button>
	<button class="btn btn-default" onclick="deleteProfile(${pro_no})">삭제</button>
</body>
</html>