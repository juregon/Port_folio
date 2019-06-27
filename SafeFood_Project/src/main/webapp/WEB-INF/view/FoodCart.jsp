<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.ssafy.model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<style><%@ include file="card.css"%></style>
</head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
	
	var data = google.visualization.arrayToDataTable([
	<% ArrayList<FoodNutrition> fnlist = (ArrayList<FoodNutrition>) request.getAttribute("fnlist");
	    			
	   Double cal=0.0, car=0.0, protein=0.0, sugar=0.0, glucose=0.0
			   , salt=0.0, chole=0.0, fat=0.0, transfat=0.0;
	   for(FoodNutrition fn : fnlist) {
	 	   cal += Double.parseDouble(fn.getCal());
		   car += Double.parseDouble(fn.getCar());
		   if(fn.getProtein().equals("N/A"))protein+=0.0;
		   else protein+=Double.parseDouble(fn.getProtein());
		   if(fn.getSugar().equals("N/A"))sugar+=0.0;
		   else sugar+=Double.parseDouble(fn.getSugar());
		   if(fn.getGlucose().equals("N/A"))glucose+=0.0;
		   else glucose+=Double.parseDouble(fn.getGlucose());
		   if(fn.getSalt().equals("N/A"))salt+=0.0;
		   else salt+=Double.parseDouble(fn.getSalt());
		   if(fn.getChole().equals("N/A"))chole+=0.0;
		   else chole+=Double.parseDouble(fn.getChole());
		   if(fn.getFat().equals("N/A"))fat+=0.0;
		   else fat+=Double.parseDouble(fn.getFat());
		   if(fn.getTransfat().equals("N/A"))transfat+=0.0;
		   else transfat+=Double.parseDouble(fn.getTransfat());
	   }%>
		[ 'Nutrtion', '현재 섭취량', '권장 섭취량' ], [ '칼로리', <%=cal%>, 2100 ], [ '탄수화물', <%=car%>, 330 ], [ '단백질', <%=protein%>, 50 ], [ '지방', <%=sugar%>, 105 ], 
		[ '당류', <%=glucose%>, 25 ], [ '나트륨', <%=salt%>, 2000 ], [ '콜레스테롤', <%=chole%>, 300  ], [ '포화지방산', <%=fat%>, 15 ], [ '트랜스지방', <%=transfat%>, 2.2 ] ]);

  var options = {
    title: "오늘 하루 섭취 통계",
    bar: {groupWidth: "90%"},
    legend: { position: "none" },
    chartArea: {width: '100%'},
    colors: ['#b0120a', '#ffab91'],
    backgroundColor: { fill:'transparent' },
    hAxis: {
      title: '섭취량',
      minValue: 0
    },
    vAxis: {
      title: '영양소'
    }
  };
  var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
  chart.draw(data, options);
}
</script>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<hr>
		<div class="child-page-listing">
				<%
					ArrayList<Food> li = (ArrayList<Food>) request.getAttribute("flist");
					ArrayList<Integer> li2 = (ArrayList<Integer>) request.getAttribute("fclist");
					for (int i = 0; i < li.size(); i++) {
				%>
			 
				<div class="card location-listing" style="margin: 10px 0; width: 18rem; display: inline-block;">
					<img src="img/<%=li.get(i).getName()%>.png" class="card-img-top location-image" width="150px" height="150px">
					<h5>
					  <a href="showDetail.mvc?code=<%=li.get(i).getCode()%>"  class="card-title location-title title">
					  <%=li.get(i).getName()%>
					  </a>
					  </h5>
					<div class="card-body">
					  <p class="card-text">찜 갯수 : <%= li2.get(i) %></p>
					  <a href="checkAlgNew.mvc?code=<%=li.get(i).getCode()%>&count=<%= li2.get(i) %>" class="btn btn-light">섭취</a>|
					  <a href="foodcartDelete.mvc?code=<%=li.get(i).getCode()%>" class="btn btn-light">삭제</a>
					</div>
				</div>
				<%
					}
				%>
		</div>
		<% if(li.size() > 0) {%>
		<div id="barchart_values" style="width: 900px; height: 300px; margin-bottom: 100px;"></div>
		<%} %>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
		<!-- end of container -->
	</div>
</body>
</html>