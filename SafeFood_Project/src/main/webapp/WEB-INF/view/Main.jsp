<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="container">
		<%
			if(request.getAttribute("status") != null) {
				String msg = (String) request.getAttribute("msg");
				out.println("<script>alert('"+msg+"');</script>");	
			}
		%>
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<% if(session.getAttribute("id") != null) {%>	
			<jsp:include page="sidebar.jsp" flush="false"/>
		<%} %>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
</body>
</html>