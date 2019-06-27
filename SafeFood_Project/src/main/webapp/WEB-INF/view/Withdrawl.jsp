<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원탈퇴</title>
<style type="text/css">
#h2 {
	margin-top: 50px;
	margin-left: 20px;
	color: black;
}

#h3 {
	margin-left: 20px;
	color: red;
}

#cancel{
background-color: highlight;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="header.jsp" flush="false"/>
		</header>
		<!-- 본문 -->
		<h2 id="h2">회원 탈퇴</h2>
		<h3 id="h3">탈퇴 전 꼭 읽어주세요.</h3>
		<hr>
		<h4 class="text-primary">개인정보 취급안내</h4>
		<table class="table">
			<thead>
				<tr>
					<th>구분</th>
					<th>재가입기준(새로운ID)</th>
					<th>재가입기준(동일ID)</th>
					<th>비고</th>
				</tr>
			</thead>
			<tr>
				<td>거래내역이 없는 회원</td>
				<td>가능</td>
				<td>불가능</td>
				<td>예전 사용했던 아이디로 재가입 불가능, 새로운 아이디로만 가입 가능</td>
			</tr>
			<tr>
				<td>거래내역이 있는 회원</td>
				<td>가능</td>
				<td>불가능</td>
				<td>개인구매회원의 경우 항상 새로운 아이디로만 가입 가능</td>
			</tr>
			<tr>
				<td>영구정지 회원</td>
				<td>불가능</td>
				<td>불가능</td>
				<td>영구정지회원은 재가입 자체가 불가능함</td>
			</tr>
		</table>
		<hr>
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> 회원탈퇴 유의사항 전문보기 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">회원탈퇴를 위해선 아래 5가지 조건 확인이 필요합니다. 판매 또는
						구매가 진행중인 상품이 없어야 합니다. 마이너스 또는 현금성 판매예치금이 없어야 합니다.
						(-) Smile Cash가 없어야 합니다. 가상계좌 잔액이 없어야 합니다. 회원탈퇴 시 보유하고 계신 쿠폰은 즉시 소멸되며, 동일한 아이디로
						재가입 하더라도 복원되지 않습니다.</div>
				</div>
			</div>
		</div>
			
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> 고객센터 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
					Tel : 1588-0184 (평일 09:00~18:00)
					스마일클럽 & VVIP : 1522-8900 (365일 09:00~18:00)
					경기도 부천시 원미구 부일로 223 (상동) 투나빌딩 6층
					Fax : 02-589-8829Mail : information@corp.auction.co.kr
					</div>
				</div>
			</div>
		</div>
		<br>
		<br>
		<div class="btn-toolbar">
	     	<div class="btn-group">
		         <input type="button" class="btn btn-default" value="회원탈퇴 " onclick="location.href='withdrawlOk.mvc'"></button>
	    	</div> 
	    	<div class="btn-group">
		         <input type="button" id="cancel" value="취소" onclick="location.href='main.mvc'" class="btn btn-default"></button>      
			</div>
	    </div>
		<!-- end of container -->
		<footer>
			<jsp:include page="footer.jsp" flush="false"/>
		</footer>
	</div>
</body>
</html>