<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트 : 질문</title>
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
<style type="text/css">
	body {
		font-size: 15pt;
	}
	table th {
		text-align: center;
	}
	button {
		margin: 2px;
	}
	.content {
		font-size: 10pt;
		padding-top: 20px;
		overflow: hidden;
		word-break: normal;
		height:400px;
		width: 800px;
		vertical-align: top;
	}
	.buttons {
		text-align: right;
	}
	.pad th, td {
		padding: 5px;
	}
</style>
<style><%@include file="basiclayout.css"%></style>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<hr>
		<div id='qna_detail'>
			<table class="table table-bordered pad">
				<tr>
					<th width="300px">제  목</th>
					<td>{{info.title}}</td>
				</tr>
				<tr>
					<th width="300px">작성자</th>
					<td>{{info.id}}</td>
				</tr>
				<tr>
					<th width="300px">날  짜</th>
					<td>{{info.date}}</td>
				</tr>
				<tr>
					<th width="300px">내용</th>
					<td class="content"><pre style="background-color: transparent; border: none;">{{info.description}}</pre></td>
				</tr>
			</table>
			<div class="buttons" v-if="isMine()">
				<a :href="url1+info.num"><input type="button" class="btn btn-light" value="수정"></a>
				<a :href="url2+info.num"><input type="button" class="btn btn-light" value="삭제"></a>
			</div>
			<hr>
			<div v-if="isReply()">
				<table style="width:100%">
					<template v-for="list in info_reply">
						<tr>
							<template v-if="list.replyer=='admin'">
								<td style="width:90%; color: red;">
									{{list.replyer}}({{list.rDate}})
									<br>
									{{list.rText}}
								</td>
							</template>
							<template v-else>
								<td style="width:90%;">
									{{list.replyer}}({{list.rDate}})
									<br>
									{{list.rText}}
								</td>
							</template>
							<td style="float: right">
								<div class="buttons" v-if="isMine_reply(list.replyer)" style="text-align:right;">
<!-- 									<a :href="url1+info.num"><input type="button" class="btn btn-light" value="수정"></a> -->
									<a :href="url3+list.rNo"><input type="button" class="btn btn-light" value="삭제"></a>
								</div>
							</td>
						</tr>
						<br>
					</template>
				</table>
			</div>
			<template v-if="id != ''">
				<div style="width:100%; text-align: center">
					<br>
					<form method="post" @submit.prevent="addReply">
						<textarea style="width:100%;" rows="5" placeholder="댓글을 작성해 주세요" v-model="rText"></textarea>
						<br>
						<input type="submit" class="btn btn-light" id="btnReply" value="댓글 작성" style="float: right"/>
					</form>
				</div>
			</template>
			
		</div>
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
	<script type="text/javascript">
		function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
	
		var qna_detail = new Vue({
			el : '#qna_detail',
			data () {
				return {
					info : '',
					info_reply : '',
					loading : true,
					errored : false,
					num: '',
					rText : '',
					id:'${id}',
					url1 : 'qna-mod.mvc?num=',
					url2 : 'qna-delete.mvc?num=',
					url3 : 'reply-delete.mvc?num='
				}
			},
			mounted () {
				axios.get('qna-detail.json', {params : {num: getParameterByName('num')}})
				.then(response => {
					this.info = response.data
				})
				.catch(error => {
					console.log(error)
					this.errored = true
				}),
				axios.get('reply-list.json', {params : {num: getParameterByName('num')}})
				.then(response => {	
					this.info_reply = response.data;
				})
				.catch(error => {
					console.log(error)
					this.errored = true
				})
			},
			methods : {
				isMine() {
					if(this.info.id==this.id || this.id == 'admin') {
						return true;
					} else {
						return false;
					}
				},
				isMine_reply(replyer) {
					if(replyer == this.id || 'admin' == this.id || this.isMine()) {
						return true;
					} else {
						return false;
					}
				},
				addReply () { // 유효성 체크
					if(this.rText == '') {alert('내용을 입력하세요'); return;}
					// form에 입력한 데이터가 문제가 없으면 진행, insert 요청
					axios.post('reply-add.json', {
						qNo : this.info.num,
						rText : this.rText,
						replyer : this.id,
					})
					.then(response => {
						console.log(response.data); //받아온 데이터 출력
						axios.get('reply-list.json', {params : {num: getParameterByName('num')}})
						.then(response => {	
							this.info_reply = response.data;
						})
						.catch(error => {
							console.log(error)
							this.errored = true
						})
// 						location.href="notice-list.mvc"; //페이지 이동 -> SPA로 구현해보기
					})
					.catch(error => {
						 console.log(error)
						 this.errored = true
					 })
					 .finally(() => this.loading = false)
				},
				isReply() {
					return this.info_reply.length > 0;
				}				
				
			}
		});
	</script>
</body>
</html>