<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>먹거리 프로젝트</title>
<style type="text/css">
.col-md-2, .col-md-3, .col-md-5, .col-md-6, .col-md-8 {
	text-align: left;
}
.row {
	padding-top: 5px;
	padding-bottom: 5px;
}
.button {
	height: 25px;
	color: white;
	background-color: #3c3c3c;
	margin-left: 3px;
	border: none;
	color: white;
}
.col-md-2 {
	text-align: right;
}
.joinform hr {
	width: 900px;
	border: 0.5px solid #b4b4b4;
	align-self: center;
}
.select {
	height: 25px;
}
#passcheck {
	display: none;
}
</style>
<script type="text/javascript">
	function inputCheck() {
		if(id.value=="") {
			$("#msg").show();
			$("#msg").html("<strong>아이디</strong>를 입력해주세요.");
			$("input[id='id']").focus();
			return;
		}
		if(pw1.value=="") {
			$("#msg").show();
			$("#msg").html("<strong>비밀번호</strong>를 입력해주세요.");
			$("input[id='pw1']").focus();
			return;
		}
		if (pw1.value != pw2.value) {
			$("#msg").show();
			$("#msg").html("<strong>비밀번호</strong>와 <strong>비밀번호 확인</strong>이 일치하지않습니다.");
			$("input[id='pw2']").focus();
			return;
		}
		if(fullname.value == "") {
			$("#msg").show();
			$("#msg").html("<strong>이름</strong>을 입력해주세요.");
			$("input[id='fullname']").focus();
			return;
		}
		var phone1 = $("input[name=phone1]").val();
		var phone2 = $("input[name=phone2]").val();
		var phone3 = $("input[name=phone3]").val();
		//set to new field
		if (phone1 == "") {
			$("#phone").val("");
		} else {
			$("#phone").val(phone1 + '-' + phone2 + '-' + phone3);
		}
		
		var selector = $("select[name=selector]").val();
		var email1 = $("input[name=email1]").val();
		var email2 = $("input[name=email2]").val();
		//set to new field
		if(email1 == "") {
			$("#email").val("");
		} else {
			if (selector == "직접입력") {
				$("#email").val(email1+"@"+email2);
			} else {
				$("#email").val(email1+"@"+selector);
			}
		}
		joinForm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<hr>
		<div class="alert alert-danger alert-dismissible" id="msg" style="display: none;">
  		</div>
		<div class="joinform">
			<div class="row container">
				<div class="col-md-offset-2 col-md-8">
					<h2>Join</h2>
				</div>
				<div class="col-md-offset-2"></div>
			</div>
			<hr>
			<form action="joinMember.mvc" name="joinForm">
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>ID</span>
					</div>
					<div class="col-md-5">
						<input type="text" name="id" id="id">
					</div>
					<div class="col-md-offset-2"></div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>비밀번호</span>
					</div>
					<div class="col-md-5">
						<input type="password" id="pw1" name="pw"
							maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>비밀번호 확인</span>
					</div>
					<div class="col-md-5">
						<input type="password" id="pw2"  maxlength="16"> <img
							src="img/check_ok.png" width="20px" height="20px" id="passcheck">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이름</span>
					</div>
					<div class="col-md-5">
						<input type="text" name="name" id="fullname">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>주소</span>
					</div>
					<div class="col-md-5">
						<input type="text" name="address">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>연락처</span>
					</div>
					<div class="col-md-5">
						<input type="text" name="phone1" maxlength="3" size="3">-
						<input type="text" name="phone2" maxlength="4" size="4">-
						<input type="text" name="phone3" maxlength="4" size="4"> 
						<input type="hidden" name="phone" id="phone">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이메일</span>
					</div>
					<div class="col-md-6">
						<input type="text" name="email1">@<input type="text"
							name="email2"> <select class="select" name="selector">
							<optgroup label="선택">
								<option value="직접입력">직접입력</option>
								<option value="ssafy.com">싸피</option>
								<option value="gmail.com">지메일</option>
								<option value="naver.com">네이버</option>
								<option value="daum.net">다음</option>
							</optgroup>
						</select>
						<input type="hidden" name="email" id="email">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>알레르기</span>
					</div>
					<div class="col-md-6">
						<fieldset>
							<legend>CHECK</legend>
							<input type="checkbox" name="allergys" value="대두">대두 <input
								type="checkbox" name="allergys" value="땅콩">땅콩 <input
								type="checkbox" name="allergys" value="우유">우유 <input
								type="checkbox" name="allergys" value="게">게 <input
								type="checkbox" name="allergys" value="새우">새우 <input
								type="checkbox" name="allergys" value="참치">참치 <input
								type="checkbox" name="allergys" value="연어">연어 <input
								type="checkbox" name="allergys" value="쑥">쑥 <input
								type="checkbox" name="allergys" value="소고기">소고기 <input
								type="checkbox" name="allergys" value="닭고기">닭고기 <input
								type="checkbox" name="allergys" value="돼지고기">돼지고기 <input
								type="checkbox" name="allergys" value="복숭아">복숭아 <input
								type="checkbox" name="allergys" value="민들레">민들레 <input
								type="checkbox" name="allergys" value="계란흰자">계란흰자
						</fieldset>
					</div>
				</div>

				<div class="row">
					<div class="col-md-offset-8 col-md-2">
						<input type="button" class="button" value="회원 가입"
							onclick="inputCheck()">
					</div>
					<div class="col-md-offset-2"></div>
				</div>
			</form>
		</div>
	</div>
	<footer>
		<jsp:include page="footer.jsp" flush="false"/>
	</footer>
</body>
</html>