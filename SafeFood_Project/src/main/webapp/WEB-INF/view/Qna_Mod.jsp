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
	table {
		width : 800px;
		table-layout: fixed;
	}
	.content {
		width : 800px;
		font-size: 10pt;
		padding: 20px;
		overflow: hidden;
		word-break: normal;
	}
	th {
		width: 200px;
	}
	td {
		width: 600px;
	}
</style>
</head>
<body>
	<div id='qna_mod'>
		<center>
		<form method="post" @submit.prevent="modQna"></form>
			<table>
				<tr>
					<th>제  목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td></td>
				</tr>
				<tr>
					<th>날  짜</th>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" class="content"><input type="text" name="description"></td>
				</tr>
			</table>
		</center>
	</div>
	<script type="text/javascript">
		var qna_detail = new Vue({
			el : '#qna_write',
			data () {
				return {
					loading : true,
					errored : false,
					title : '',
					content : '',
				}
			},
			methods : {
				addQna () { // 유효성 체크
					if(this.title == '') {alert('제목을 입력하세요'); return;}
					if(this.description == '') {alert('내용을 입력하세요'); return;}
					
					// form에 입력한 데이터가 문제가 없으면 진행, insert 요청
					axios.post('mod.json', {
						title : this.title,
						description : this.description
					})
					.then(response => {
						console.log(response.data); //받아온 데이터 출력
						if(response.data.result == true) {
							alert("insert 성공")
							location.href="list.do"; //페이지 이동 -> SPA로 구현해보기
						} else {
							alert("insert 실패")
							location.href="list.do"; //페이지 이동
						}
					})
					.catch(error => {
						 console.log(error)
						 this.errored = true
					 })
					 .finally(() => this.loading = false)
				}
			}
		});
	</script>
</body>
</html>