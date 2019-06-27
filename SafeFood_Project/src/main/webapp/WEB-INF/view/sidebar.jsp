<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
 var stmnLEFT = 10; // 오른쪽 여백 
 var stmnGAP1 = 0; // 위쪽 여백 
 var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
 var stmnBASE = 150; // 스크롤 시작위치 
 var stmnActivateSpeed = 35; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식)
 var stmnScrollSpeed = 20; //스크롤 속도 (클수록 느림)
 var stmnTimer; 
 
 function RefreshStaticMenu() { 
  var stmnStartPoint, stmnEndPoint; 
  stmnStartPoint = parseInt(document.getElementById('STATICMENU').style.top, 10); 
  stmnEndPoint = Math.max(document.documentElement.scrollTop, document.body.scrollTop) + stmnGAP2; 
  if (stmnEndPoint < stmnGAP1) stmnEndPoint = stmnGAP1; 
  if (stmnStartPoint != stmnEndPoint) { 
   stmnScrollAmount = Math.ceil( Math.abs( stmnEndPoint - stmnStartPoint ) / 15 ); 
   document.getElementById('STATICMENU').style.top = parseInt(document.getElementById('STATICMENU').style.top, 10) + ( ( stmnEndPoint<stmnStartPoint ) ? -stmnScrollAmount : stmnScrollAmount ) + 'px'; 
   stmnRefreshTimer = stmnScrollSpeed; 
   }
  stmnTimer = setTimeout("RefreshStaticMenu();", stmnActivateSpeed); 
  } 
 function InitializeStaticMenu() {
  document.getElementById('STATICMENU').style.right = stmnLEFT + 'px';  //처음에 오른쪽에 위치. left로 바꿔도.
  document.getElementById('STATICMENU').style.top = document.body.scrollTop + stmnBASE + 'px'; 
  RefreshStaticMenu();
  }
</script>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<style type="text/css">
	#STATICMENU { margin: 0pt; padding: 0pt;  position: absolute; right: 0px; top: 0px;}
 	#floatdiv {
		position: fixed; 
		_position: absolute;
		_z-index: -1;
		right: 60px;
		top: 320px;
		background-image: url('img/gold.jpg')
		margin: 0;
		padding: 20px;
		border-radius: 20px;
		font-family: 'Noto Sans KR';
	}
	
	#floatdiv ul {list-style: none;}
	#floatdiv li {margin-bottom: 2px; text-align: cetner;}
	#floatdiv a {color: #5D5D5D; border: 0; text-decoration: none; display: block;}
	#floatdiv a:hover. #floatdiv .menu {background-color: #5D5D5D, color: #fff;}
	#floatdiv .menu, #floatdiv .last {margin-bottom: 0px;}
	#floatdiv img {
		width: 80px;
		height: 80px;
	}
</style>
</head>
<body>
	<div id="STATICMENU"  onload="InitializeStaticMenu();">
		<div id="floatdiv">
			<h4>베스트 섭취</h4>
			<div v-if="flag">
				<a :href="url+best.code"><img :src="path+name+tag"></a><br/>
				{{best.name}}<br/>
				섭취 개수 : {{best.count}}
			</div>
			<div v-else="flag">
				<a href="list.mvc"><img :src="path+name+tag"></a><br/>
				섭취 음식을<br/>
				등록해주세요
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var sideapp = new Vue({
		el : '#floatdiv',
		data(){
			return{
				best : [],
				loading : true,
				errored : false,
				url : '', // 베스트 섭취 클릭
				path: 'img/',
				tag: '.png',
				url: 'showDetail.mvc?code=',
				flag: false,
				name: 'undefined'
			}
		},
		created() {
			axios.get('best-intake.json')
			.then(response => {
				this.best = response.data
				this.name = this.best.name
				this.flag = true
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