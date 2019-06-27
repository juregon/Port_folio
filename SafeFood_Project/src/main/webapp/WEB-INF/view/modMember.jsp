<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.model.dto.*"%>
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
.modform hr {
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
	$('input:checkbox[name=allergys]').each(function() {
	    if($(this).attr('value') == "<%=allergys[i]%>"){ //값 비교
	           $(this).attr('checked', true); //checked 처리
	     }
	});
});
<%
	}
%>

function inputCheck(){
	if(pw1.value != pw2.value){
		alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
		$("input[id='pw2']").focus();
		return ;
	}
	var phone1 = $("input[name=phone1]").val();
	var phone2 = $("input[name=phone2]").val();
	var phone3 = $("input[name=phone3]").val();
	//set to new field
	if (phone1 != "") {
		$("#phone").val(phone1 + '-' + phone2 + '-' + phone3);
	}
	
	var selector = $("select[name=selector]").val();
	var email1 = $("input[name=email1]").val();
	var email2 = $("input[name=email2]").val();
	//set to new field
	if(email1 != "") {
		if (selector == "직접입력") {
			$("#email").val(email1+"@"+email2);
		} else {
			$("#email").val(email1+"@"+selector);
		}
	}
	modForm.submit();
}

</script>
</head>
<body>
    <div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
        <div class="modform">
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
						<input type="password" name="pw" maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>새 비밀번호</span>
					</div>
					<div class="col-md-5">
						<input type="password" name="newPw" id="pw1"
							placeholder="8~16 characters" maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>새 비밀번호 확인</span>
					</div>
					<div class="col-md-5">
						<input type="password" id="pw2"> <img src="img/check_ok.png"
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
					<% if(phone.length == 1){ %>
						<input type="text" name="phone1" maxlength="3" size="3">-
						<input type="text" name="phone2" maxlength="4" size="4">-
						<input type="text" name="phone3" maxlength="4" size="4">
						<input type="hidden" name="phone" id="phone">
					<%} else {%>
						<input type="text" name="phone1" maxlength="3" size="3" placeholder=<%=phone[0] %>>-
						<input type="text" name="phone2" maxlength="4" size="4" placeholder=<%=phone[1] %>>-
						<input type="text" name="phone3" maxlength="4" size="4" placeholder=<%=phone[2] %>>
						<input type="hidden" name="phone" id="phone">
					<%} %>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>이메일</span>
					</div>
					<div class="col-md-6">
					<% String[] email = m.getEmail().split("@"); %>
					<% if(email.length == 1){ %>
						<input type="text" name="email1">@
					<%} else { %>
						<input type="text" name="email1" placeholder=<%=email[0] %>>@
						<input type="text" name="email2" placeholder=<%=email[1] %>>
					<%} %>
						 <select class="select" name="selector">
							<optgroup label="선택">
								<option value="직접입력">직접입력</option>
								<option value="ssafy.com">싸피</option>
								<option value="gmail.com">지메일</option>
								<option value="naver.com">네이버</option>
								<option value="daum.net">다음</option>
							</optgroup>
						</select>
						<input type="hidden" id="email" name="email">
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-3">
						<span>알레르기</span>
					</div>
					<div class="col-md-6">
						<fieldset>
							<legend>CHECK</legend>
							<input type="checkbox" name="allergys" value="대두">대두
							<input type="checkbox" name="allergys" value="땅콩">땅콩
							<input type="checkbox" name="allergys" value="우유">우유
							<input type="checkbox" name="allergys" value="게">게
							<input type="checkbox" name="allergys" value="새우">새우
							<input type="checkbox" name="allergys" value="참치">참치
							<input type="checkbox" name="allergys" value="연어">연어
							<input type="checkbox" name="allergys" value="쑥">쑥
							<input type="checkbox" name="allergys" value="소고기">소고기
							<input type="checkbox" name="allergys" value="닭고기">닭고기
							<input type="checkbox" name="allergys" value="돼지고기">돼지고기
							<input type="checkbox" name="allergys" value="복숭아">복숭아
							<input type="checkbox" name="allergys" value="민들레">민들레
							<input type="checkbox" name="allergys" value="계란흰자">계란흰자
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
    	<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
</body>
</html>