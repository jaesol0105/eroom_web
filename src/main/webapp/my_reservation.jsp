<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	예약 취소
	</div>
	<br>
	${msg}
	<c:if test="${rsv != '0'}">
	<form action="/eroom_web/EroomServlet?cmd=do_reserve_cancel" method="post">
    	<font class=font>${rsv}번</font> 좌석입니다.
    	아래의 버튼을 누르면 예약이 취소됩니다.
    	<br><br>
		<input class="reserve_btn border" id="submit_btn" type="submit" name="submit" value="좌석 예약 취소">
	</form>
	</c:if>
	</div>
	</div>
</body>
</html>