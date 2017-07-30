<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MEMORY 486GB</title>


<style type="text/css">
	#wrap {top:10%;}
</style>

<script type="text/javascript">
	var websocket;

	$(document).ready(function() {
		connect();
	});

	function connect() {	//ws:workspace 약자 (ip찾기 : ipconfig - IPvs4주소)
		websocket = new WebSocket("ws://192.168.0.5:8181/memory486/chat-ws.do");
		/* websocket.onopen = onOpen; // 연결이 될 때 호출될 메소드 설정  */
		websocket.onmessage = onMessage; // 메시지가 왔을 때 호출될 메소드 설정 
		/* websocket.onclose = onClose; // 연결이 해제될 때 호출될 메소드 설정 */
	}
	
	function onOpen(event) {
		addMessage("${id}(${name1})님이 안녕하세요!");
	

	}
	
	function onMessage(event) {
		var data = event.data;
		addMessage(data);
	}
	
	function onClose(event) {
		addMessage("${id}(${name1})님이 퇴장하였습니다.");		
	}
	
	$(function() {
		$('#chatText').keypress(function(event) {
			var keycode = event.keyCode?event.keyCode:event.which;
			if (keycode == 13) send();
			event.stopPropagation();
		});
		$('#sendBtn').click(function() {
			send();
		});
	});
	
 	function send() {
		
			var name = '${member.name}';
			var msg = $('#chatText').val();
			if ('${id}'=='${member.memberID}' ) 
				websocket.send('msg:' + '<font color=blue><b>' + name + '</b> : ' + msg);
			else
				websocket.send('msg:' + '<font color=green><b>' + name + '</b> : ' + msg);	 
			$('#chatText').val('');
		
		/* if ('${coupleMember.m_online}' == 'on') {
			var name = '${coupleMember.name}';
			var msg = $('#chatText').val();
		
			websocket.send('msg:' + '<font color=green><b>' + name + '</b> : ' + msg);	 
			$('#chatText').val('');
		} */
	}
	
	function addMessage(msg) {
		var length = msg.length;
		if (length > 35) {
			$('#chatMsgArea').append(msg.substring(0,50) + '<p>' + msg.substring(50));
		} else
			$('#chatMsgArea').append(msg + '<br>');
		
	}
		
</script>
</head><body>


<div id="wrap" align="center">
	<div style="background-color:#f2dcdb; height:45px; width:45%; margin-bottom:20px;" align="center">
		<h3 style="color:#ff4136; padding-top: 10px; font:bold;" align="center">CHAT</h3>
	</div>

	<div id="chatArea" style="border: 1px solid #ff4136; height: 60%; width: 45%; overflow-y: auto;" align="center">
		<div id="chatMsgArea" align="left" style="margin: 5px;">
			 <c:if test="${member.m_online == 'on' }">
				<font>${member.memberID}(${member.name})님 안녕하세요!</font><p>
			</c:if>
			<c:if test="${coupleMember.m_online == 'on' }">
				<font>${coupleMember.memberID}(${coupleMember.name})님 안녕하세요!</font><p>
			</c:if>
			<c:if test="${member.m_online == 'off' }">
				<font>${member.memberID}(${member.name})님이 로그아웃하셨습니다.</font><p>
			</c:if>
			<c:if test="${coupleMember.m_online == 'off' }">
				<font>${coupleMember.memberID}(${coupleMember.name})님이 로그아웃하셨습니다.</font><p>
			</c:if> 
			<c:if test="${acceptchk == null or acceptchk == 'n' }">
				${msg }
			</c:if>
		</div>
	</div>
	 
	<div style="height: 40%; width: 45%; margin-top: 5px;">
		<c:if test="${not empty couple }">
			<textarea id="chatText" cols="41" rows="3" style="resize: none; width:78%; float: left;"></textarea>
		</c:if>	
		<c:if test="${empty couple }">
			<textarea  id="chatText" disabled="disabled" cols="41" rows="3" style="resize: none; width:78%; float: left;"></textarea>
		</c:if>
		<div style="float: left; padding: 10px; margin-left: 3px; width:20%;">
			<c:if test="${not empty couple}">
				<input type="button" value="SEND" id="sendBtn" class="btn btn-danger">
			</c:if>
			<c:if test="${empty couple }">
				<input type="button" value="SEND"  disabled="disabled" class="btn btn-danger disabled">
			</c:if>
		</div>
		<div align="center" style="width: 100%; padding-top: 30px; clear: both;">
   			<button class="btn btn-danger" onclick="location.href='main?id=${id}'">MAIN</button>
   			<button class="btn btn-danger">DIARY</button>
   			<button class="btn btn-danger" onclick="location.href='photoListMain?id=${id}'">PHOTO</button>
   			<button class="btn btn-danger" onclick="location.href='calendarMain?id=${id}'">CALENDAR</button>
   			
   		</div>
	</div>
	
</div>

</body>
</html>