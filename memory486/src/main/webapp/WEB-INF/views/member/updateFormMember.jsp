<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	padding-top:2%;
	padding-bottom:2%;
	padding-left: 30%;
	padding-right: 30%;
}

.coupleId{
	float: left;
	width: 82%;
	margin-right: 10px;
}

	.upload {  
  position: absolute;
  width: 1px; 
  height: 1px; 
  padding: 0; 
  margin: -1px; 
  overflow: hidden; 
  clip:rect(0,0,0,0); 
  border: 0;
}
.upload-name {
	padding : 9px 12px 7px;
	background-color: #f5f5f5; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; 
	-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
	-moz-appearance: none; appearance: none;
}
</style>
<%-- <% String id = (String)session.getAttribute("id"); %> --%>
<script type="text/javascript">
	$(document).ready(function(){ 
		var fileTarget = $('.upload');
		fileTarget.on('change', function(){ // 값이 변경되면
			if(window.FileReader){ // modern browser 
				var filename = $(this)[0].files[0].name; 
			} else { // old IE 
				var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
				} // 추출한 파일명 삽입 
				$(this).siblings('.upload-name').val(filename); 
				}); 
		
			$('#plocation').click(function() {
				window.open("locationPop", "", "width=400, height=550, scrollbars=0, location=0");
			});
		}); 
	
	function uploadbtn() {
		frm.upfile.click();
	}

	function update() {
		if (confirm("수정하시겠습니까?") == true){    //확인
			document.frm.submit();
		}else{   //취소
		    return false;
		}
	}
	function cancel() {
		if (confirm("취소하시겠습니까?") == true){    //확인
			location.href="main?id=${id}";
		}else{   //취소
		    return false;
		}
	}
	function couplechk() {													 //커플 신청
		if (!frm.coupleId.value) { 
			alert("아이디 입력후에 체크하세요");
			frm.coupleId.focus();  
			return false;		
			}
			window.open("couplechk?coupleid="+frm.coupleId.value,"",
			"width=480 height=220");
			
	}
	function breakup() {
		if (confirm("${msg}") == true){    //확인
			if (confirm("신중히 생각해 주십시오. 커플과의 연결을 끊겠습니까?") == true){    //확인
				location.href="breakup?id=${id}";
			}else{   //취소
			    return false;
			}
		}else{   //취소
		    return false;
		}
	}
	
	function deleteMember() {
		if (confirm("탈퇴하시겠습니까?") == true){    //확인
			location.href="deleteMember?id=${id}";
		}else{   //취소
		    return false;
		}
	}
	
	function main() {
		location.href="main?id=${id}";
	}
	
	
</script>
</head>
<body>
<div class="wrap">
<form action="updateMember" method="post" enctype="multipart/form-data" onsubmit="return update()" name="frm">

			<!-- <th>MY ID</th> -->
			<div class="form-group"><label class="control-label" for="disabledInput">MY ID</label>
			<input type="text" id="disabledInput" value="${member.memberID }" class="form-control" disabled="disabled">
			<input type="hidden" name="memberID" value="${member.memberID }"></div>

			 <!-- <th>COUPLE ID</th> -->
			<div class="form-group"><label class="control-label" for="focusedInput">COUPLE ID</label>
			<div >
			<input type="text"  name="coupleId" id="focusedInput" value="${yourID}" class="form-control coupleId">
			<c:if test="${msg != null }">
			<span class="input-group-btn">
			<input type="button" class="btn btn-danger" value="BREAK UP" onclick="breakup()">
			</span>
			</c:if>
			<c:if test="${msg == null }">
			<span class="input-group-btn">
			<input type="button" class="btn btn-danger" value="COUPLE ID" onclick="couplechk()">
			</span>
			</c:if></div>
			</div> 
	
			<!-- <th>NAME</th> -->
			<div class="form-group"><label class="control-label" for="focusedInput">NAME</label>
			<input type="text" name="name" id="focusedInput" required="required" class="form-control" value="${member.name }">
			</div>

			<!-- <th>PASS</th> -->
			<div class="form-group">
			<label class="control-label" for="focusedInput">PASS</label>
			<input type="password"  id="focusedInput" name="memberPass" required="required" value="${member.memberPass }" class="form-control">
			</div>

			<!-- <th>E-mail</th> -->
			<div class="form-group"><label class="control-label" for="focusedInput">E-mail</label>
			<input type="email" id="focusedInput" name="email" required="required" value="${member.email }" class="form-control" placeholder="memory@memory.com">
			</div>
		
			<!-- <th>COUNTRY/CITY</th> -->
			<label class="control-label" for="inputDefault" >LOCATION</label>
				<input type="text" value="${member.m_location }" readonly="readonly" class="form-control input-sm" id="inputSmall" name="m_location" required="required" style="text-align: center;">
				<a class="btn btn-default" id="plocation" style="width:99%;" >SELECT YOUR LOCATION</a>
			</div>
		
			<!-- <th>PIC</th> -->
			<div class="form-group"><label class="control-label" for="focusedInput">PIC</label>
			<input class="upload-name" value="파일선택" disabled="disabled"> 
			<input type="button" class="btn btn-danger" onclick="uploadbtn()" value="파일업로드" >
			<input type="file" id="focusedInput" name="upfile" value="${member.m_pic }" class="upload">
			</div>
	
			<!-- <th>LAYOUT</th> -->
			<div class="form-group"><label class="control-label" for="focusedInput">LAYOUT</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${member.layout == null }">
			<div style="display: inline-block;" ><input type="radio"  value="left" name="layout"  >LEFT &nbsp;
			<input type="radio"   value="right" name="layout" >RIGHT<p></div>
			</c:if>
			<c:if test="${member.layout != null }">
			<div style="display: inline-block;" ><input type="radio"  value="left" name="layout" <c:if test="${member.layout == 'left' }"> checked="checked"</c:if>>LEFT &nbsp;
			<input type="radio"   value="right" name="layout" <c:if test="${member.layout == 'right' }"> checked="checked"</c:if>>RIGHT<p></div>
			</c:if>
			</div>
		
	<div align="center">
	<input type="button" class="btn btn-danger" onclick="main()" value="MAIN">
		<input type="submit" class="btn btn-danger" value="수정">
		<input type="button" class="btn btn-default" value="취소" onclick="cancel()">
	</div>
	
</form>
<div align="center" style="width: 100%; margin-top: 10">
   			<img alt="탈퇴하기" src="img/memoryImg/outt.gif" 
	width="200px" height="200px" onclick="deleteMember()" style="cursor: pointer; " >

   		</div>
</div>
</body>
</html>