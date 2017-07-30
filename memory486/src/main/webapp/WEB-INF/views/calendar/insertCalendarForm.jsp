<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path }/css/bootstrap-datetimepicker.css">
<script src="${path }/js/bootstrap-datetimepicker.js"></script>
<script src="${path }/js/moment-with-locales.min.js"></script>



<title>Insert title here</title>
<style type="text/css">
	body {
	padding-top:2%;
	padding-bottom:2%;
	padding-left: 15%;
	padding-right: 15%;
}

.category {
	width:70%;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#datetimepicker2").hide();
	$("#ani").click(function(){
		$("#category").val($("#ani").text());
		$("#datetimepicker2").hide();
		$(".fromText").val('${date}');
		$(".fromText").attr("data-format","yyyy-MM-dd");
		$(".toText").val(null);
		$('#datetimepicker1').datetimepicker('remove');
		 $('#datetimepicker1').datetimepicker({
		        format: "yyyy-mm-dd",
		        autoclose: true,
		        todayBtn: true,
		        todayHighlight:true,
		        minView:2,
		        pickerPosition: "bottom-left"
		    });
		
	});
	$("#sc").click(function(){
		$("#category").val($("#sc").text());
		$("#datetimepicker2").show();
		$(".fromText").val('${date} 00:00');
		$(".fromText").attr("data-format","yyyy-MM-dd a hh:mm");
		$(".toText").val('${date} 00:00');
		$('#datetimepicker1').datetimepicker('remove');
		 $('#datetimepicker1').datetimepicker({
		        format: "yyyy-mm-dd P hh:ii",
		        autoclose: true,
		        todayBtn: true,
		        todayHighlight:true,
		        showMeridian: true,
		        pickerPosition: "bottom-left"
		    });
		 $('#datetimepicker2').datetimepicker({
		        format: "yyyy-mm-dd P hh:ii",
		        autoclose: true,
		        todayBtn: true,
		        todayHighlight:true,
		        showMeridian: true,
		        pickerPosition: "bottom-left"
		    });
	});

});

	 
function cancel() {
	window.close();
}

function insert() {
if($("#category").val().length < 1 ) {
	alert("카테고리를 선택하세요.");	
	return false;
	}
}


</script>
</head>
<body>
<div>
<form action="insertCalendar" name="frm" method="post" onsubmit="return insert()">
  
	 <div class="btn-group" style="float: left; margin-right: 10px;">
	  <a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">CATEGORY &nbsp;<span class="caret"></span></a>
	  <ul class="dropdown-menu">
    	<li><a href="#" id="ani">ANNIVERSARY</a></li>
        <li class="divider"></li>
        <li><a href="#" id="sc">SCHEDULE</a></li>
	  </ul>
	</div>
 
  	<input type="text" id="category" onFocus="blur();" class="form-control category" name="c_category" required="required" readonly="readonly" ><p><br>
  	<%-- <%String id = (String)session.getAttribute("id");%> --%>
	<input type="hidden" name="c_writer" value="<%=id%>">
	<div class="form-group"><label class="control-label" for="focusedInput">CONTENT</label>
	<input type="text" id="focusedInput" class="form-control" name="c_content" required="required"></div>
	
	<div class="form-group"><label class="control-label" for="focusedInput">DATE</label>
		 <div class='input-group date' id='datetimepicker1'>
           <input id="focusedInput" data-format="yyyy-MM-dd" type="text" class="form-control fromText" value="${date }" name="fromText" required="required" readonly="readonly"> 
            <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </span>
         </div>
         <div class='input-group date' id='datetimepicker2'>
           <input id="focusedInput" data-format="yyyy-MM-dd a hh:mm" type="text" class="form-control toText" value="${date }" name="toText" readonly="readonly">
            <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
            </span>
         </div>

 
         	
	</div> 
	<div align="center">
		<input type="submit" class="btn btn-danger" value="등록">
		<input type="button" class="btn btn-default" value="취소" onclick="cancel()">
	</div>
</form>

</div>
</body>
</html>