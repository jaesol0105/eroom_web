<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-room 독서실</title>
<link rel="stylesheet" href="resources/style.css" type="text/css"></link>
</head>

<body>
	<mytag:menubar/>

	<div class=section>
	<div class=child_section>
	<div class=sub_header>
	회원가입
	</div>
		<form action="/eroom_web/EroomServlet?cmd=join" method="post">
			<ul>
				<li>아이디 <input class="form_input border" type="text" name="id" autofocus required placeholder="공백없이 입력하세요"></li>
				<li>비밀번호 <input class="form_input border" type="password" name="passwd" required placeholder="공백없이 입력하세요"></li>
				<li>이름 <input class="form_input border" type="text" name="username" required placeholder="공백없이 입력하세요"></li>
				<li>핸드폰 <input class="form_input border" type="text" name="mobile" placeholder="***.****.****"></li>
				<li>이메일 <input class="form_input border" type="text" name="email" placeholder="****@****.***"></li>
			</ul>
			<input class="form_btn_type2 border" type="submit" name="submit" value="회원가입">
		</form>
	</div>
	</div>
</body>
</html>