<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html PUBLIC><html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEMORY 486GB</title>
<style type="text/css">
	#all { width: 80%; margin: 5%;}
	#profile { display : inline-block; width:40%; }
	.menu_btn {
		font-size:15px;
		font-weight: bold;
		text-align: center;
		vertical-align: middle;
		height: 10%;
		width: 50%;
		padding-top: 5px;
		padding-bottom: 5px;
		border: 1px solid #ff291c;
		color : white;
		background-color: #ff4136;
		-moz-border-radius: 10px;
		-webkit-border-radius: 10px;
		border-radius: 10px;
		text-shadow: 1px 2px 1px #333333;
		margin: 5px;
	}
	.menu_btn:hover {
		-moz-box-shadow: 2px 2px 5px #666666;
		-webkit-box-shadow: 2px 2px 5px #666666;
		box-shadow: 2px 2px 5px #666666;
		position: relative;
		top: 2px;
	}
</style>

<script type="text/javascript">
	$(function() {
		$("#chatStart").click(function() {
			var id = '<%=(String)session.getAttribute("id") %>';
			location.href="chatWindow?memberID=" + id;
		});
	}); 
	
	function maincouplecheck() {
		window.open("maincouplecheck?id=${id}","","width=500, height=250");
	}
</script>

</head><body>

<div id="all" align="center">
	<div id="profile" align="center" style="width:50%; margin-left: 20%; margin-right: 20%;">
		<div style="float:left; display: inline-block;">
			<a href ="updateFormMember?id=${id }">edit</a><br>
			<font size="3px">${member.name }	</font>
			<font color="green">${member.m_online}</font><br>
			<font>Location in <br> ${member.m_location }</font><p>
			<font>${locTime }</font><br> 
			<a href="logout?id=${id }">로그아웃</a>
		</div>
		<div style="vertical-align:middle; display: inline-block;">
			
			<c:if test="${member.m_pic==null }">
				<a href="updateFormMember?id=${id }"><img src="img/who.png" width="60px" height="80px"></a>
			</c:if>
			<c:if test="${member.m_pic != null }">
				<a href="updateFormMember?id=${id }"><img src="${path}/upload/${member.m_pic}" width="60px" height="80px"> </a>
			</c:if>
			<img src="img/heart.JPG" width="20px" height="20px">
				<c:if test="${coupleMember.m_pic==null }" >
					<img src="img/who.png" width="60px" height="80px">
				</c:if>
				<c:if test="${coupleMember.m_pic!=null }" >
				<img src="${path}/upload/${coupleMember.m_pic}" width="60px" height="80px">
				</c:if>
				<p>
			<c:if test="${empty dday }"> <a href="calendarMain?id=${id }">SET D-Day</a></c:if>
			<c:if test="${not empty dday }">
				<c:if test="${dday != 0 }">
					<a href="calendarMain?id=${id }"> ${dd.day_title}</a><br>
					<c:if test="${dday>0 }">
					<a style="color: blue;"> D+${dday}</a></c:if> 
					<c:if test="${dday<0 }">
					<a style="color:red;"> D${dday}</a></c:if> 
				</c:if>	
				<c:if test="${dday == 0 }">
				<div>
					<img src="${path }/img/memoryImg/emo.gif" width="50px" height="50px">
					<div style="display: inline-block; vertical-align: middle;">
					${dd.day_title }<br>
					<fmt:formatDate value="${dd.day_date }" pattern="yyyy-MM-dd"/>
					</div>
					<img style="vertical-align: top;" alt="" src="${path }/img/memoryImg/balloon.gif" width="50px" height="40px">
					</div>
					
				</c:if>
			</c:if>
		</div>
		<c:if test="${coupleMember.memberID!=null }" >
			<div style="float:right; display: inline-block;">
				<br>
				<c:if test="${coupleMember.m_online == 'on' }">
					<font color="green">${coupleMember.m_online}</font>
				</c:if>
				<c:if test="${coupleMember.m_online == 'off' }">
					<font color="red">${coupleMember.m_online }</font>
				</c:if>
				<font size="3px">${coupleMember.name }</font><br>
				<font>Location in <br> ${coupleMember.m_location }</font><br>
				<font>${locTime2 }</font>
			</div>
		</c:if>
		<c:if test="${coupleMember.memberID==null }" >
			<c:if test="${acceptchk=='n' }" >
				<div style="vertical-align:middle; display: inline-block; float: right;">
					<button class="btn btn-default" onclick="maincouplecheck()">커플 신청이 왔습니다.</button>
				</div>
			</c:if>
			<c:if test="${acceptchk=='ok' }" >
				<div style="vertical-align:middle; display: inline-block; float: right;">
					<button class="btn btn-default disabled">커플 신청 진행중</button>
				</div>
			</c:if>
		</c:if>
	</div>



	<div style="padding-top: 3%;">
		<button class="menu_btn" onclick="location.href='diary'">DIARY</button>
		<button class="menu_btn" onclick="location.href='photoListMain?id=${id}'">OUR PHOTOS</button>
		<button class="menu_btn" onclick="location.href='calendarMain?id=${id}'">CALENDAR</button>
		<button class="menu_btn" id="chatStart">CHAT</button>
	</div>

</div>

</body>
</html>