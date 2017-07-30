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
</head>
<body>
	<button class="btn btn-primary" onclick="location.href='adminMemberList'">전체회원</button>
	<button class="btn btn-primary" onclick="location.href='adminCouple'">인증된 커플</button>
	<button class="btn btn-primary" onclick="location.href='adminCouple2'">미인증된 커플</button>
<div id="container">
	<table class="table table-striped table-hover">
		<tr>
			<th>아이디</th>
			<th>아이디2</th>
			<th>승인여부</th>
		</tr>
		<c:if test="${empty list}">
					
						<tr>
							<td colspan="3">데이터가 없습니다</td>
						</tr>
					
				</c:if>
				<c:if test="${not empty list }">
					
						<c:forEach var="couple" items="${list}">						
								<tr>
									<td><a href ="adminMember/${couple.id_A}">${couple.id_A}</a></td>
									<td><a href ="adminMember/${couple.id_B}">${couple.id_B}</a></td>
									<td>${couple.accept}</td>
								</tr>
						</c:forEach>
					
				</c:if> 
	</table>
</div>
</body>
</html>