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
	th {
		width: 200px;
	}
	td {
		width: 600px;
	}
	.content {
		width : 800px;
		font-size: 10pt;
		padding: 20px;
		overflow: hidden;
		word-break: normal;
	}
	.button {
		text-align: right;
	}
	button {
		margin: 2px;
	}
</style>
</head>
<body>
	<div id='qna_detail'>
		<center>
			<table>
				<tr>
					<th>제  목</th>
					<td>{{info.title}}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>{{info.writer}}</td>
				</tr>
				<tr>
					<th>날  짜</th>
					<td>{{info.date}}</td>
				</tr>
				<tr>
					<td colspan="2" class="content">{{info.description}}</td>
				</tr>
				<tr>
					<td colspan="2" class="button">
						<button>수정
						<button>삭제
					</td>
				</tr>
			</table>
		</center>
	</div>
	<script type="text/javascript">
		var qna_detail = new Vue({
			el : '#qna_detail',
			data () {
				return {
					info : '',
					loading : true,
					errored : false
				}
			},
			mounted () {
				axios.get('qna_detail.json', params{id : ''})
				.then(response => {
					this.info = response.data
				})
				.catch(error => {
					console.log(error)
					this.errored = true
				})
				.then(() => this.loading = false)
			}
		});
	</script>
</body>
</html>