<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.ssafy.model.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<style><%@ include file="list.css"%></style>
<%
	if(request.getAttribute("msg") != null) {
		String msg = (String) request.getAttribute("msg");
		int code = (int	) request.getAttribute("code");
		int count = (int) request.getAttribute("count");
		out.println("<script>if(confirm(\""+msg+"\")) { " 
				+"window.location.href='intake.mvc?code="+code+"&count="+count+"'; "
			+"} else { "
			+	"alert('섭취 정보 등록을 취소하였습니다.');"	
			+" }</script>");
	} 
%>

<style type="text/css">
.page_buttons {
	text-align: right;
	margin-right: 35px;
}
</style>
</head>
<body>
<%
	ArrayList<Food> li = (ArrayList<Food>) request.getAttribute("list");
	
%>
<div class="container">
	<header>
		<jsp:include page="header.jsp" flush="false"/>
	</header>
		<jsp:include page="search.jsp" flush="false"/>		
	<hr>
	<div id='app'>
		<router-view></router-view> <!-- 갱신할 화면영역 -->
		<div class='page_buttons'>
			<div class='search_box' style="clear: both;">
				 <ul class="pagination">
		    		<li class="page-item">
						<router-link to="/route1" class="page-link">1</router-link>
					</li>
					<% if(li.size() > 6) {%>
					<li class="page-item">
						<router-link to="/route2" class="page-link">2</router-link>
					</li>
					<% if(li.size() > 12) {%>
					<li class="page-item">
						<router-link to="/route3" class="page-link">3</router-link>
					</li>
					<% if(li.size() > 18) {%>
					<li class="page-item">
						<router-link to="/route4" class="page-link">4</router-link>
					</li>
					<%		}
						}
					}%>
				</ul>
			</div>
		</div>
	</div>	
	<div>
		<% if(session.getAttribute("id") != null) {%>	
			<jsp:include page="sidebar.jsp" flush="false"/>
		<%} %>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
	<!-- end of container -->
</div>
<script type="text/x-template" id="addhrmtemplate">
	<div>
	<%
		for (int i = 0; i < 6 && i < li.size(); i++) {
	%>
		<div style="float: left; width:33.3%; padding:20px;">
			<figure class="snip1436">
				<img style="height:350px; width:100%; "src="img/<%=li.get(i).getName()%>.png">
				<figcaption>
					<h3><%=li.get(i).getName()%></h3>
					<p><%=li.get(i).getMaterials()%></p>
					<a href="showDetail.mvc?code=<%=li.get(i).getCode()%>" class="read-more">Read More</a>
					<div class="button-5" onclick="location.href='checkAlg.mvc?code=<%=li.get(i).getCode()%>&count=1'">
						<div class="eff-5">
						</div>
						<a>추가 </a>
					</div>
					<div class="button-5" onclick="location.href='insertFoodcart.mvc?code=<%=li.get(i).getCode()%>'">
						<div class="eff-5">
						</div>
						<a>찜 </a>
					</div>
				</figcaption>
			</figure>
		</div>
	<% }%>
	</div>
</script>
<script type="text/x-template" id="namehrmtemplate">
	<div>
	<%
		for (int i = 6; i < 12 && i < li.size(); i++) {
	%>
		<div style="float: left; width:33.3%; padding:20px;">
			<figure class="snip1436">
				<img style="height:350px; width:100%; "src="img/<%=li.get(i).getName()%>.png">
				<figcaption>
					<h3><%=li.get(i).getName()%></h3>
					<p><%=li.get(i).getMaterials()%></p>
					<a href="showDetail.mvc?code=<%=li.get(i).getCode()%>" class="read-more">Read More</a>
					<div class="button-5" onclick="location.href='checkAlg.mvc?code=<%=li.get(i).getCode()%>&count=1'">
						<div class="eff-5">
						</div>
						<a>추가 </a>
					</div>
					<div class="button-5" onclick="location.href='insertFoodcart.mvc?code=<%=li.get(i).getCode()%>'">
						<div class="eff-5">
						</div>
						<a>찜 </a>
					</div>
				</figcaption>
			</figure>
		</div>
	<% }%>
	</div>
</script>
<script type="text/x-template" id="idhrmtemplate">
	<div>
	<%
		for (int i = 12; i < 18 && i < li.size(); i++) {
	%>
		<div style="float: left; width:33.3%; padding:20px;">
			<figure class="snip1436">
				<img style="height:350px; width:100%; "src="img/<%=li.get(i).getName()%>.png">
				<figcaption>
					<h3><%=li.get(i).getName()%></h3>
					<p><%=li.get(i).getMaterials()%></p>
					<a href="showDetail.mvc?code=<%=li.get(i).getCode()%>" class="read-more">Read More</a>
					<div class="button-5" onclick="location.href='checkAlg.mvc?code=<%=li.get(i).getCode()%>&count=1'">
						<div class="eff-5">
						</div>
						<a>추가 </a>
					</div>
					<div class="button-5" onclick="location.href='insertFoodcart.mvc?code=<%=li.get(i).getCode()%>'">
						<div class="eff-5">
						</div>
						<a>찜 </a>
					</div>
				</figcaption>
			</figure>
		</div>
	<% }%>
	</div>
	
</script>
<script type="text/x-template" id="listhrmtemplate">
	<div>
	<%
		for (int i = 18; i < 24 && i < li.size(); i++) {
	%>
		<div style="float: left; width:33.3%; padding:20px;">
			<figure class="snip1436">
				<img style="height:350px; width:100%; "src="img/<%=li.get(i).getName()%>.png">
				<figcaption>
					<h3><%=li.get(i).getName()%></h3>
					<p><%=li.get(i).getMaterials()%></p>
					<a href="showDetail.mvc?code=<%=li.get(i).getCode()%>" class="read-more">Read More</a>
					<div class="button-5" onclick="location.href='checkAlg.mvc?code=<%=li.get(i).getCode()%>&count=1'">
						<div class="eff-5">
						</div>
						<a>추가 </a>
					</div>
					<div class="button-5" onclick="location.href='insertFoodcart.mvc?code=<%=li.get(i).getCode()%>'">
						<div class="eff-5">
						</div>
						<a>찜 </a>
					</div>
				</figcaption>
			</figure>
		</div>
	<% }%>
	</div>
</script>

<script type="text/javascript">
	var addhrm = Vue.component('p1',{
	    template: '#addhrmtemplate'
	});
	var namehrm = Vue.component('p2',{
	    template: '#namehrmtemplate'
	});
	var idhrm = Vue.component('p3',{
	    template: '#idhrmtemplate'
	});
	var listhrm = Vue.component('p4',{
	    template: '#listhrmtemplate'
	});
	
	const Route1 = { template: addhrmtemplate} // 컴포넌트 정의
	const Route2 = { template: namehrmtemplate }
	const Route3 = { template: idhrmtemplate}
	const Route4 = { template: listhrmtemplate }
	
	const routes = [
	   { path: '/route1', component: Route1 }, // 각 URL 에 표시할 컴포넌트 연결
	   { path: '/route2', component: Route2 },
	   { path: '/route3', component: Route3 },
	   { path: '/route4', component: Route4 }
	];
	const router = new VueRouter({ // vue router 정의
	   routes 
	});
	var App=new Vue({ // vue 인스턴스 생성, router 추가
	  el: '#app',
	  router,
	  mounted () {
		  router.push({path: '/route1'})
	  }
	})

 </script>
</body>
</html>