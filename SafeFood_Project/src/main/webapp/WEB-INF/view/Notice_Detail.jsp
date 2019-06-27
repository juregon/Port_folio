<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트 : 공지사항_Detail</title>
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
		<div id='notice_detail'>
		
			<table class="table table-bordered pad">
				<tr>
					<th width="300px">제  목</th>
					<td>{{info.title}}</td>
				</tr>
				<tr>
					<th width="100px">카테고리</th>
					<td>{{info.division}}</td>
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
			<div class="buttons" v-if="isAdmin()">
				<a :href="url1+info.num"><input type="button" class="btn btn-light" value="수정"></a>
				<a :href="url2+info.num"><input type="button" class="btn btn-light" value="삭제"></a>
			</div>
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
			el : '#notice_detail',
			data () {
				return {
					info : '',
					loading : true,
					errored : false,
					num: '',
					id:'${id}',
					url1 : 'notice-mod.mvc?num=',
					url2 : 'notice-delete.mvc?num='
				}
			},
			mounted () {
				axios.get('notice-detail.json', {params : {num: getParameterByName('num')}})
				.then(response => {
					this.info = response.data
				})
				.catch(error => {
					console.log(error)
					this.errored = true
				})
				.then(() => this.loading = false)
			},
			methods : {
				isAdmin() {
					if(this.id == 'admin') {
						return true;
					} else {
						return false;
					}
				}
			}
		});
	</script>
</body>
</html>