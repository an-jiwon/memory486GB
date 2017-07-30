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
            window.name = "diaryWriteForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");n
            openWin = window.open("diaryWriteFormsub",
                    "Emoticon","width=400, height=300, resizable = no, scrollbars = no");    
        }
        window.onload = function() {
           		document.getElementById('btn').onclick = function() {
           		
           		document.getElementById('frm').submit();
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
	/* String id = (String)session.getAttribute("id");	 */
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
		<form id="frm" action="diaryWrite" method="post" enctype="multipart/form-data">
			<input type = "hidden" name="id" value="${id}">
			<select  class="form-control" name = "year" id = "year"style="width:100px;height:50px;">
					<option value="2017">2017</option>
			</select>			
			<select  class="form-control" name = "month" id = "month"style="width:100px;height:50px;">
					<option value="1">JAN</option>
					<option value="2">FEB</option>
					<option value="3">MAR</option>
					<option value="4">APR</option>
					<option value="5">MAY</option>
					<option value="6">JUN</option>
					<option value="7">JUL</option>
					<option value="8">AUG</option>
					<option value="9">SEP</option>
					<option value="10">OCT</option>
					<option value="11">NOV</option>
					<option value="12">DEC</option>					
			</select>
			<select  class="form-control" name = "day" id = "day"style="width:100px;height:50px;">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>					
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select><p>
			
			<input type="file" id="file" name="file" required="required"><p>
			

			<div class="form-group">
				<label class="control-label" for="inputSmall"><img src="icon/loc.png" width="23px" height="29px"></label>
	  			<input class="form-control input-sm" type="text" id="inputSmall" name = "location" required="required">
			</div>
			<textarea id = "content" name ="content" rows="12" cols="100" required="required"></textarea>
			
		 	<!-- <input type="button" value="Emoticon" class="btn btn-primary" onclick="openChild()">  		-->	
			
			<input type="hidden" id="emotion" name="emotion" value="none.jpg">
			
			<br><input type="button" class="btn btn-danger" value = "확인" id="btn">
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