<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../diarycss.jsp" %>
<%@ include file="../login/sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>이모티콘 선택</title>
 
    <script type="text/javascript">
        function setParentText(){
             var value1 =  document.getElementsByName('subemotion');
            	 /*  document.getElementByName("subemotion").value */
             for(var i = 0;i<value1.length;i++){
            	 if(value1[i].checked == true){
            		 opener.document.getElementById("emotion").value = value1[i].value
            	 }
             }
             window.close();
        }
   </script>
 	
</head>
<body>
    <br>
    <b><font size="5" color="gray">이모티콘 선택</font></b>
    <br><br>
    <form>
    <table class="table table-striped table-hover"> 
    <tr>
	    <td>
	    	<img src="emotion/happy.png" width="20px" height="20px">
	    </td>
	    <td>
	    	<img src="emotion/like.png" width="20px" height="20px">
	    </td>
	    <td>
	    	<img src="emotion/like2.png" width="20px" height="20px">
	    </td>
	    <td>
	    	<img src="emotion/love.png" width="20px" height="20px">
	    </td>
	    <td>
	    	<img src="emotion/sad.png" width="20px" height="20px">
	    </td>
	</tr>
    <tr>
		<td>
		    	<input type="radio" name="subemotion" value="happy.png">
	    </td>
	    <td>
		    	<input type="radio" name="subemotion" value="like.png">
	    </td>
	    <td>
		    	<input type="radio" name="subemotion" value="like2.png">
	    </td>
	    <td>
		    	<input type="radio" name="subemotion" value="love.png">
	    </td>
	    <td>
		    	<input type="radio" name="subemotion" value="sad.png"> 
	    </td>
	</tr>    
    </table>    
     <input type="button" value="완료" onclick="setParentText()">
    <br><br>
    <input type="button" value="닫기" onclick="window.close()">
    </form>
  
   
</body>
</html>