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
		<div id='notice_write'>
			<form method="post" @submit.prevent="addNotice">
				<table class="table-borderless form-inline">
					<tr>
						<th width="300px">제  목</th>
						<td><input type="text" class="form-control" style="width: 800px;" name="title" v-model="title"
									placeholder="제목을 입력하세요"></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<select v-model="division" class="form-control" width="100px">
								<option value="공지" selected="selected">공지</option>
								<option value="이벤트">이벤트</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="300px">작성자</th>
						<td><input type="text" readonly class="form-control-plaintext pad"  style="border: none; background-color: transparent;" name="id;" :value="id"></td>
					</tr>
					<tr>
						<th width="300px">내용</th>
						<td class="content">
							<textarea class="form-control" name="description" v-model="description" style="height : 400px;  width: 800px; border: 0.5px solid; border-color: #d9d9d9;" placeholder="내용을 입력하세요">
							</textarea>
						</td>
					</tr>
				</table>
				<div class="buttons">
					<input type="submit" class="btn btn-dark" value="작성완료">
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
	
		var qna_write = new Vue({
			el : '#notice_write',
			data () {
				return {
					loading : true,
					errored : false,
					title : '',
					division : '',
					description : '',
					id : '${id}',
				}
			},
			methods : {
				addNotice () { // 유효성 체크
					if(this.title == '') {alert('제목을 입력하세요'); return;}
					if(this.description == '') {alert('내용을 입력하세요'); return;}
					// form에 입력한 데이터가 문제가 없으면 진행, insert 요청
					axios.post('notice-add.json', {
						title : this.title,
						division : this.division,
						description : this.description,
					})
					.then(response => {
						console.log(response.data); //받아온 데이터 출력
						location.href="notice-list.mvc"; //페이지 이동 -> SPA로 구현해보기
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