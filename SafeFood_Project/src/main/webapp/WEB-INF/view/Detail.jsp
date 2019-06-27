<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
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
			<%FoodNutrition fn = (FoodNutrition) request.getAttribute("fn");
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
			   else transfat=Double.parseDouble(fn.getTransfat());%>
				[ 'Nutrtion', 'Hi' ], [ '칼로리', <%=cal%> ], [ '탄수화물', <%=car%> ], [ '단백질', <%=protein%> ], [ '지방', <%=sugar%> ], 
				[ '당류', <%=glucose%> ], [ '나트륨', <%=salt%> ], [ '콜레스테롤', <%=chole%> ], [ '포화지방산', <%=fat%> ], [ '트랜스지방', <%=transfat%> ] ]);

		var options = {
			title : '${fn.getName()}',
			backgroundColor: { fill:'transparent' },
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
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<hr>
		<div id="result"></div>
		<div id="productset">
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
		<script type="text/javascript">
			var leaf_image=new Array();
			leaf_image[0]='img/<%=f.getName()%>.png';
		</script>
			<div class="row">
				<div class="col-xs-5" id="img">
				<img src="img/<%=fn.getName()%>.png" width="80%"></div>
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
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
		<!-- end of container -->
	</div>

</body>
</html>