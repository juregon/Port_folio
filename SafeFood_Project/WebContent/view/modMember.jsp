<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style><%@ include file="basiclayout.css"%></style>
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

<% 
	if (request.getAttribute("status") != null) {
		String status = (String) request.getAttribute("status");
		if (status.equals("fail")) {
%>
			alert('수정 실패 : 패스워드가 일치하지 않습니다.');
<%
		}
	}
	Member m = (Member) request.getAttribute("member");

	String[] allergys = m.getAllergys();
	for(int i = 0; i < allergys.length; i++) {
%>
$(document).ready(function() {
	$('input:checkbox[name=al]').each(function() {
	    if($(this).attr('value') == "<%=allergys[i]%>"){ //값 비교
	           $(this).attr('checked', true); //checked 처리
	     }
	});
});
<%
	}
%>

function inputCheck(){
	if(pass1.value != pass2.value){
		alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
		$("input[id='pass2']").focus();
		return ;
	}
	modForm.submit();
}

</script>
</head>
<body>
    <div class="container">
		<header>
			<!-- nav : 네비게이션 시작을 알려주는 태그 (div로 해도되긴하지만 알려주기위해 사용)
			 navbar navbar-default: 배경 색상과 테투리를 지정해 주는 역할
			 navbar-fixed-top: 화면 상단에 고정 -->
			<nav class="navbar navbar-default navbar-fixed-top navbar-inverse"
				role="navigation">
				<div class="container">
					<div class="navbar-header">
						<!-- 화면 사이즈가 크면 안보임, 모바일 정도로 작아지면 보임 -->
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-ex1-collapse">
							<span class="sr-only">toggle navigation</span>
							<!-- screen reader only  없어도 되는 코드임. 화면상 아무 일 없다-->
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="main.mvc"><img
							src="https://edu.ssafy.com/asset/images/logo.png" height="50px"
							width="70px"></a>
					</div>
					<!-- class="navbar-header" -->

					<!-- navbar-collapse: 화면 사이즈가 작으면 안보임 -->
					<div
						class="collapse navbar-collapse navbar-left navbar-ex1-collapse">
						<ul class="nav navbar-nav" id="menu">
							<li><a href="#">공지 사항</a></li>
							<li><a href="list.mvc">상품 정보</a></li>
							<li><a href="#">베스트 섭취 정보</a></li>
							<li><a href="intakeInfo.mvc">내 섭취 정보</a></li>
							<li><a href="#">예상 섭취 정보</a></li>
						</ul>
					</div>
					<div
						class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
						<ul class="nav navbar-nav" id="menuMember">
							<% if(session.getAttribute("id") != null) {
							String id = (String) session.getAttribute("id");
							%>
							<li><a href="logout.mvc">Logout</a></li>
							<li><a href="memberInfo.mvc">회원정보</a></li>
							<%}else{ %>
							<li><a href="login.mvc">Login</a></li>
							<li><a href="join.mvc">Join</a></li>
							<%} %>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	</div>
    <!-- 본문 -->
    <div class="container">
        <!-- 배너 -->
        <div id="carousel-example-generic" class="carousel slide">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0"
                    class="active"></li>
                <!-- <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
            </ol>
            <!-- Carousel items -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="img/banner_img.png" alt="First slide">
                </div>
                <!-- 
                <div class="item">
                   <img src="./slide2.jpg" alt="Second slide">               
                </div>
                <div class="item">
                   <img src="./slide3.jpg" alt="Third slide">                 
                </div>
                 -->
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic"
                data-slide="prev"> <span class="icon-prev"></span>
            </a> <a class="right carousel-control" href="#carousel-example-generic"
                data-slide="next"> <span class="icon-next"></span>
            </a>
        </div>
        <div class="joinform">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <h2>회원 정보 수정</h2>
                </div>
                <div class="col-md-offset-2"></div>
            </div>
            <hr>
            <form action="modMemberInfo.mvc" name="modForm">
                <div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>ID</span>
					</div>
					<div class="col-md-5">
						<input type="text"
							style="border: none; background-color: transparent" name="id"
							value="<%=m.getId()%>">
					</div>
					<div class="col-md-offset-2"></div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>현재 비밀번호</span>
					</div>
					<div class="col-md-5">
						<input type="password" name="pass" maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>새 비밀번호</span>
					</div>
					<div class="col-md-5">
						<input type="password" name="newPass" id="pass1"
							placeholder="8~16 characters" maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>새 비밀번호 확인</span>
					</div>
					<div class="col-md-5">
						<input type="password" id="pass2"> <img src="img/check_ok.png"
							width="20px" height="20px" id="passcheck">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이름</span>
					</div>
					<div class="col-md-5">
						<span><%=m.getName()%></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>주소</span>
					</div>
					<div class="col-md-5">
						<input type="text" name="address" placeholder=<%=m.getAddress()%>>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>연락처</span>
					</div>
					<div class="col-md-5">
					<% String[] phone = m.getPhone().split("-"); %>
						<input type="text" name="phone1" maxlength="3" size="3" placeholder=<%=phone[0] %>>-
						<input type="text" name="phone2" maxlength="4" size="4" placeholder=<%=phone[1] %>>-
						<input type="text" name="phone3" maxlength="4" size="4" placeholder=<%=phone[2] %>>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이메일</span>
					</div>
					<div class="col-md-6">
					<% String[] email = m.getEmail().split("@"); %>
						<input type="text" name="email1" placeholder=<%=email[0] %>>@<input type="text"
							name="email2" placeholder=<%=email[1] %>> <select class="select" name="selector">
							<optgroup label="선택">
								<option value="직접입력">직접입력</option>
								<option value="ssafy.com">싸피</option>
								<option value="gmail.com">지메일</option>
								<option value="naver.com">네이버</option>
								<option value="daum.net">다음</option>
							</optgroup>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>알레르기</span>
					</div>
					<div class="col-md-6">
						<fieldset>
							<legend>CHECK</legend>
							<input type="checkbox" name="al" value="대두">대두 <input
								type="checkbox" name="al" value="땅콩">땅콩 <input
								type="checkbox" name="al" value="우유">우유 <input
								type="checkbox" name="al" value="게">게 <input
								type="checkbox" name="al" value="새우">새우 <input
								type="checkbox" name="al" value="참치">참치 <input
								type="checkbox" name="al" value="연어">연어 <input
								type="checkbox" name="al" value="쑥">쑥 <input
								type="checkbox" name="al" value="소고기">소고기 <input
								type="checkbox" name="al" value="닭고기">닭고기 <input
								type="checkbox" name="al" value="돼지고기">돼지고기 <input
								type="checkbox" name="al" value="복숭아">복숭아 <input
								type="checkbox" name="al" value="민들레">민들레 <input
								type="checkbox" name="al" value="계란흰자">계란흰자
						</fieldset>
					</div>
				</div>

				<div class="row">
					<div class="col-md-offset-8 col-md-2">
						<input type="button" class="button" onclick="inputCheck()" value="수정 완료">
                    </div>
                    <div class="col-md-offset-2"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="container-fluid">
		<footer>
			<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse"
				role="navigation">
				<div class="container">
					<div
						class="collapse navbar-collapse navbar-left navbar-ex1-collapse">
						<h4>findUs</h4>
						<hr>
						<ul class="footerLink">
							<li><a href="#">(SSAFY) 서울시 강남구 테헤란로 멀티스퀘어</a></li>
							<li><a href="#">1544-9001</a></li>
							<li><a href="#">admin@ssafy.com</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</footer>
	</div>
</body>
</html>