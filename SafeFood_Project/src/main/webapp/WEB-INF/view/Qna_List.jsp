<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 문의</title>
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
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<br>
		<div id="qna_list">
			<div class="form-inline">
				<select class="form-control" v-model="find">
					<option selected="selected" value="title">제목</option>
					<option value="description">내용</option>
					<option value="id">작성자</option>
				</select>
				<input class="form-control" type="text" placeholder="검색어를 입력해 주세요" name='name' v-model="cname" v-cloak />
				<button style="border:none; background-color: transparent;"><img class="btn-img" alt="" src="img/search.png" style="border:none; width:30px; height:30px"></button>
				<div style="float: right; display: inline-block;" v-if="id!=''">
					<button class="btn btn-dark" onclick="location.href='qna-write.mvc'">글 작성</button>
				</div>
			</div>
			<table class="table table-hover">
				<thead class="thead-light">
					<tr align="center">
						<th width="100px" >번호</th>
						<th width="800px" >제목</th>
						<th width="100px" >작성자</th>
						<th width="90px" >상태</th>
						<th width="150px" >작성일</th>
						
					</tr>
				</thead>
				<tbody class="tbody-dark">
					<template v-if="find == 'title'">
						<template v-for="list in info" v-if="list.title.includes(cname)">
							<tr align="center">
								<td>{{list.num}}</td>
								<td><a :href="url+list.num">{{list.title}}</a><span style="color:blue;">({{list.reCnt}})</span></td>
								<td>{{list.id}}</td>
								<td>
									<div style="background: red; border-radius: 5%" v-if="list.reCheck > 0">
										<span style="font-size:smaller; color:white;" >답변 완료</span>
									</div>
									<div style="background: blue; border-radius: 5%" v-if="list.reCheck == 0">
										<span style="font-size:smaller; color:white;" >답변 대기</span>
									</div>
								</td>
								<td>{{list.date}}</td>
							</tr>
						</template>
					</template>
					<template v-else-if="find == 'description'">
						<template v-for="list in info" v-if="list.description.includes(cname)">
							<tr align="center">
								<td>{{list.num}}</td>
								<td><a :href="url+list.num">{{list.title}}</a><span style="color:blue;">({{list.reCnt}})</span></td>
								<td>{{list.id}}</td>
								<td>
									<div style="background: red; border-radius: 5%" v-if="list.reCheck > 0">
										<span style="font-size:smaller; color:white;" >답변 완료</span>
									</div>
									<div style="background: blue; border-radius: 5%" v-if="list.reCheck == 0">
										<span style="font-size:smaller; color:white;" >답변 대기</span>
									</div>
								</td>
								<td>{{list.date}}</td>
							</tr>
						</template>
					</template>
					<template v-else-if="find == 'id'">
						<template v-for="list in info" v-if="list.id.includes(cname)">
							<tr align="center">
								<td>{{list.num}}</td>
								<td><a :href="url+list.num">{{list.title}}</a><span style="color:blue;">({{list.reCnt}})</span></td>
								<td>{{list.id}}</td>
								<td>
									<div style="background: red; border-radius: 5%" v-if="list.reCheck > 0">
										<span style="font-size:smaller; color:white;" >답변 완료</span>
									</div>
									<div style="background: blue; border-radius: 5%" v-if="list.reCheck == 0">
										<span style="font-size:smaller; color:white;" >답변 대기</span>
									</div>
								</td>
								<td>{{list.date}}</td>
							</tr>
						</template>
					</template>
				</tbody>
			</table>
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
	<script type="text/javascript">
		var app = new Vue({
			el : '#qna_list',
			data(){
				return{
					info : [],
					reply_admin : false,
					loading : true,
					errored : false,
					cname : '',
					find : 'title',
					url : 'qna-detail.mvc?num=',
					id: '${id}'
				}
			},
			mounted() {
				axios.get('qna-list.json')
					.then(response => {
						this.info = response.data
					})
					.catch(error => {
						console.log(error)
						this.errored = true
					})
					.finally(() => this.loading = false)
			},
// 			methods: {
// 				isReply(qNo) {
// 					axios.get('reply-list-check.json', {params : {num: qNo}})
// 					.then(response => {
// 						this.reply_admin = response.data;
// // 						console.log(response.data);
// // 						if(response.data)
// // 							return true;
// // 						else
// // 							return false;
// // 						for (var i = 0; i < response.data.length; i++) {
// // 							if(response.data[i].replyer == 'admin'){
// // 								console.log("관리자가 답변함" + response.data[i].qNo);
// // 								this.reply_admin = true;
// // 								break;
// // 							}
// // 						}
// 					})
// 					.catch(error => {
// 						console.log(error)
// 						this.errored = true
// 					})
// 					return this.reply_admin;
					
// 				}
// 			}
		})
	</script>
</body>
</html>