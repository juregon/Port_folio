<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
</head>
<body>
	<div id="mail">
		<table class="table">
			<thead class="thaed-dark">
				<tr>
					<th>no</th>
					<th>제목</th>
					<th>보낸 날짜</th>
					<th>보낸 사람</th>
				</tr>
			</thead>
			<tbody class="tbody-dark">
				<tr v-for="info in infos">
					<td>{{count}}</td>
					<td>{{info.title}}</td>
					<td>{{info.date}}</td>
					<td>{{info.sender}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
	var app = new Vue({
			el : '#mail_list',
			data(){
				return{
					infos : [],
					loading : true,
					errored : false,
					id:'${id}',
					find : 'title',
					url : '',
					count : 1
				}
			},
			mounted() {
				axios.get('mail-list.json')
					.then(response => {
						this.info = response.data
					})
					.catch(error => {
						console.log(error)
						this.errored = true
					})
					.finally(() => this.loading = false)
			}
		})
	</script>
</body>
</html>