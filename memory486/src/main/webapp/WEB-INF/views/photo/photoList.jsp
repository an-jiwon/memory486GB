<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body { text-align: center;}
#header{width:91%;  margin-left:30px; margin-right:30px;  margin-bottom:15px; padding-top: 10px;}
#middle{width:100%; float:left; margin-left: 15px; }
#left{width:47%;height:500px; float:left; margin-right:10px;  overflow:auto;}
#right{width:47%; height:500px;float:left;  overflow:auto;}
#footer{width:91%; height:130px; float:left; margin-left:30px; margin-right:30px; margin-top:10px;}
.leftPage{width: 50%; float: left;}
.rightPage{width: 50%; float: left;}
.plus {
	vertical-align:baseline; 
	cursor:pointer;
}
#profile{
	
}
img {
	cursor: pointer;
}


/* #inside { height:300px; background-color:yellow; overflow-y:scroll; } */
</style>
<%-- <%String id = (String)session.getAttribute("id"); %> --%>
<script type="text/javascript">

	function plus() {
		window.open("insertPhotoForm","",
		"width=600 height=700");
	}
	
	function searchYear(year) {
		var month = document.getElementById('selectMonth').value;
		location.href="photoList?id=${id}&searchYear="+year+"&searchMonth="+month;

	}
	
	function searchMonth(month) {
		var year = document.getElementById('selectYear').value;
		location.href="photoList?id=${id}&searchYear="+year+"&searchMonth="+month;		
	}
	function modify(p_no) {
		window.open("modifyPhotoForm?p_no="+p_no,"",
		"width=550 height=250");
	}

	
	function plusProfile() {
		window.open("insertProfileForm","",
		"width=600 height=700");
	}
	
	function modifyProfile(pro_no) {
		window.open("modifyProfileForm?pro_no="+pro_no,"",
				"width=550 height=250");
	}
	
</script>
</head>
<body >


<div id="wrap">
   <div id="header">
   	<div style="margin-bottom: 10px;">
   		<c:if test="${member.layout == 'left' }">
   			<img alt="" src="img/memoryImg/plus2.png" class="plus" width="20" height="20" style="margin-right: 15px;" onclick="plus()">
   		</c:if>
   		<h1 style="display: inline;">Our Photo</h1>
   		<c:if test="${member.layout == 'right' }">
   			<img alt="" src="img/memoryImg/plus2.png" class="plus" width="20" height="20" style="margin-left: 15px;" onclick="plus()">
   		</c:if>
   	</div>
   	<div id="profile">
   	<c:if test="${empty myprofile }">
   		<img alt = "대표사진을 선택하세요." src="" width = "300px" height = "179px" onclick="plusProfile()"/>
   	</c:if>
   	<c:if test="${not empty myprofile }">
   	<c:if test="${myprofile.pro_divide=='y' }">
   		<c:if test="${member.layout == 'left' }">
   			<img alt = "" src="${path}/upload/${myprofile.pro_title }" width = "149px" height = "179px" onclick="modifyProfile(${myprofile.pro_no})"/>
   			<c:if test="${yourprofile.pro_divide=='y' }">
   				<img alt = "" src="${path}/upload/${yourprofile.pro_title }" width = "149px" height = "179px" style="cursor: default;" />
   			</c:if>
   			<c:if test="${yourprofile.pro_divide=='n' or empty yourprofile }">
   				<img alt = "" src="${path}/img/memoryImg/noprofile.png" width = "149px" height = "179px" style="cursor: default;" />
   			</c:if>
   		</c:if>
   		<c:if test="${member.layout == 'right' }">
   			<c:if test="${yourprofile.pro_divide=='y' }">
   				<img alt = "" src="${path}/upload/${yourprofile.pro_title }" width = "149px" height = "179px" style="cursor: default;" />
   			</c:if>
   			<c:if test="${yourprofile.pro_divide=='n' or empty yourprofile }">
   				<img alt = "" src="${path}/img/memoryImg/noprofile.png" width = "149px" height = "179px" style="cursor: default;" />
   			</c:if>
   			<img alt = "" src="${path}/upload/${myprofile.pro_title }" width = "149px" height = "179px" onclick="modifyProfile(${myprofile.pro_no})"/>
   		</c:if>
   	</c:if>
   	<c:if test="${myprofile.pro_divide=='n' }">
   		<img alt = "" src="${path}/upload/${myprofile.pro_title }" width = "300px" height = "179px" onclick="modifyProfile(${myprofile.pro_no})"/>
   	</c:if>
   	</c:if>
   	</div>
   </div>
   <div id="middle">
   <c:if test="${member.layout==null }">
   		<h2 class="jumbotron">레이아웃 설정 후 이용해주세요.</h2>
   </c:if>
   <c:if test="${member.layout!=null }">
       <div id="left">
      	 <div>
       		<c:if test="${member.layout=='left' }">
       			<c:if test="${empty myphoto}">
       			<h4>데이터가 없습니다.</h4>
       			</c:if>
       			<c:if test="${not empty myphoto }">
       				<table align="right">
       					<c:forEach var="mylist" items="${myphoto }">
       						<tr>
       							<td valign="bottom" style="padding-right: 10px; padding-bottom: 15px;">${mylist.p_date}</td>
       							<td style="padding-bottom: 15px;"><img alt = "" src="${path}/upload/${mylist.p_file}" width = "149px" height = "179px"
       							onclick="modify(${mylist.p_no})"/></td>
       						</tr>
       					</c:forEach>
       				</table>
       			</c:if>  
       		</c:if>
       		<c:if test="${member.layout != 'left' }">
       			<c:if test="${empty yourphoto }">
       			<h4>데이터가 없습니다.</h4>
       			</c:if>
       			<c:if test="${not empty yourphoto }">
       				<table align="right">
       					<c:forEach var="yourlist" items="${yourphoto }">
       						<tr>
       							<td valign="bottom" style="padding-right: 10px; padding-bottom: 15px;">${yourlist.p_date }</td>
       							<td style="padding-bottom: 15px;"><img style="cursor: default;" alt="" src="${path}/upload/${yourlist.p_file}" width = "149px" height = "179px"/></td>
       						</tr>
       					</c:forEach>
       				</table>
       			</c:if>  
       		</c:if>
       	</div>
       	
       </div>
       <div id="right">
       	<div>
       		<c:if test="${member.layout=='right' }">
       			<c:if test="${ empty myphoto }">
       			<h4>데이터가 없습니다.</h4>
       			</c:if>
       			<c:if test="${not empty myphoto }">
       				<table>
       					<c:forEach var="mylist" items="${myphoto }">
       						<tr>
       							<td style="padding-bottom: 15px;" ><img alt = "" src="${path}/upload/${mylist.p_file}" width = "149px" height = "179px"
       							onclick="modify(${mylist.p_no})"/></td>
       							<td valign="bottom" style="padding-left: 10px; padding-bottom: 15px;">${mylist.p_date }</td>
       						</tr>
       					</c:forEach>
       				</table>
       			</c:if>  
       		</c:if>
       		<c:if test="${member.layout != 'right' }">
       			<c:if test="${empty yourphoto }">
       				<h4>데이터가 없습니다.</h4>
       			</c:if>
       			<c:if test="${not empty yourphoto}">
       				<table>
       					<c:forEach var="yourlist" items="${yourphoto }">
       						<tr>
       							<td style="padding-bottom: 15px;"><img style="cursor: default;" alt = "" src="${path}/upload/${yourlist.p_file}" width = "149px" height = "179px"/></td>
       							<td valign="bottom" style="padding-left: 10px; padding-bottom: 15px;">${yourlist.p_date }</td>
       						</tr>
       					</c:forEach>
       				</table>
       			</c:if>  
       		</c:if>
       	</div>
       	
       </div>
       </c:if>
   </div>
    
   <div id="footer">
   	<div style="width:100%; margin-bottom: 20px; margin-top: 10px;">
   	<div class="leftPage">
   	<c:if test="${member.layout=='left' }">
   	<c:if test="${empty myphoto }">x</c:if>
       	<c:if test="${not empty myphoto }">
       		<div style="display:-webkit-inline-box;" class="btn-toolbar">
				<c:if test="${mystartPage>PAGEPERBLOCK }">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${myendPage-PAGEPERBLOCK}&yourpageNum=${yourpageNum}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${mystartPage}" end="${myendPage}">
				<div class="btn-gruop">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${i }&yourpageNum=${yourpageNum}">${i}</a>
				</div>
				</c:forEach>
				<c:if test="${myendPage < mytotPage }">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mystartPage+PAGEPERBLOCK}&yourpageNum=${yourpageNum}">[다음]</a>
				</c:if>
		</div>
       	</c:if>
      </c:if>
      <c:if test="${member.layout!='left' }">
      <c:if test="${empty yourphoto }">x</c:if>
       	<c:if test="${not empty yourphoto }">
       	<div style="display: -webkit-inline-box;" class="btn-toolbar">
			<c:if test="${yourstartPage>PAGEPERBLOCK }">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum}&yourpageNum=${yourendPage-PAGEPERBLOCK}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${yourstartPage}" end="${yourendPage}">
				<div class="btn-gruop">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum }&yourpageNum=${i}">${i}</a>
				</div>
			</c:forEach>
			<c:if test="${yourendPage < yourtotPage }">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum}&yourpageNum=${yourstartPage+PAGEPERBLOCK}">[다음]</a>
			</c:if>
		</div>
		</c:if>
      </c:if>
      </div>
      <div class="rightPage">
      	<c:if test="${member.layout=='right' }">
      	<c:if test="${empty myphoto }">x</c:if>
       	<c:if test="${not empty myphoto }">
       		<div style="display: -webkit-inline-box;" class="btn-toolbar">
				<c:if test="${mystartPage>PAGEPERBLOCK }">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${myendPage-PAGEPERBLOCK}&yourpageNum=${yourpageNum}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${mystartPage}" end="${myendPage}">
				<div class="btn-gruop">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${i }&yourpageNum=${yourpageNum}">${i}</a>
				</div>
				</c:forEach>
				<c:if test="${myendPage < mytotPage }">
					<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mystartPage+PAGEPERBLOCK}&yourpageNum=${yourpageNum}">[다음]</a>
				</c:if>
		</div>
       	</c:if>
      </c:if>
      <c:if test="${member.layout!='right' }">
      <c:if test="${empty yourphoto }">x</c:if>
       	<c:if test="${not empty yourphoto }">
       	<div style="display: -webkit-inline-box;" class="btn-toolbar">
			<c:if test="${yourstartPage>PAGEPERBLOCK }">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum}&yourpageNum=${yourendPage-PAGEPERBLOCK}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${yourstartPage}" end="${yourendPage}">
			<div class="btn-gruop">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum }&yourpageNum=${i}">${i}</a>
			</div>
			</c:forEach>
			<c:if test="${yourendPage < yourtotPage }">
				<a class="btn btn-default" href="photoList?id=${id }&searchYear=${searchYear }&searchMonth=${searchMonth }&mypageNum=${mypageNum}&yourpageNum=${yourstartPage+PAGEPERBLOCK}">[다음]</a>
			</c:if>
		</div>
		</c:if>
      </c:if>
      </div>
      </div>
      
	   	<div style="margin-top: 20px; clear: both;">
   		<select id="selectYear" onchange="javascript:searchYear(this.value);">
			<option value="0"  <c:if test="${searchYear==null }"> selected="selected"</c:if>>YEAR
			<c:forEach var="yearList" items="${yearlist }">
			<option value="${yearList }" <c:if test="${yearList==searchYear }"> selected="selected"</c:if>>${yearList }
			</c:forEach>
		</select>
		
		<select id="selectMonth" onchange="javascript:searchMonth(this.value);">
			<option value="0" <c:if test="${searchMonth==null }"> selected="selected"</c:if>>MONTH 
			<option value="1" <c:if test="${searchMonth==1 }"> selected="selected"</c:if>>JANUARY 
			<option value="2" <c:if test="${searchMonth==2 }"> selected="selected"</c:if>>FEBUARY 
			<option value="3" <c:if test="${searchMonth==3 }"> selected="selected"</c:if>>MARCH
			<option value="4" <c:if test="${searchMonth==4 }"> selected="selected"</c:if>>APRIL 
			<option value="5" <c:if test="${searchMonth==5 }"> selected="selected"</c:if>>MAY 
			<option value="6" <c:if test="${searchMonth==6 }"> selected="selected"</c:if>>JUN 
			<option value="7" <c:if test="${searchMonth==7 }"> selected="selected"</c:if>>JULY 
			<option value="8" <c:if test="${searchMonth==8 }"> selected="selected"</c:if>>AUGUST
			<option value="9" <c:if test="${searchMonth==9 }"> selected="selected"</c:if>>SEPTEMBER 
			<option value="10" <c:if test="${searchMonth==10 }"> selected="selected"</c:if>>OCTOBER 
			<option value="11" <c:if test="${searchMonth==11 }"> selected="selected"</c:if>>NOVEMBER 
			<option value="12" <c:if test="${searchMonth==12 }"> selected="selected"</c:if>>DECEMBER
		</select>
   		</div>
   		<div align="center" style="width: 100%; margin-top: 10">
   			<button class="btn btn-danger" onclick="location.href='main?id=${id}'">MAIN</button>
   			<button class="btn btn-danger" onclick="location.href='diary'">DIARY</button>
   			<button class="btn btn-danger" onclick="location.href='calendarMain?id=${id}'">CALENDAR</button>
   			<button class="btn btn-danger" onclick="location.href='chatWindow?memberID=${id}'">CHAT</button>
   		</div>
   </div>
</div>

</body>
</html>