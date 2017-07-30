<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../diarycss.jsp" %>
<%@ include file="../login/sessionCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function delete1(num){
		var no = num;
		if (confirm("삭제하시겠습니까??") == true){  

		    location.href="../diarydelete/"+no;

		}else{   

		    return;

		}
	}
	function update(num){
		var no = num;
		location.href="../update/"+no;
	}

</script>

<body>

<%-- <%
	/* String id = (String)session.getAttribute("id");	 */
	String coupleid = (String)session.getAttribute("coupleid");
%> --%>
<div id="container">   
	<div id="header">
		<button type="button" onclick="location.href='${path}/diaryall'" class="btn2 btn-link">U ♥ I</button>
		<button type="button" onclick="location.href='${path}/diarymy'" class="btn2 btn-link">My Diary</button>
		<button type="button" onclick="location.href='${path}/diaryyour'"class="btn2 btn-link">Your Diary</button>
	</div>	         
	<div id="content">
				<c:forEach var="diary" items="${content}">				
					<div style="margin-bottom: 10px;">
						<img alt="" style="cursor: pointer;" src="${path }/icon/eraser.png" onclick="update(${diary.d_no})"  width="27px" height="35px">
						<h4 style="display: inline-block; margin-left: 20px; margin-right: 20px;">${diary.d_date}</h4>
						<img alt="" style="cursor: pointer;" src="${path}/icon/trashcan.png" onclick="delete1(${diary.d_no})"  width="27px" height="35px">
					</div>
				<table class="table table-striped table-hover">					
					<tr>
						<td>${diary.d_location }</td>
					</tr>
					<tr>
						<td><img width = "550px" src="${path }/diaryimg/${diary.d_file}"></td>
					</tr>				
					<tr>
						<td>${diary.d_content }<p>
	
						<%-- <img src="../emotion/${diary.d_emotion}" width="20px" height="20px"></td> --%>
					</tr>
					
					<c:if test="${id == diary.d_writer }">
						
					</c:if>
					
				</table>
				</c:forEach>
	</div>
	<div id="footer">	
		<a href="${path}/main?id=${id}" class="btn btn-danger">MAIN</a>
		<a href="${path}/photoListMain?id=${id}" class="btn btn-danger">OUR PHOTOS</a>
		<a href="${path}/calendarMain?id=${id}" class="btn btn-danger">CALENDER</a>
		<a href="${path}/chatWindow?memberID=${id}" class="btn btn-danger">CHAT</a>
	</div>
</div>

</body>
</html>