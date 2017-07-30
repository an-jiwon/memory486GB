<%-- <%
	String id = (String)session.getAttribute("id");
	if (id == null || id=="") {
	response.sendRedirect("../login/loginForm.jsp");
	return;
	}
%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String id = (String)session.getAttribute("id");
	if (id == null || id.equals("") || id.equals("null")) {%>
	<script type="text/javascript">
	window.open("sessionloginForm","login","width=400  height=300 left=100 top=50");

	</script>
<%	return;
	}
%>