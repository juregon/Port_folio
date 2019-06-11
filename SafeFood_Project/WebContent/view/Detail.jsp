<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<style><%@ include file="basiclayout.css"%></style>
<style type="text/css">
#h3 {
	margin-left: 200px;
	margin-bottom: 30px;
}
.cat{
	width: 100px;
}
.table-hover {
	width: 500px;
	height: 200px;
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = google.visualization.arrayToDataTable([
			<% FoodNutrition fn = (FoodNutrition) request.getAttribute("fn");
			   Food f = (Food) request.getAttribute("f");
			   Member m = null;
			   if(request.getAttribute("m")!=null) {
				   m = (Member) request.getAttribute("m");
			   }			   
			   Double cal, car, protein, sugar, glucose, salt, chole, fat, transfat;
			   cal = Double.parseDouble(fn.getCal());
			   car = Double.parseDouble(fn.getCar());
			   if(fn.getProtein().equals("N/A"))protein=0.0;
			   else protein=Double.parseDouble(fn.getProtein());
			   if(fn.getSugar().equals("N/A"))sugar=0.0;
			   else sugar=Double.parseDouble(fn.getSugar());
			   if(fn.getGlucose().equals("N/A"))glucose=0.0;
			   else glucose=Double.parseDouble(fn.getGlucose());
			   if(fn.getSalt().equals("N/A"))salt=0.0;
			   else salt=Double.parseDouble(fn.getSalt());
			   if(fn.getChole().equals("N/A"))chole=0.0;
			   else chole=Double.parseDouble(fn.getChole());
			   if(fn.getFat().equals("N/A"))fat=0.0;
			   else fat=Double.parseDouble(fn.getFat());
			   if(fn.getTransfat().equals("N/A"))transfat=0.0;
			   else transfat=Double.parseDouble(fn.getTransfat());
			%>
				[ 'Nutrtion', 'Hi' ], [ '칼로리', <%=cal%> ], [ '탄수화물', <%=car%> ], [ '단백질', <%=protein%> ], [ '지방', <%=sugar%> ], 
				[ '당류', <%=glucose%> ], [ '나트륨', <%=salt%> ], [ '콜레스테롤', <%=chole%> ], [ '포화지방산', <%=fat%> ], [ '트랜스지방', <%=transfat%> ] ]);

		var options = {
			title : '${fn.getName()}'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}
</script>
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
				<!-- 
                <div class="item">
                   <img src="./slide2.jpg" alt="Second slide">               
                </div>
                <div class="item">
                   <img src="./slide3.jpg" alt="Third slide">                 
                </div>
                 -->
			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"> <span class="icon-prev"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				data-slide="next"> <span class="icon-next"></span>
			</a>
		</div>
		<!-- 검색창 -->
		<div>
			<form action="search.mvc" class="search" role="search" method="post">
				<img alt="" src="img/search.png" width="40px" height="40px">
				<input type="text" class="form-control" name="search" placeholder="검색어를 입력해 주세요">
				<button type="submit" class="btn btn-default">검색</button>
			</form>
		</div>
		<hr>
		<div id="result"></div>
		<div class="container" id="productset">
		<%
			String result = "";
			if(m!=null){
				String[] allergys = m.getAllergys();
				for(int i = 0;i < allergys.length;i++){
					if(f.getMaterials().contains(allergys[i])){
						result= result+allergys[i]+" ";
					}
				}
			}
		%>
			<div class="row">
				<div class="col-xs-5" id="img">
				<img src="img/<%=fn.getName()%>.jpg" width="80%"></div>
				<div class="col-xs-7">
					<table class="table table-hover">
						<tr>
							<td class="cat">제품명</td>
							<td><%= f.getName() %></td>
						</tr>
						<tr>
							<td class="cat">제조사</td>
							<td><%= f.getCompany() %></td>
						</tr>
						<tr>
							<td class="cat">원재료</td>
							<td><%= f.getMaterials() %></td>
						</tr>
						<tr>
							<td class="cat">알레르기</td>
							<td><%if(f.getAllergys()[0] == null){ %>
									<%="알러지 성분 없음" %>
								<%}else{ %>
										<%for(int i = 0; i < f.getAllergys().length; i++){ %>
											<%boolean check = false; %>
											<%for(int j = 0; m != null && j < m.getAllergys().length; j++){
												if(f.getAllergys()[i].equals(m.getAllergys()[j])){
													check = true;
												}
											}%>
											<%if(check == true){%>
												<span style="font-weight: bold; color:red"><%= f.getAllergys()[i] %></span>
											<%} %>
											<%if(check == false) {%>
												<span><%= f.getAllergys()[i] %></span>
											<%} %>
											<%if(i < f.getAllergys().length-1){ %>
											<%= ", " %> 
											<%} %>
									   <%}
								}%></td>
						</tr>
					</table>
					<!-- end of table -->
				</div>
			</div>
			<div class="row">
				<div class="col-xs-5" id="chart">
				<div id="piechart" style="display: block; width:80%; height:400px;"></div>
				</div>
				<div class="col-xs-7">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>영양성분</th>
								<th>제공량</th>
								<th>1일 권장 섭취량</th>
							</tr>
						</thead>
						<tr>
							<td>무게</td>
							<td><%= fn.getWeight() %></td>
						</tr>
						<tr>
							<td>칼로리</td>
							<td><%= fn.getCal() %></td>
						</tr>
						<tr>
							<td>탄수화물</td>
							<td><%= fn.getCar() %></td>
						</tr>
						<tr>
							<td>단백질</td>
							<td><%= fn.getProtein() %></td>
							<td>70</td>
						</tr>
						<tr>
							<td>지방</td>
							<td><%= fn.getSugar() %></td>
							<td>30g</td>
						</tr>
						<tr>
							<td>당류</td>
							<td><%= fn.getGlucose() %></td>
						</tr>
						<tr>
							<td>나트륨</td>
							<td><%= fn.getSalt() %></td>
						</tr>
						<tr>
							<td>콜레스테롤</td>
							<td><%= fn.getChole() %></td>
						</tr>
						<tr>
							<td>포화 지방산</td>
							<td><%= fn.getFat() %></td>
						</tr>
						<tr>
							<td>트랜스 지방</td>
							<td><%= fn.getTransfat() %></td>
						</tr>
						
					</table>
					<!-- end of table -->
				</div>
			</div>
			<!-- end of row -->
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