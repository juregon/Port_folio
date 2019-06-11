<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<style><%@ include file="basiclayout.css"%></style>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
	<div class="container">
		<header>
			<!-- nav : 네비게이션 시작을 알려주는 태그 (div로 해도되긴하지만 알려주기위해 사용)
			 navbar navbar-default: 배경 색상과 테투리를 지정해 주는 역할
			 navbar-fixed-top: 화면 상단에 고정 -->
			<nav class="navbar navbar-default navbar-fixed-top navbar-inverse"
				role="navigation">
				<div class="container">
					<div class="navbar-header">
						<!-- 화면 사이즈가 크면 안보임, 모바일 정도로 작아지면 보임 -->
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-ex1-collapse">
							<span class="sr-only">toggle navigation</span>
							<!-- screen reader only  없어도 되는 코드임. 화면상 아무 일 없다-->
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="main.mvc"><img
							src="https://edu.ssafy.com/asset/images/logo.png" height="50px"
							width="70px"></a>
					</div>
					<!-- class="navbar-header" -->

					<!-- navbar-collapse: 화면 사이즈가 작으면 안보임 -->
					<div
						class="collapse navbar-collapse navbar-left navbar-ex1-collapse">
						<ul class="nav navbar-nav" id="menu">
							<li><a href="#">공지 사항</a></li>
							<li><a href="list.mvc">상품 정보</a></li>
							<li><a href="#">베스트 섭취 정보</a></li>
							<li><a href="#">내 섭취 정보</a></li>
							<li><a href="#">예상 섭취 정보</a></li>
						</ul>
					</div>
					<div
						class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
						<ul class="nav navbar-nav" id="menuMember">
							<% if(session.getAttribute("id") != null) {
							String id = (String) session.getAttribute("id");
							%>
							<li><a href="logout.mvc">Logout</a></li>
							<li><a href="memberInfo.mvc">회원정보</a></li>
							<%}else{ %>
							<li><a href="login.mvc">Login</a></li>
							<li><a href="join.mvc">Join</a></li>
							<%} %>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	</div>
	</body>
</html>