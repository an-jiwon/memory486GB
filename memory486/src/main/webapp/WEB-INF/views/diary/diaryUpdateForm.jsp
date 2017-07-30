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
</head>

     <script type="text/javascript">
    
        var openWin;
    
        function openChild()
        {
            // window.name = "부모창 이름"; 
            window.name = "../diaryWriteForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");n
            openWin = window.open("../diaryWriteFormsub",
                    "Emoticon", "width=400, height=300, resizable = no, scrollbars = no");    
        }
        window.onload = function() {
       		document.getElementById('btn').onclick = function() {
           	 document.frm.submit();
            return false;

        };
    };
 
   </script>
	<style>
			.form-group{
			clear:both;
			float:left;
			}
			.input-sm{
			width:100px;
			}
			.input-lg {
		    
		    width: 700px;
		    }
	</style>
<body>
<%-- <%
	/* String id = (String)session.getAttribute("id"); */	
	String coupleid = (String)session.getAttribute("coupleid");
%> --%>
<c:if test="${not empty resultMsg }">
<script type="text/javascript">
	alert("${resultMsg}");
</script>
</c:if>
<div id="container"> 
	<div id="header">
		<button type="button" onclick="location.href='${path}/diaryall'" class="btn2 btn-link">U ♥ I</button>
		<button type="button" onclick="location.href='${path}/diarymy'" class="btn2 btn-link">My Diary</button>
		<button type="button" onclick="location.href='${path}/diaryyour'"class="btn2 btn-link">Your Diary</button>
	</div>		
	<div id="content">
		<form action="${path }/diaryUpdate" method="post" enctype="multipart/form-data" name="frm">
		<c:forEach var="content" items="${content}">
			<input type = "hidden" name="id" value="${id}">
			<input type="hidden" name="d_no" value="${content.d_no }">
			<select  class="form-control" name = "year" id = "year"style="width:100px;height:50px;">
					<option value="2017">2017</option>
			</select>			
			<select  class="form-control" name = "month" id = "month"style="width:100px;height:50px;">
					<option value="01" <c:if test="${month=='01' }">selected="selected" </c:if>>JAN</option>
					<option value="02" <c:if test="${month=='02' }">selected="selected" </c:if>>FEB</option>
					<option value="03" <c:if test="${month=='03' }">selected="selected" </c:if>>MAR</option>
					<option value="04" <c:if test="${month=='04' }">selected="selected" </c:if>>APR</option>
					<option value="05" <c:if test="${month=='05' }">selected="selected" </c:if>>MAY</option>
					<option value="06" <c:if test="${month=='06' }">selected="selected" </c:if>>JUN</option>
					<option value="07" <c:if test="${month=='07' }">selected="selected" </c:if>>JUL</option>
					<option value="08" <c:if test="${month=='08' }">selected="selected" </c:if>>AUG</option>
					<option value="09" <c:if test="${month=='09' }">selected="selected" </c:if>>SEP</option>
					<option value="10" <c:if test="${month=='10' }">selected="selected" </c:if>>OCT</option>
					<option value="11" <c:if test="${month=='11' }">selected="selected" </c:if>>NOV</option>
					<option value="12" <c:if test="${month=='12' }">selected="selected" </c:if>>DEC</option>							
			</select>
			<select  class="form-control" name = "day" id = "day"style="width:100px;height:50px;">
				<option value="1" <c:if test="${day== '01' }">selected="selected"</c:if>>1</option>
				<option value="2" <c:if test="${day== '02' }">selected="selected"</c:if>>2</option>
				<option value="3" <c:if test="${day== '03' }">selected="selected"</c:if>>3</option>
				<option value="4" <c:if test="${day== '04' }">selected="selected"</c:if>>4</option>
				<option value="5" <c:if test="${day== '05' }">selected="selected"</c:if>>5</option>
				<option value="6" <c:if test="${day== '06' }">selected="selected"</c:if>>6</option>
				<option value="7" <c:if test="${day== '07' }">selected="selected"</c:if>>7</option>
				<option value="8" <c:if test="${day== '08' }">selected="selected"</c:if>>8</option>
				<option value="9" <c:if test="${day== '09' }">selected="selected"</c:if>>9</option>
				<option value="10" <c:if test="${day== '10' }">selected="selected"</c:if>>10</option>
				<option value="11" <c:if test="${day== '11' }">selected="selected"</c:if>>11</option>
				<option value="12" <c:if test="${day== '12' }">selected="selected"</c:if>>12</option>
				<option value="13" <c:if test="${day== '13' }">selected="selected"</c:if>>13</option>
				<option value="14" <c:if test="${day== '14' }">selected="selected"</c:if>>14</option>
				<option value="15" <c:if test="${day== '15' }">selected="selected"</c:if>>15</option>
				<option value="16" <c:if test="${day== '16' }">selected="selected"</c:if>>16</option>
				<option value="17" <c:if test="${day== '17' }">selected="selected"</c:if>>17</option>
				<option value="18" <c:if test="${day== '18' }">selected="selected"</c:if>>18</option>					
				<option value="19" <c:if test="${day== '19' }">selected="selected"</c:if>>19</option>
				<option value="20" <c:if test="${day== '20' }">selected="selected"</c:if>>20</option>
				<option value="21" <c:if test="${day== '21' }">selected="selected"</c:if>>21</option>
				<option value="22" <c:if test="${day== '22' }">selected="selected"</c:if>>22</option>
				<option value="23" <c:if test="${day== '23' }">selected="selected"</c:if>>23</option>
				<option value="24" <c:if test="${day== '24' }">selected="selected"</c:if>>24</option>
				<option value="25" <c:if test="${day== '25' }">selected="selected"</c:if>>25</option>
				<option value="26" <c:if test="${day== '26' }">selected="selected"</c:if>>26</option>
				<option value="27" <c:if test="${day== '27' }">selected="selected"</c:if>>27</option>
				<option value="28" <c:if test="${day== '28' }">selected="selected"</c:if>>28</option>
				<c:if test="${month != '02' }">
				<option value="29" <c:if test="${day== '29' }">selected="selected"</c:if>>29</option>
				<option value="30" <c:if test="${day== '30' }">selected="selected"</c:if>>30</option>
				<c:if test="${month == '01' || month == '03' || month == '05' || month == '07' || month == '08' || month == '10' || month == '12' }">
				<option value="31" <c:if test="${day== '31' }">selected="selected"</c:if>>31</option>
				</c:if>
				</c:if>
			</select><p>
			
			<input type="file" id="file" name="file" required="required"><p>
			<h6 align="left">사진은 다시 등록해야 합니다</h6>
			<div class="form-group">
				<label class="control-label" for="inputSmall"><img src="${path }/icon/loc.png" width="23px" height="29px"></label>
	  			<input class="form-control input-sm" type="text" id="inputSmall" name = "location" value = "${content.d_location}">
			</div>
			
			<textarea id = "content" name ="content" rows="12" cols="100">${content.d_content}</textarea>
			
			<%-- 
			<input type="button" class="btn btn-primary" value="Emoticon" onclick="openChild()"> --%>
   			<input type="hidden" id="emotion" name="emotion" value = "${content.d_emotion}">   			
   			
			<br><input type="button" class="btn btn-danger" value = "확인" id="btn">
			<button class="btn btn-default" onclick="history.go(-1)">취소</button>
			</c:forEach>
		</form>
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