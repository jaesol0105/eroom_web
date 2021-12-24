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
var login_st = "${param.login}";
if(login_st == "login_required")
{
  alert("로그인이 필요한 작업입니다.");
}
</script>

<body>
	<mytag:menubar/>
	
	<div class=section>
	<div class=child_section>
	<div class=sub_header>
	로그인
	</div>
		<form action="/eroom_web/EroomServlet?cmd=login" method="post">
			<ul>
	  			<li>아이디 <input class="form_input border" type="text" name="id" autofocus required placeholder="공백없이 입력하세요"/><br/></li>
      			<li>비밀번호 <input class="form_input border" type="password" name="passwd" required placeholder="공백없이 입력하세요"/><br/></li>
    		</ul>
    		<input class="form_btn border" type="submit" value="로그인"> &nbsp;&nbsp;
    		<a href="register.jsp">회원가입</a> &nbsp;&nbsp;&nbsp;&nbsp;
    		<a href="main_page.jsp">메인 페이지로</a>
    	</form>
	</div>
	</div>
</body>
</html>