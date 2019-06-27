<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
<style type="text/css">
thead th{
	text-align: center;
}
table {
	margin-top: 20px;
}
</style>
</head>
<body>
	<%
		if (request.getAttribute("status") != null) {
			String msg = (String) request.getAttribute("msg");
			out.println("<script>alert('" + msg + "');</script>");
		}
	%>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false" />
		</header>

		<!-- 본문 -->
		<br>
		<div id="notice_list">
			<div class="form-inline">
				<select class="form-control" v-model="find">
					<option selected="selected" value="title">제목   </option>
					<option value="description">내용   </option>
				</select>
				<input class="form-control" type="text" placeholder="검색어를 입력해 주세요" name='name' v-model="cname" v-cloak />
				<button style="border:none; background-color: transparent;"><img class="btn-img" alt="" src="img/search.png" style="border:none; width:30px; height:30px"></button>
				<div style="float: right; display: inline-block;" v-if="isAdmin()">
					<button class="btn btn-dark" onclick="location.href='notice-write.mvc'">글 작성</button>
				</div>
			</div>
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr align="center">
						<th width="100px">번호</th>
						<th width="100px">구분</th>
						<th width="800px">제목</th>
						<th width="300px">작성일</th>
					</tr>
				</thead>

				<tbody class="tbody-dark">
					<template v-if="find == 'title'"> 
						<template v-for="list in info" v-if="list.title.includes(cname)">
							<tr align="center">
								<td>{{list.num}}</td>
								<td>{{list.division}}</td>
								<td><a :href="url+list.num">{{list.title}}</a></td>
								<td>{{list.date}}</td>
							</tr>
						</template>
					</template>
					<template v-else-if="find == 'description'"> 
						<template v-for="list in info" v-if="list.description.includes(cname)">
							<tr align="center">
								<td>{{list.num}}</td>
								<td>{{list.division}}</td>
								<td><a :href="url+list.num">{{list.title}}</a></td>
								<td>{{list.date}}</td>
							</tr>
						</template>
					</template>
				</tbody>
			</table>
			
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false" />
		</footer>

	</div>
	<script type="text/javascript">
		var app = new Vue({
			el : '#notice_list',
			data(){
				return{
					info : [],
					loading : true,
					errored : false,
					cname : '',
					id:'${id}',
					find : 'title',
					url : 'notice-detail.mvc?num=',
					url2 : 'notice-write.mvc?num=',
				}
			},
			mounted() {
				axios.get('notice-list.json')
					.then(response => {
						this.info = response.data
					})
					.catch(error => {
						console.log(error)
						this.errored = true
					})
					.finally(() => this.loading = false)
			},
			methods : {
				isAdmin(){
					if(this.id == 'admin'){
						return true;
					} else{
						return false;
					}
				}
			}
		})
	</script>
</body>
</html>