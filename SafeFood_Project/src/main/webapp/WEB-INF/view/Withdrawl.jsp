<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원탈퇴</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style><%@include file="basiclayout.css"%></style>

<style type="text/css">
#h2 {
	margin-top: 50px;
	margin-left: 20px;
	color: black;
}

#h3 {
	margin-left: 20px;
	color: red;
}

#cancel{
background-color: highlight;
}
</style>


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
							<li><a href="intakeInfo.mvc">내 섭취 정보</a></li>
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
	<!-- 본문 -->
	<div class="container">
		<!-- 배너 -->
		<div id="carousel-example-generic" class="carousel slide">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<!-- <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
			</ol>
			<!-- Carousel items -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="img/banner_img.png" alt="First slide">
				</div>
				
			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"> <span class="icon-prev"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				data-slide="next"> <span class="icon-next"></span>
			</a>
		</div>
	<h2 id="h2">회원 탈퇴</h2>
	<h3 id="h3">탈퇴 전 꼭 읽어주세요.</h3>
	<hr>
		<h4 class="text-primary">개인정보 취급안내</h4>
		<table class="table">
			<thead>
				<tr>
					<th>구분</th>
					<th>재가입기준(새로운ID)</th>
					<th>재가입기준(동일ID)</th>
					<th>비고</th>
				</tr>
			</thead>
			<tr>
				<td>거래내역이 없는 회원</td>
				<td>가능</td>
				<td>불가능</td>
				<td>예전 사용했던 아이디로 재가입 불가능, 새로운 아이디로만 가입 가능</td>
			</tr>

			<tr>
				<td>거래내역이 있는 회원</td>
				<td>가능</td>
				<td>불가능</td>
				<td>개인구매회원의 경우 항상 새로운 아이디로만 가입 가능</td>
			</tr>

			<tr>
				<td>영구정지 회원</td>
				<td>불가능</td>
				<td>불가능</td>
				<td>영구정지회원은 재가입 자체가 불가능함</td>
			</tr>
		</table>
		<hr>

		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> 회원탈퇴 유의사항 전문보기 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">회원탈퇴를 위해선 아래 5가지 조건 확인이 필요합니다. 판매 또는
						구매가 진행중인 상품이 없어야 합니다. 마이너스 또는 현금성 판매예치금이 없어야 합니다.
						(-) Smile Cash가 없어야 합니다. 가상계좌 잔액이 없어야 합니다. 회원탈퇴 시 보유하고 계신 쿠폰은 즉시 소멸되며, 동일한 아이디로
						재가입 하더라도 복원되지 않습니다.</div>
				</div>
			</div>

		</div>
		
			<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> 고객센터 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">Tel : 1588-0184 (평일 09:00~18:00)
스마일클럽 & VVIP : 1522-8900 (365일 09:00~18:00)
경기도 부천시 원미구 부일로 223 (상동) 투나빌딩 6층
Fax : 02-589-8829Mail : information@corp.auction.co.kr
					</div>
			</div>

		</div>
		</div>
	<br>
	<br>
	
<div class="btn-toolbar">
      <div class="btn-group">
         <input type="button" class="btn btn-default" value="회원탈퇴 " onclick="location.href='withdrawlOk.mvc'"></button>
      </div> 
      <div class="btn-group">
         <input type="button" id="cancel" value="취소" onclick="location.href='main.mvc'" class="btn btn-default"></button>      
      </div>
    </div>


		<!-- end of container -->
	</div>
	
	
	<div class="container-fluid">
		<footer>
			<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse"
				role="navigation">
				<div class="container">
					<div
						class="collapse navbar-collapse navbar-left navbar-ex1-collapse">
						<h4>findUs</h4>
						<hr>
						<ul class="footerLink">
							<li><a href="#">(SSAFY) 서울시 강남구 테헤란로 멀티스퀘어</a></li>
							<li><a href="#">1544-9001</a></li>
							<li><a href="#">admin@ssafy.com</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</footer>
	</div>
</body>
</html>