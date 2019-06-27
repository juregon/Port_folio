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
	table td {
		padding: 2px;
	}
	.content {
		font-size: 10pt;
		padding-top: 10;
		overflow: hidden;
		word-break: normal;
	}
	.pad {
		padding: 5px;
	}
	.buttons {
		text-align: right;
		margin-right: 35px;
		margin-top: 5px;
	}
</style>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<hr>
		<div id='qna_mod'>
			<form method="post" @submit.prevent="modQna">
				<table class="table-borderless">
					<tr>
						<th width="300px">제  목</th>
						<td><input type="text" class="form-control"  style="width: 800px;" name="title" v-model="info.title" :value="info.title"></td>
					</tr>
					<tr>
						<th width="300px">작성자</th>
						<td><input type="text" readonly class="form-control-plaintext pad"  style="border: none; background-color: transparent;" name="id" :value="id"></td>
					</tr>
					<tr>
						<th width="300px">내용</th>
						<td class="content">
						<textarea class="form-control" name="description" v-model="info.description" :value="info.description" style="border: 0.5px solid; border-color: #d9d9d9; height:400px;" >
						</textarea></td>
					</tr>
				</table>
				<div class="buttons">
					<input class="btn btn-light" type="submit" value="수정완료">
					<input class="btn btn-light" type="reset" value="취소">
				</div>
			</form>
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
	
		var qna_mod = new Vue({
			el : '#qna_mod',
			data () {
				return {
					loading : true,
					errored : false,
					num: getParameterByName('num'),
					id:'${id}',
					info: []
				}
			},
			mounted () {
				axios.get('qna-detail.json', {params :{num: this.num}})
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
				modQna () { // 유효성 체크
					//if(this.title == '') {this.title = this.info.title}
					//if(this.description == '') {this.description = this.info.description}
					
					// form에 입력한 데이터가 문제가 없으면 진행, insert 요청
					axios.post('mod.json', {
						num: this.num,						
						title : this.info.title,
						description : this.info.description
					})
					.then(response => {
						location.href="qna-list.mvc"; //페이지 이동 -> SPA로 구현해보기
						
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