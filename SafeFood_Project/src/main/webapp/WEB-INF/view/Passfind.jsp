<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<style type="text/css">
.loginform {
	text-align: center;
}

.col-md-3 {
	text-align: left;
}

.row {
	padding-top: 5px;
	padding-bottom: 5px;
}

.button {
	height: 25px; color : white;
	background-color: #3c3c3c;
	margin-left: 3px;
	border: none;
	color: white;
}

.col-md-2 {
	text-align: right;
}

.loginform hr {
	width: 600px; 
	border : 0.5px solid #b4b4b4;
	align-self: center;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<div class="loginform">
			<div class="row">
				<div class="col-md-offset-3 col-md-3">
					<h2>비밀번호 찾기</h2>
				</div>
				<div class="col-md-offset-6"></div>
			</div>
				<hr>
			<form action="passfindMember.mvc" role="login">
				<div class="row">
					<div class="col-md-offset-3 col-md-3">
						<span>ID</span>
					</div>
					<div class="col-md-3">
						<input type="text" name="id">
					</div>
					<div class="col-md-offset-3"></div>
				</div>
				<div class="row">
					<div class="col-md-offset-3 col-md-3">
						<span>이름</span>
					</div>
					<div class="col-md-3">
						<input type="text" name="name">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-6 col-md-3">
						<button type="submit" class="button">비밀번호 찾기</button>
					</div>
					<div class="col-md-offset-4"></div>
				</div>
			</form>
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
</body>
</html>