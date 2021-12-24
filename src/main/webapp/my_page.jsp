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

<script>
function delete_user(frm) { 
  frm.action='/eroom_web/EroomServlet?cmd=delete'; 
  frm.submit(); 
} 
</script>

<body>
	<mytag:menubar/>
	
	<div class=section>
	<div class=child_section>
	<div class=sub_header>
	MY PAGE
	</div>
		<form action="/eroom_web/EroomServlet?cmd=update" method="post">
			<ul>
				<li>아이디 <input class="form_input border" type="text" name="id" value="${requestScope.user.id}" readonly></li>
				<li>비밀번호 <input class="form_input border" type="password" name="passwd" value= "${requestScope.user.passwd}" autofocus required></li>
				<li>이름 <input class="form_input border" type="text" name="username" value= "${requestScope.user.username}" required></li>
				<li>핸드폰 <input class="form_input border" type="text" name="mobile" value= "${requestScope.user.mobile}" required></li>
				<li>이메일 <input class="form_input border" type="text" name="email" value= "${requestScope.user.email}" required></li>
			</ul>
			<input class="form_btn_type2 border" type="submit" name="submit" value="회원 정보 수정"><br>
			<input class="form_btn_type3 border" type='submit' value='회원 탈퇴 (경고 메세지없이 탈퇴 됩니다.)' onclick='delete_user(this.form);'>
		</form>
	</div>
	</div>
</body>
</html>