<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp"%>
<!DOCTYPE html >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
#blah {
	margin-top: 30px;
}
body {text-align: center; padding-top: 150px;}
</style>

<script type="text/javascript">
var isChange = false;
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$(document).ready(function(){ 
		var fileTarget = $('.upload');
		fileTarget.on('change', function(){ // 값이 변경되면
			isChange = true;
			if(window.FileReader){ // modern browser 
				var filename = $(this)[0].files[0].name; 
			} else { // old IE 
				var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
				} // 추출한 파일명 삽입 
				$(this).siblings('.upload-name').val(filename); 
				}); 
		}); 
	
	function uploadbtn() {
		form1.upfile.click();
	}
	
	function insert() {
		if(isChange) {
			if (confirm("사진을 업로드 하시겠습니까?") == true){    //확인
				document.form1.submit();
			}else{   //취소
			    return false;
			}
		}else {
			alert("사진을 선택하세요.");
			return false;
		}
	}
	
	function cancel() {
		if (confirm("취소하시겠습니까?") == true){    //확인
			window.close();
		}else{   //취소
		    return false;
		}
	}
</script>

</head>
<body>

<div class="form-group">
	<form id="form1" runat="server" method="post" action="insertPhoto" enctype="multipart/form-data" onsubmit="return insert()">
		<label class="control-label" for="focusedInput">PIC</label>
			<input class="upload-name" value="파일을 선택하세요." disabled="disabled"> 
		<input type="button" class="btn btn-danger" onclick="uploadbtn()" value="파일선택" >
		<input type="file" onchange="readURL(this);" id="focusedInput" name="upfile" class="upload" ><p>
		<img id="blah" width = "149px" height = "179px"	src="img/memoryImg/noImg.png" alt="이미지를 선택하세요." /><p>
		<input type="submit" value="파일업로드" class="btn btn-danger">
		<input type="button" value="취소" class="btn btn-default" onclick="cancel()">
	</form>
</div>


</body>
</html>