<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../diarycss.jsp" %>
<%@ include file="../login/sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.form-control{
	display:inline;
}	
</style>
<script type="text/javascript">
	$(function(){
		$("#month").change(function(){
			location.href="${path}/diary/"+this.value;
		})
	})
</script>
</head>

<body>
<%-- <%
	/* String id = (String)session.getAttribute("id");	 */
	String coupleid = (String)session.getAttribute("coupleid");
	String datelist[] = (String [])session.getAttribute("datelist");
%> --%>

<div id="container">   
	<div id="header">
		<button type="button" onclick="location.href='${path}/diaryall'" class="btn2 btn-link">U ♥ I</button>
		<button type="button" onclick="location.href='${path}/diarymy'" class="btn2 btn-link">My Diary</button>
		<button type="button" onclick="location.href='${path}/diaryyour'"class="btn2 btn-link">Your Diary</button>
	</div>         
	
	<div id="content">
		 	<c:if test="${empty list}">
				<table class="table table-striped table-hover">
					<tr>
						<td colspan="2">데이터가 없습니다</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty list }">
				<table class="table table-striped table-hover">
						<c:forEach var="diary" items="${list}" varStatus="status">
								<tr class="active">
									<th>${diary.d_date}<br>
									<c:forEach begin="${status.index}" end="${status.index}">
										   ${datelist[status.index]}
									</c:forEach>
									</th>
									<td>
										<a href="diaryshow/${diary.d_no }">
											<c:forEach begin="${status.index}" end="${status.index}">
												${contentlist[status.index]}
											</c:forEach>								
										</a>
									</td>
								</tr>
						</c:forEach>
				</table>
			</c:if> 
	</div>
		
	<div id="footer">
		<p>
		<form action="diarychoice" method="post" enctype="multipart/form-data">
			<input type = "hidden" name="id" value="${id}">
					
			<select class="form-control"  name = "month" id = "month" style="width:100px;height:50px;" onchange="monthchange(this.value)">
					<option value="01" <c:if test="${no=='01' }">selected="selected" </c:if>>JAN</option>
					<option value="02" <c:if test="${no=='02' }">selected="selected" </c:if>>FEB</option>
					<option value="03" <c:if test="${no=='03' }">selected="selected" </c:if>>MAR</option>
					<option value="04" <c:if test="${no=='04' }">selected="selected" </c:if>>APR</option>
					<option value="05" <c:if test="${no=='05' }">selected="selected" </c:if>>MAY</option>
					<option value="06" <c:if test="${no=='06' }">selected="selected" </c:if>>JUN</option>
					<option value="07" <c:if test="${no=='07' }">selected="selected" </c:if>>JUL</option>
					<option value="08" <c:if test="${no=='08' }">selected="selected" </c:if>>AUG</option>
					<option value="09" <c:if test="${no=='09' }">selected="selected" </c:if>>SEP</option>
					<option value="10" <c:if test="${no=='10' }">selected="selected" </c:if>>OCT</option>
					<option value="11" <c:if test="${no=='11' }">selected="selected" </c:if>>NOV</option>
					<option value="12" <c:if test="${no=='12' }">selected="selected" </c:if>>DEC</option>					
			</select>
			<a>|</a>
			<select class="form-control" name = "year" id = "year" style="width:100px;height:50px;">
					<option value="2017">2017</option>
			</select>	
		<a>|</a>
		<img style="cursor: pointer;" src="${path }/icon/plus.png" width="40px" height="40px" onclick="location.href='${path}/diaryWriteForm'">
		</form>
		
		
		<a href="${path}/main?id=${id}" class="btn btn-danger">MAIN</a>
		<a href="${path}/photoListMain?id=${id}" class="btn btn-danger">OUR PHOTOS</a>
		<a href="${path}/calendarMain?id=${id}" class="btn btn-danger">CALENDER</a>
		<a href="${path}/chatWindow?memberID=${id}" class="btn btn-danger">CHAT</a>
	</div>
</div>

</body>
</html>