<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="com.ssafy.vo.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0//css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
.picture_button {
	display: inline-block;
}
</style>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="login-box well">
					<form action="index.html">
						<legend>회원정보</legend>
						<% User user = (User)request.getAttribute("user");%>
						<div class="form-group">
							<table class="table table-striped">
								<tbody>
									<tr>
										<th scope="row">아이디</th>
										<td></td>
										<td></td>
										<td><%=user.getId() %></td>
									</tr>
									<tr>
										<th scope="row">이름</th>
										<td></td>
										<td></td>
										<td><%=user.getName() %></td>
									</tr>
									<tr>
										<th scope="row">주소</th>
										<td></td>
										<td></td>
										<td><%=user.getAddress() %></td>
									</tr>
									<tr>
										<th scope="row">전화번호</th>
										<td></td>
										<td></td>
										<td><%=user.getPhone() %></td>
									</tr>
									<tr>
										<th scope="row">알레르기</th>
										<td></td>
										<td></td>
										<% String str = "";
										 for(int i = 0; i < user.getAllergy().length; i++){ 
											str += user.getAllergy()[i] + "  ";
										}%>
										<td><%=str %></td>
									</tr>
									<tr>
										<th scope="row">사진</th>
										<td></td>
										<td></td>
										<td><img style="width: 120px; height: 160px"
											class="img-responsive img-thumbnail" src="../img/맛있는우유GT.jpg">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
							<a href="user_modify.mvc"
								class="btn btn-default btn-login-submit btn-block m-t-md">수정</a> <a
								href="user_withdrawal.mvc" class="btn btn-default btn-login-submit btn-block m-t-md">탈퇴</a>
							<a href="index.mvc" class="btn btn-default btn-login-submit btn-block m-t-md">닫기</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>