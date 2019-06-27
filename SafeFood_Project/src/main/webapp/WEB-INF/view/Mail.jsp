<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div id="mail_list">
		<table class="table">
			<thead class="thaed-dark">
				<tr>
					<th>제목</th>
					<th>보낸 날짜</th>
					<th>보낸 사람</th>
				</tr>
			</thead>
			<tbody class="tbody-dark">
				<tr v-for="info in infos">
					<td><a @click="changeFlag(info)">{{info.title}}</a></td>
					<td>{{info.date}}</td>
					<td>{{info.sender}}</td>
					</template>
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
					num: 0
				}
			},
			mounted() {
				axios.get('mail-list.json')
					.then(response => {
						this.infos = response.data
					})
					.catch(error => {
						console.log(error)
						this.errored = true
					})
					.finally(() => this.loading = false)
			},
			methods: {
				changeFlag: function(value) {
					this.num = value.num;
					console.log(this.num)
					axios.post('mail-update.json', {
						num : this.num
					})
					.then(response => {
						console.log(value.url)
						location.href=value.url;
					})
					.catch(error => {
						 console.log(error)
						 this.errored = true
					 })
					 .finally(() => this.loading = false)
				}
			}
		})
	</script>
</body>
</html>