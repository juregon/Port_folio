<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="com.ssafy.vo.Food" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 안전먹거리</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
	body .container{
		margin-top: 50px;
	}
	.btn-light{
		padding: 10px 15px;
		margin-top : 25px;
		font-size: 15px;
		font-weight: 300;
	}
	.title{
		text-align: center;
		background-color: gray;
		height: 200px;
	}
	.title .SSAFY{
		margin-top: 40px;
		color: white;
		font-size: 20px;
		font-display: inherit;
	}
	.title .go{
		color: white;
		font-size: 20px;
	}
	.search{
		background-color: darkgrey;
		height: 100px;
		text-align: right;
	}
	.search .search-title{
		color: white;
		margin-top: 15px;
		text-align: right;
	}
	.search .dropdown-menu{
		padding-top: 0px;
		padding-bottom: 0px;
		border-radius: 5%;
		color: white;
	}
	.search-word{
		color: white;
		text-align: left;
		margin-top: 15px;
		margin-left: 20px;
	}
	.input-group{
		display: inline;
	}
	.button-row{
		margin-top: 34px;
	}
	.list div{
		display: inline-block;
		width: 45%;
		margin-right: 20px;
	}
	.food-title{
		font-size: 20px;
		font-weight: bold;
	}
	.list .product{
		font-style: italic;
	}
	.list .btn{
		margin-right: 10px;
	}
	
</style>
<script>
function search(){
	$('.list').empty();
	var searchResult = $('.search-input').val();
	$('.search-input').val('');
	$.ajax({
			url: '../res/foodInfo.xml',
			async: false,
			dataType: 'xml',
			success: function (result, text) {
				if( $(result).find('food').length>0){
					$(result).find('food').each(function () {
						var name = $(this).find('name').text();
						if( name.search(searchResult) == -1)
							return;
						var div = document.createElement('div');
						var span_img = document.createElement('span');
						var img = document.createElement('img')
						var a = document.createElement('a');
						a.setAttribute('href', 'product_info.html');
						img.setAttribute('src', '../' + $(this).find('image').text());
						img.setAttribute('width', "200");
						img.setAttribute('height', '200');
						a.appendChild(img);
						span_img.appendChild(a);
						div.appendChild(span_img);
						// 제품 이름 파싱
						var span_right = document.createElement('span');
						var divName = document.createElement('div');
						var text = document.createTextNode($(this).find('name').text());
						divName.appendChild(text);
						divName.setAttribute('class', 'food-title');
						// 버튼 추가
						divName.appendChild(document.createElement('br'));
						var button = document.createElement('button');
						button.setAttribute('type', 'button');
						button.setAttribute('class', 'btn btn-primary add-' + $(this).find('name').text());
						text = document.createTextNode('추가');
						button.appendChild(text);
						divName.appendChild(button);
						// 찜 추가
						button = document.createElement('button');
						button.setAttribute('type', 'button');
						button.setAttribute('class', 'btn btn-primary JJim-' + $(this).find('name').text());
						text = document.createTextNode('찜');
						button.appendChild(text);
						divName.appendChild(button);
						span_right.appendChild(divName);
					
						div.appendChild(span_right);
						$('.list').append(div);
					});
				}
			}
		});
}
</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@ include file="navigation.jsp"%>
	
	<div class="container">
		<section class="title">
			<div class="row">
				<div class="row">
					<div class="col-md-offset2">
						<p class="SSAFY">
							WHAT WE PROVIDE
						</p>
						<hr style="border: solid 10% white;width: 40%;">
						<p class="go">
							건강한 삶을 위한 먹거리 프로젝트
						</p>
					</div>

				</div>
			</div>
		</section>
		<section class="search">
			<form action="search_name.mvc">
			<div class="row">
				<div class="col-md-2 col-md-offset-3 search-title">검색 조건
					<div class="row">
						<div class="btn-group" role="group" aria-label="Button group with nested dropdown">
							<button id="btnGroupDrop1" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">상품명</button>
							<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
								<ul class="nav">
									<li class="btn btn-warning"><a class="dropdown-item" href="#">가격</a></li>
									<li class="btn btn-warning"><a class="dropdown-item" href="#">상품설명</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-2">
						<div class="row search-word">검색 단어
							<div class="row">
								<div class="input-group">
									<input type="text" class="form-control" name="product_name" placeholder="검색 입력해주세요." aria-describedby="basic-addon2">
								</div>
							</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="row">
						<div class="button-row">
							<button class="btn btn-primary search-button" type="submit">검색</button>
						</div>
					</div>
				</div>
				
			
			</div>
			</form>
		</section>
		<article class="list">
			<% request.setCharacterEncoding("euc-kr"); %>
			<% List<Food> list = (List)request.getAttribute("list"); %>
			<%
				for(int i = 0; i < list.size(); i++){
			%>
			<div>
				<span>
					<a></a>
					<a href="product_info.mvc?code=<%= list.get(i).getCode() %>">
					<img alt="<%=list.get(i).getName() %>" src="<%= list.get(i).getImg() %>" 
						style="width:200px; height:200px;"></a>
				</span>
				
				<span>
					<div class="food-title">
						<%= list.get(i).getName() %>
						<br>
						<button type="button" class="btn btn-primary">
							추가
						</button>
						<button type="button" class="btn btn-primary">
							찜
						</button>
					</div>
				</span>
			</div>
			<%} %>
		</article>
		
	</div>
	<!-- footer 바 -->
	<%@ include file="footer.jsp"%>
<!-- 	<footer class="footer"> -->
<!-- 		Copyright by SSAFY6. All Right Reversed.  -->
<!-- 	</footer> -->
</body>
</html>




























