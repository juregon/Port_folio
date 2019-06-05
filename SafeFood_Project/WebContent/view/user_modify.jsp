<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="com.ssafy.vo.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0//css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
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
					<form action="user_modify_process.mvc" method="post">
						<legend> 회원수정 </legend>
						<% User user = (User)request.getAttribute("user");%>
						<div class="form-group">
							<label for="user_id">아이디</label> <input class="form-control"
								name="user_id" value="<%=user.getId() %>" id="user_id" type="text"
								disabled="disabled">
						</div>
						<div class="form-group">
							<label for="user_password">비밀번호</label> <input
								class="form-control" name="user_password" id="user_password"
								value='' placeholder="영문 숫자 포함 8자리 이상" type="password">
						</div>
						<div class="form-group">
							<label for="user_password_check">비밀번호 확인</label> <input
								class="form-control" name="user_password_check"
								id="user_password_check" value='' placeholder="영문 숫자 포함 8자리 이상"
								type="password">
						</div>
						<div class="form-group">
							<label for="user_name">이름</label> <input class="form-control"
								name="user_name" value="<%=user.getName() %>" id="user_name" type="text"
								disabled="disabled">
						</div>
						<div class="form-group">
							<label for="user_address">주소</label> <input class="form-control"
								name="user_address" value="<%=user.getAddress() %>" id="user_address"
								type="text">
						</div>
						<div class="form-group">
							<label for="user_phone">전화번호</label> <input class="form-control"
								name="user_phone" value="<%=user.getPhone() %>" id="user_phone"
								type="text">
						</div>
						<div class="form-group">
							<label for="user_allergie">알레르기</label>
							<div class="checkbox">
								<label for="대추"> <input type="checkbox" id="대추">
									대추
								</label> <label for="땅콩"> <input value="땅콩" name="chkbox" type="checkbox" id="땅콩">
									땅콩
								</label> <label for="우유"> <input value="우유" name="chkbox" type="checkbox" id="우유">
									우유
								</label> <label for="게"> <input value="게" name="chkbox" type="checkbox" id="게">
									게
								</label> <label for="새우"> <input value="새우" name="chkbox" type="checkbox" id="새우">
									새우
								</label> <label for="참치"> <input value="참치" name="chkbox" type="checkbox" id="참치">
									참치
								</label> <label for="연어"> <input value="연어" name="chkbox" type="checkbox" id="연어">
									연어
								</label> <label for="쑥"> <input value="쑥" name="chkbox" type="checkbox" id="쑥">
									쑥
								</label> <label for="소고기"> <input value="소고기" name="chkbox" type="checkbox" id="소고기">
									소고기
								</label> <label for="닭고기"> <input value="닭고기" name="chkbox" type="checkbox" id="닭고기">
									닭고기
								</label> <label for="돼지고기"> <input value="돼지고기" name="chkbox" type="checkbox" id="돼지고기">
									돼지고기
								</label> <label for="복숭아"> <input value="복숭아" name="chkbox" type="checkbox" id="복숭아">
									복숭아
								</label> <label for="민들레"> <input value="민들레" name="chkbox" type="checkbox" id="민들레">
									민들레
								</label> <label for="계란흰자"> <input value="계란흰자" name="chkbox" type="checkbox" id="계란흰자">
									계란흰자
								</label>
							</div>
						</div>
						<div class="form-group">
							<div class="picture_button">
								<label for="user_picture">사진</label> <br>
								<ul class="nav">
									<li>
										<div>
											<img style="width: 120px; height: 160px"
												class="img-responsive img-thumbnail"
												src="../img/맛있는우유GT.jpg"> <a href="index.html" class="btn btn-default">찾아보기</a>
											<a href="index.html" class="btn btn-default">삭제</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<button type="submit"
								class="btn btn-default btn-login-submit btn-block m-t-md">
								완료
							</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>