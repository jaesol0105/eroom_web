<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="kpu.web.club.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div class=sub_header><b>${msg}</b></div>
		
		<c:if test="${cmd eq 'join'}">
			<c:if test="${register_id != null}">
				<p>${register_id}님 회원가입을 축하합니다.</p>
			</c:if>
			<c:if test="${register_id == null }">
				<p>중복된 아이디 또는 잘못된 입력 입니다.</p>
			</c:if>
		</c:if>
		
		<c:if test="${cmd eq 'delete'}">
			<c:if test="${state eq 'fail'}">
				<p>예약 중인 좌석이 있을경우 회원 탈퇴 하실 수 없습니다.<br>예약 취소 후 시도해주세요.</p>
			</c:if>
		</c:if>
		
		<c:if test="${cmd eq 'do_reserve'}">
			<c:if test="${seat_no != null}">
				<br>
				<c:if test="${seat_no == 'exist'}">
				<p>이룸 독서실은 <font id=red>1인 1좌석</font>만 예약할 수 있습니다.</p>
				<p><b>예약 취소 후 다시 예약해주세요.</b></p>
				</c:if>
				<c:if test="${seat_no != 'exist'}">
				<p><font class=font>${seat_no}번</font></p>
				</c:if>
			</c:if>
		</c:if>
		
		<p id=go_main><a href="main_page.jsp" target="_self"> 메인 페이지로 이동 </a></p>
	</div>
	</div>
</body>
</html>