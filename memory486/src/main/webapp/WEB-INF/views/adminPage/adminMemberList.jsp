<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../diarycss.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#container{
	width :800px;
	border : none;
	margin-top:50px;
	}
</style>
<script type="text/javascript">
	function recover(id) {
		if (confirm("탈퇴 복구를 진행하시겠습니까?") == true){    //확인
			location.href="recover?id="+id;
		}else{   //취소
		    return false;
		}
	}
</script>
</head>
<body>
	<button class="btn btn-primary" onclick="location.href='adminMemberList'">전체회원</button>
	<button class="btn btn-primary" onclick="location.href='adminCouple'">인증된 커플</button>
	<button class="btn btn-primary" onclick="location.href='adminCouple2'">미인증된 커플</button>
<div id="container">
	<table class="table table-striped table-hover">
		<tr>
		<th>ID</th>
		<th>이름</th>
		<th>이메일</th>
		<th>가입날짜</th>
		<th>탈퇴여부</th>
	</tr>
	<c:if test="${empty content}">
				
					<tr>
						<td colspan="5">데이터가 없습니다</td>
					</tr>
				
			</c:if>
			<c:if test="${not empty content }">
				
					<c:forEach var="member" items="${content}">						
							<tr>
								<td>${member.memberID}</td>
								<td>${member.name}</td>
								<td>${member.email}</td>
								<td>${member.joindate}</td>
								<td><a href="#" onclick="recover('${member.memberID}')">${member.m_out}</a></td>
							</tr>
					</c:forEach>
				
			</c:if>
			</table>
	<input class="btn btn-primary" type="button" onclick="history.go(-1)" value = "돌아가기"> 
</div>
</body>
</html>