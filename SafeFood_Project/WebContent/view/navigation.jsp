<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
    .container{
        margin-top: 50px;
    }
    .btn-light{
        padding: 10px 15px;
        margin-top : 25px;
        font-size: 15px;
        font-weight: 300;
    }
    </style>
</head>
<body>
    <!-- 네비게이션 바 -->
        <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-header">
            <a class="navbar-brand" onclick=""></a>
        </div>
        <div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
            <ul class="nav navbar-nav">
            <% if(session.getAttribute("id") == null) {%>
                <li><a href="sign_up.mvc">Sign Up</a></li>
                <li><a data-toggle="modal" data-target="#myModal">Login</a></li>
            <%} else {%>
                <li><a href="user_info.mvc">회원 정보</a></li>
                <li><a href="logout.mvc">Logout</a></li>
            <%} %>
            </ul>
        </div>
    </nav>
</body>
    <header class="header">
    <div class="container">
            <div class="row">
                <div class="col-md-2">
                <a href="index.mvc"><img src="img/ssafy.png" alt="ssafy" width="100px" height="100px"></a>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-light"> 공지사항 </button>
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-light"> 베스트 섭취 정보 </button>
                </div>
                <div class="col-md-1  col-md-offset-1">
                    <button type="button" class="btn btn-light"> 내 섭취 정보 </button>
                </div>
                <div class="col-md-1 col-md-offset-1">
                    <button type="button" class="btn btn-light"> 예상 섭취 정보 </button>
                </div>
            </div>
        </div>
        </header>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    			<form action="login_process.mvc" method="post">
    			
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Login</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="user_id">아이디</label> <input class="form-control"
                                        name="user_id" value='' id="user_id" placeholder="ID"
                                        type="text">
                                </div>
                                <div class="form-group">
                                    <label for="user_password">비밀번호</label> <input
                                        class="form-control" name="user_password" id="user_password"
                                        value='' placeholder="Password" type="password">
                                </div>
                                <div class="form-group">
                                    <button type="submit"
                                        class="btn btn-default btn-login-submit btn-block m-t-md"
                                        >Login
                                        </button>
                                </div>
<!--                                 <span class="text-center"><a href="index.html" -->
<!--                                     class="text-sm">비밀번호 찾기</a></span> -->
                                <hr>
                                <div class="form-group">
                                    <a href="sign_up.mvc" class="btn btn-default btn-block m-t-md">회원가입</a>
                                </div>
                            </div>    
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                     </form>
                </div>
               
</html>