<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.search {
	padding-top: 10px;
	padding-bottom: 10px;
	text-align: center;
}

.form-control {
	display: inline-block;
}
button {
	border:none; 
	background-color: transparent;
}
</style>
</head>
<body>
<!-- 검색창 -->
	<div class="form-group form-inline">
		<form action="search.mvc" class="search" role="search" method="post">
			<select class="form-control" name="search_category">
				<option selected="selected" value="name">상품명</option>
				<option value="company">제조사명</option>
				<option value="material">원재료명</option>
			</select>
			<input type="text" class="form-control" name="search" placeholder="검색어를 입력해 주세요">
			<button type="submit"><img class="btn-img" alt="" src="img/search.png" style="border:none; width:30px; height:30px;"></button>
		</form>
	</div>
</body>
</html>