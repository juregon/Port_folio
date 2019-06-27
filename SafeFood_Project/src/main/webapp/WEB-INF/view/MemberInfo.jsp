<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import=" com.ssafy.model.dto.* "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 조회</title>
<style type="text/css">
.col-md-2, .col-md-3, .col-md-5, .col-md-6, .col-md-8 {
	text-align: left;
}

.row {
	padding-top: 5px;
	padding-bottom: 5px;
}

.button {
	height: 25px;
	color: white;
	background-color: #3c3c3c;
	margin-left: 3px;
	border: none;
	color: white;
}

.col-md-4 {
	text-align: right;
}

.memberinfo hr {
	width: 900px;
	border: 0.5px solid #b4b4b4;
	align-self: center;
}

.select {
	height: 25px;
}

table {
	width: 900px;
	text-align: left;
	font-size: 15pt;
}

th {
	color: gray;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<%
			if(request.getAttribute("member") != null) {
				Member m = (Member) request.getAttribute("member");
		%>
		<div class="memberinfo">
			<div class="row">
				<div class="col-md-offset-2 col-md-8">
					<h2>회원 정보 조회</h2>
				</div>
				<div class="col-md-offset-2"></div>
			</div>
			<hr>
			<form action="modMember.mvc" role="join">
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>ID</span>
					</div>
					<div class="col-md-5">
					<input type="text" style="border: none; background-color: transparent" name="id" value="<%=m.getId()%>">
					</div>
					<div class="col-md-offset-2"></div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이름</span>
					</div>
					<div class="col-md-5">
						<span><%=m.getName()%></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>주소</span>
					</div>
					<div class="col-md-5">
						<span><%=m.getAddress()%></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>연락처</span>
					</div>
					<div class="col-md-5">
						<span><%=m.getPhone()%></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이메일</span>
					</div>
					<div class="col-md-5">
						<span><%=m.getEmail()%></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>알러지정보</span>
					</div>
					<div class="col-md-5">
						<%	if(m.getAllergys() != null) {
								String[] al = m.getAllergys();
								for (int i = 0; i < al.length; i++) {
						%>
						<%=al[i]%>
						<%
							if (i < al.length - 1) {
						%>
						,
						<%
							}
							}}
						%>

					</div>
				</div>

				<div class="row">
					<div class="col-md-offset-6 col-md-4">
						<button class="button" type="submit">회원 정보 수정</button>
						<input type="button" class="button" value="회원 탈퇴" onclick="location.href='withdrawl.mvc'"></button>
					</div>
					<div class="col-md-offset-2"></div>
				</div>
			</form>
			<%} %>
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
</body>
</html>