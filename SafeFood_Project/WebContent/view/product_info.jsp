<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="com.ssafy.vo.Food" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product_info</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0//css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
<style>
th {
	width: 75px;
}
</style>
<script type="text/javascript">
$(document).ready(
		function() {
			var ctx = document.getElementById("myChart").getContext('2d');
						var myDoughnutChart = new Chart(ctx, {
							type : 'doughnut',
							data : {
								datasets : [ {
									data : [ 10, 2, 10, 15 ],
									backgroundColor : [
											'rgba(255, 99, 132, 0.2)',
											'rgba(54, 162, 235, 0.2)',
											'rgba(255, 206, 86, 0.2)',
											'rgba(75, 192, 192, 0.2)',
											'rgba(153, 102, 255, 0.2)',
											'rgba(255, 159, 64, 0.2)',
											'rgba(200, 122, 64, 0.2)'

									]
								} ],

								// These labels appear in the legend and in the tooltips when hovering different arcs
								labels : [ '탄수화물', '단백질', '지방', '당류', '나트륨', '콜레스테롤', '포화 지방산', '트랜스지방' ],

							},
							options : {
								responsive : false
							}
						});
		})

</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@ include file="navigation.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<% request.setCharacterEncoding("euc-kr"); %>
				<% Food temp = (Food)request.getAttribute("food"); %>
				<h2>제품 정보</h2>
				<hr>
				<table class="table">
					
					<tbody>
						<tr>
							<td rowspan="5"><img style="height: 300px; width: 300px"
								src="<%= temp.getImg() %>" /></td>
						</tr>
						<tr>
							<th scope="row">제품명</th>
							<td><%= temp.getName() %></td>
						</tr>
						<tr>
							<th scope="row">제조사</th>
							<td><%= temp.getMaker() %></td>
						</tr>
						<tr>
							<th scope="row">원재료</th>
							<td><%= temp.getMaterial() %></td>
						</tr>
						<tr>
							<th scope="row">알레르기 성분</th>
							<td><%= temp.getAllergy() %></td>
						</tr>
					</tbody>
				</table>
				<hr>	
				<div class="col-md-2 col-md-offset-9">
					수량
					<input style="margin-bottom:5px" type="number">
					<br>
					<button type="button" class="btn btn-primary">추가</button>
					<button type="button" class="btn btn-primary">찜</button>
				</div>
				<br>
				<div class="col-md-7 col-md-offset-1"> 
					<canvas id="myChart" width="400px" height="400px"></canvas>
				</div>
				<div class="col-md-4">
					<table style=" margin-top:40px;" class="table table-striped">
								<tbody>
									<tr>
										<th colspan="2">총 칼로리</th>
										<td></td>
										<td>120</td>
									</tr>
									<tr>
										<th colspan="2">탄수화물</th>
										<td></td>
										<td>10</td>
									</tr>
									<tr>
										<th colspan="2">단백질</th>
										<td></td>
										<td>2</td>
									</tr>
									<tr>
										<th colspan="2">지방</th>
										<td></td>
										<td>10</td>
									</tr>
									<tr>
										<th colspan="2">당류</th>
										<td></td>
										<td>15</td>
									</tr>
									<tr>
										<th colspan="2">나트륨</th>
										<td></td>
										<td>0</td>
									</tr>
									<tr>
										<th colspan="2">콜레스테롤</th>
										<td></td>
										<td>0</td>
									</tr>
									<tr>
										<th colspan="2">포화 지방산</th>
										<td></td>
										<td>0</td>
									</tr>
									<tr>
										<th colspan="2">트랜스지방</th>
										<td></td>
										<td>0</td>
									</tr>
								</tbody>
							</table>
				</div>
			</div>
		</div>
	</div>
	<!-- footer 바 -->
	<%@ include file="footer.jsp"%>
</body>
</html>

































