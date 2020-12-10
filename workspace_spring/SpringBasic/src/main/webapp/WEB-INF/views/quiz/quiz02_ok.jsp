<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>${user.id }(${user.name })님 가입은 환영합니다 !</h2>
	<hr/>
	<p>
	<h3>- 회원정보 -</h3>
	ID : ${user.id }<br/>
	PW : ${user.pw }<br/>
	NAME : ${user.name }<br/>
	E-MAIL : ${user.email }<br/>
	</p>

</body>
</html>