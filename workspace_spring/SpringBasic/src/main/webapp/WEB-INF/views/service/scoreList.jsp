<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>점수결과 확인</h2>
	
	<c:forEach var="list" items="${list }" varStatus="num"><!-- varStatus : var변수의 상태 -->
		번호 : ${list.num }
		이름 : ${list.name }
		국어 : ${list.kor }
		영어 : ${list.eng }
		수학 : ${list.math }
		<button type="button" onclick="location.href='scoreDelete?num=${list.num}' ">점수삭제</button>
		<br>
	</c:forEach>
	
	<a href="scoreRegist">점수 추가 등록</a>
	
	
	
</body>
</html>