<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/sessionCheck.jsp" %>
<%@ include file="../header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href='${path}/css/fullcalendar.css' rel='stylesheet' />
<link href='${path}/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='${path}/js/moment.min.js'></script>
<script src='${path}/js/jquery.min.js'></script>
<script src='${path}/js/fullcalendar.js'></script>
<script src='${path}/js/locale-all.js'></script>

<style type='text/css'>

	body {
		margin-top: 40px;
		margin-bottom: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}

	#calendar {
		width: 900px;
		margin: 0 auto;
		}
		
	
	@font-face {
		font-family: "NanumBrush";
		src:url("fonts/NanumBrush.ttf") format("truetype");
	}
	#dd {font-family: NanumBrush;
	font-size:20px;}

</style>
<%-- <% String id = (String)session.getAttribute("id"); %> --%>
<script type="text/javascript">
$(document).ready(function() {	
	
	$(".ddaydrop").hide();
	$("#showdday").click(function() {
		$(".ddaydrop").toggle("slow");
	});
	
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$.ajax({
	    url:"calendarList?id=${id}",
	    dataType:"json",
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    type: 'POST',
	    success:function(data){
	        display(data);
	    },
	    error : function(request, status, error) {
	     if (request.status != '0') {
	      alert("code : " + request.status + "\r\nmessage : "
	        + request.reponseText + "\r\nerror : " + error);
	     }
	    }
	 });
});

function display(data) {

	/* var json_data = JSON.stringify(data);
    var obj = JSON.parse(json_data); */
	var events_array = [];
 
	for(var i in data){
		if(data[i].toDate==null || data[i].toDate=='') {
			events_array.push ({
				id:data[i].c_no,
				writer:data[i].c_writer,
				title:data[i].c_content,
				start:data[i].fromDate,
				allDay:true,
				color: '#E26868'
			});
		}else {
			if(data[i].c_writer != '${id}') {
			events_array.push ({
				id:data[i].c_no,
				writer:data[i].c_writer,
				title:data[i].c_content,
				start:data[i].fromDate,
				end:data[i].toDate,
				color:'#4DAF8A'
			});
			}else {
				events_array.push ({
					id:data[i].c_no,
					writer:data[i].c_writer,
					title:data[i].c_content,
					start:data[i].fromDate,
					end:data[i].toDate,
				});
			}
			
		 	
		}
		
	}
	
	calendarEvent(events_array);
}

function calendarEvent(events_array) {
var calendar = $('#calendar').fullCalendar({
		
		header: {
			left: 'prev,next, today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},

		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			var dt_start = moment(start).format('YYYY-MM-DD');
		    var dt_end = moment(end).format('YYYY-MM-DD');

			window.open("insertCalendarForm?date="+dt_start,"",
    		"width=500 height=580");
			 /*  var title = prompt('일정을 입력하세요.');
			if (title) {
			
				calendar.fullCalendar('renderEvent',
					{
						title: title,
						start: start,
						end: end,
						allDay: allDay
					},
					true // make the event "stick"
				);
			} 
			calendar.fullCalendar('unselect');  */
			
		},
		editable: true,
		/* events: [
			{
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'insertCalendarForm'
			}
		], */
		 events:	events_array,//events

		eventClick:function(event) {
			if(event.writer == '${id}')
                window.open("updateCalendarForm?c_no="+event.id,"",
                		"width=500 height=580");
			else
				return false;
        },
       /*  dayClick:function(date) {
            if(event.url) {
                window.open("insertCalendarForm/date/"+date,"",
                		"width=500 height=500");
                return false;
            }
        } */
		

	}); 
}

function setdday(c_no) {
	if (confirm("D-Day로 설정 하시겠습니까?") == true){    //확인
		location.href="setdday?id=${id}&c_no="+c_no;
	}else{   //취소
	    return false;
	}
	
} 

</script>

</head>


<body>
<div id="dday" style="float: left;"><img id="showdday" src="${path }/img/memoryImg/dday.png" width="60px" height="50px" style="cursor: pointer;">
	<div class="ddaydrop" style="position: absolute; margin-left: 20px;">
		<table id="dd" class="table table-striped table-hover ">
		<c:if test="${empty ddayList }">
			<tr><td colspan="2">기념일이 없습니다.</td></tr>
		</c:if>
		<c:if test="${not empty ddayList }">
			<c:forEach var="dday" items="${ddayList }">
			<tr class="active" style="cursor: pointer;" onclick="setdday(${dday.c_no})">
				<td style="padding-right: 10px; line-height:20px; width: 100px;">${dday.c_content }</td>
				<td valign="middle">${dday.fromText }</td>
			</tr>
			</c:forEach>
		</c:if>
		</table>
	</div>
</div>
<div id='calendar'></div>
<div align="center" style="width: 100%; margin-top: 10">
   			<button class="btn btn-danger" onclick="location.href='main?id=${id}'">MAIN</button>
   			<button class="btn btn-danger" onclick="location.href='diary'">DIARY</button>
   			<button class="btn btn-danger" onclick="location.href='photoListMain?id=${id}'">OUR PHOTOS</button>
   			<button class="btn btn-danger" onclick="location.href='chatWindow?memberID=${id}'">CHAT</button>
   		</div>
</body>
</html>