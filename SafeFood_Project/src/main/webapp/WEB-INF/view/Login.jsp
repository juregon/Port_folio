<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
	charset="utf-8"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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
	height: 25px;
	color: white;
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
	border: 0.5px solid #b4b4b4;
	align-self: center;
}
</style>

</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" flush="false" />
		<hr>
		<%
			if (request.getAttribute("status") != null) {
				String status = (String) request.getAttribute("status");
				if (status.equals("fail")) {
		%>
		<div class="alert alert-danger alert-dismissible">
			<%=(String) request.getAttribute("msg") %>
		</div>
		<%
			}
			}
		%>
		<div class="loginform">
			<div class="row">
				<div class="col-md-offset-3 col-md-3">
					<h2>LOGIN</h2>
				</div>
				<div class="col-md-offset-6"></div>
			</div>
			<hr>
			<form action="loginMember.mvc" method="post" role="login">
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
						<span>PASSWORD</span>
					</div>
					<div class="col-md-3">
						<input type="password" name="pass">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-6 col-md-3" style="display: inline-block;">
						<button type="submit" class="button" style="height: 32px" >로그인</button>
						<div id="naver_id_login" style="text-align: right; display: inline-block;">
							<a href="${url}"> <img width="110" height="32"
								src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
							</a>
						</div>
					</div>
<!-- 					<div class="col-md-offset-4"></div> -->
				</div>
				<div class="row">
					<div class="col-md-offset-6 col-md-3">
						<input type="button" class="button" style="height: 32px" value="비밀번호 찾기"
							onclick="location.href='passfind.mvc'"> <input
							type="button" class="button" style="height: 32px" value="회원가입"
							onclick="location.href='join.mvc'">
					</div>
				</div>
			</form>
			<!-- 		<div id="naver_id_login" style="text-align:right"> -->
			<%-- 			<a href="${url}">  --%>
			<!-- 				<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/> -->
			<!-- 			</a> -->
			<!-- 		</div> -->
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false" />
		</footer>
	</div>
</body>
</html>