<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select your location</title>
<style type="text/css">
a {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#country').find('ul>li').click(function() {
			var sel = $(this);
			sel.addClass('active');
			$('#country').find('ul>li').not(sel).removeClass('active');
		});	
	
	 	$('#country #korea_city').click(function() {
			$('#city').css('display','block');
		});
		 	
		$('.other').click(function() {
			$('#city').css('display','none');
		});
		
		$('#cancle').click(function() {
			window.close();
		});
		
		$('#country ul li a').click(function() {
			var txt = $(this).text();
			$('#hide_value').attr('value',txt);
		});
		
	});	
</script>
</head><body>

<form action="check_location" name="frmpop">
	<c:if test="${not empty loc1 || loc2}">
		<script type="text/javascript">
		$(function() {
			window.opener.document.frm.inputSmall.value = document.frmpop.hide_value.value;
			self.close();			
		});
		</script>
	</c:if>	
	
	<input type="hidden" name="location1" id="hide_value" value="${loc1 } ${loc2}">
	
	<div id="country" align="center" style="margin:50px; margin-top:10px;">
		<ul class="nav nav-pills">
		  <li><a id="korea_city">Korea</a></li>
		  <li><a class="other">Japan</a></li>
		  <li><a class="other">China</a></li>
		  <li><a class="other">Russia</a></li>
		</ul>
		
		<ul class="nav nav-pills" style="padding-left: 20px;"> 
		  <li><a class="other">America</a></li>
		  <li><a class="other">Africa</a></li>
		  <li><a class="other">Australia</a></li>
		</ul>
		
		<ul class="nav nav-pills">
		  <li><a class="other">United Kingdom</a></li>
		  <li><a class="other">France</a></li>
		  <li><a class="other">Italy</a></li>
		</ul>
	</div>
	
	<div id="city" style="display:none;">
		<div align="center">
			<div id="korea_city">
				<p>
				<input type="radio" name="location2" value="seoul">서울 SEOUL <p>
				<input type="radio" name="location2" value="gyeonggi" >경기도 GYEONGGI <p>
				<input type="radio" name="location2" value="gangwon" >강원도 GANGWON <p>
				<input type="radio" name="location2" value="chungcheong" >충청도 CHUNGCHEONG <p>
				<input type="radio" name="location2" value="jeolla" >전라도 JEOLLA <p>
				<input type="radio" name="location2" value="jeju" >제주도 JEJU-ISLAND <p>
			</div>
		</div>
	</div>
	
	<div id="ok" align="center" style="margin:50px;">
		<input type="submit" value="확인" class="btn btn-danger"> &nbsp;
		<input type="button" value="취소" class="btn btn-danger" id="cancle">
	</div> 
</form>

</body></html>