<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
<!-- Vue 최신버전 반영 cdn -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
<style type="text/css">
body {
	font-size: 15pt;
}

table {
	width: 800px;
	table-layout: fixed;
}

th {
	width: 200px;
}

td {
	width: 600px;
}

.content {
	width: 800px;
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
	<div id='qna_list'>
		{{info}}
		<div class='headtitle'>
			<h2>SSAFY HRM</h2>
		</div>
		<div class='search_box'>
			<router-link to="/route1">글목록 보기</router-link>
			<!-- 이동할 페이지 <a href 와 비슷한 느낌 -->
			<!-- 			<router-link to="/route2">글 상세</router-link> -->
			<!-- 			<router-link to="/route3">ㄱ</router-link> -->
			<!-- 			<router-link to="/route4">모든사원 보기</router-link> -->
		</div>
		<router-view></router-view>
		<!-- 갱신할 화면영역 -->

	</div>
	<script type="text/x-template" id="addhrmtemplate">
	<div>
		<h3>asdasd</h3>
		<div>{{info}}
		</div>
		<h3>asdasaaaaaaaaaaaaaaad</h3>
	</div>
	</script>


	<script type="text/javascript">
	var addhrm = Vue.component('addhrm',{
	    template: '#addhrmtemplate',
	    props: ['info', 'loading']
	});
// 	var namehrm = Vue.component('namehrm',{
// 	    template: '#namehrmtemplate'
// 	});
// 	var idhrm = Vue.component('idhrm',{
// 	    template: '#idhrmtemplate'
// 	});
// 	var listhrm = Vue.component('listhrm',{
// 	    template: '#listhrmtemplate'
// 	});
	
	const Route1 = { template: addhrmtemplate} // 컴포넌트 정의
// 	const Route2 = { template: namehrmtemplate }
// 	const Route3 = { template: idhrmtemplate}
// 	const Route4 = { template: listhrmtemplate }
	
	const routes = [
	   { path: '/route1', component: Route1 }, // 각 URL 에 표시할 컴포넌트 연결
// 	   { path: '/route2', component: Route2 },
// 	   { path: '/route3', component: Route3 },
// 	   { path: '/route4', component: Route4 }
	];
	const router = new VueRouter({ // vue router 정의
	   routes	   
	});
	var App=new Vue({ // vue 인스턴스 생성, router 추가
		el: '#qna_list',
		router,
		  data(){
			return{
				info : [],
				loading : true,
				errored : false
			}
	  	},
	    created() {
			this.fetchData()
		},
		methods: {
			fetchData() {
				axios.get('qna-list.json')
				.then(response => {
					this.info = response.data
				})
				.catch(error => {
					console.log(error)
					this.errored = true
				})
				.finally(() => this.loading = false)
			}
		},
		components: {
			'addhrm': 
		}
	})

	
 </script>
</body>
</html>