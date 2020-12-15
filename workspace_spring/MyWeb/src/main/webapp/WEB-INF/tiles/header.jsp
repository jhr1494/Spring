<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<header>
        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <div class="navbar">
                        <div class="navbar-header">
                            <!--data-toggle 같은 것들은 내부적으로 지원하는 반응형 API기능이므로 지우면 안됩니다-->
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#"><img width="30" src="${pageContext.request.contextPath }/resources/img/logo.svg" alt="Brand"></a>
                        </div>


                        <div class="collapse navbar-collapse" id="myNavbar">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="#">Main</a></li>
                                <li><a href="/myweb/freeBoard/freeList">자유게시판</a></li>
                                <!-- 
				<li><a href="#">강의</a></li>
                                <li><a href="#">Form</a></li>
                                <li><a href="#">Board</a></li>
 				-->

                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">로그인
                                        <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><span class="glyphicon glyphicon-user"></span>Join</a></li>
                                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
                                        <li><a href="#"><span class="glyphicon glyphicon-user"></span>MyPage</a></li>
                                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
                                    </ul>
                                </li>
                            </ul>

                            <form class="navbar-form navbar-right" action="">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                    <div class="input-group-btn">

                                        <button class="btn btn-primary" type="submit">
                                           	 검색
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>