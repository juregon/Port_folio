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
	[ '이름', '개수 '],
	<% 	ArrayList<Food> li = (ArrayList<Food>) request.getAttribute("flist");
		ArrayList<Integer> li2 = (ArrayList<Integer>) request.getAttribute("fclist");
	%>	
	<%
		for (int i = 0; i < li.size(); i++) {
   	%>
  		['<%=li.get(i).getName()%>', <%= li2.get(i) %>]	
		
  		<%if(i < li.size()-1) {%>
  			,
  			
 		<%}
  		
		}%>
		
		
		]);

	 var options = {
	          title: '누적 섭취 개수',
	          is3D: true,
	          backgroundColor: { fill:'transparent' },
	        };

     var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
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
		<ul class="nav nav-pills nav-fill">
			  <li class="nav-item">
			    <a class="nav-link active" href="intakeInfo.mvc">오늘 하루 섭취량</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="yesIntake.mvc">어제 섭취량</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link disabled" href="totalIntake.mvc">전체 섭취량</a>
			  </li>
		</ul>
	
		<div class="child-page-listing" style="white-space: nowrap; overflow-x: scroll;">
				<%
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
					  <p class="card-text">섭취 갯수 : <%= li2.get(i) %></p>
					  <a href="intakeDelete.mvc?code=<%=li.get(i).getCode()%>" class="btn btn-light">삭제</a>
					</div>
				</div>
				<%
					}
				%>
		</div>
		<% if(li.size() > 0) {%>
		<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
		<%} %>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
		<!-- end of container -->
	</div>
</body>
</html>