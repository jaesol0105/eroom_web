<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-room 독서실</title>
<link rel="stylesheet" href="resources/style.css" type="text/css"></link>
</head>

<script>
var login_st = "${param.login}";
if(login_st == "success")
{
  	alert("${sessionScope.welcome_user}님 환영합니다.");
}
else if (login_st == "fail") {
 	alert("로그인 실패! 아이디 또는 비밀번호가 일치하지 않습니다.");
}
</script>

<body>
	<mytag:menubar />

	<div id=main>
	<div class=child_section>
		<div class="main_header">스마트 독서실 E-room</div>
		<div class="main_header main_header_sub">for KPU Students</div>
	</div>
	</div>
</body>
</html>