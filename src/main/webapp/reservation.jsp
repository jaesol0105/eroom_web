<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>E-room 독서실</title>
<link rel="stylesheet" href="resources/style.css" type="text/css"></link>
</head>

<script>
var selected=-1;
function desel(no){
	td_ = document.getElementById(no);
	td_.style.backgroundColor="#EEE";
	td_.style.fontWeight="normal";
	td_.style.color="Black";
	input_ = document.getElementById('seat_no');
	input_.value = ' ';
	button_ = document.getElementById('submit_btn');
	button_.disabled = true;
	button_.backgroundColor="#EEE";
}
function sel(no){
	td_ = document.getElementById(no);
	td_.style.backgroundColor="#337ab7";
	td_.style.fontWeight="Bold";
	td_.style.color="White";
	input_ = document.getElementById('seat_no');
	input_.value = no;
	button_ = document.getElementById('submit_btn');
	button_.disabled = false;
	button_.backgroundColor="#337ab7";
}
function td_click(now){
	if (selected == -1) {
		sel(now);
		selected = now;
	}
	else if (now == selected) {
		desel(now);
		selected=-1;
	}
	else {
		desel(selected);
		sel(now);
		selected = now;
	}
}
</script>

<body>
	<mytag:menubar/>

	<sql:query var="rs" dataSource="jdbc/mysql">
	select idx,seat_no,state from eroom_seat
	</sql:query>

	<div class=section>
	<div class=child_section>
	<div class=sub_header>
	좌석 예약
	</div>
	<p><div id=green_box></div> 이미 예약된 좌석. <div id=white_box></div> 이용 가능한 좌석<br></p>
	<p><b>좌석번호를 클릭하면 선택됩니다.</b></p>
	<table border=1>
		<c:forEach var="row" items="${rs.rows}">
			<c:if test="${row.idx % 5 == 1}"> <tr> </c:if>
			<td>
			<c:if test="${row.state eq '1'}">
				<div class=table_content_1>${row.seat_no}</div>
			</c:if>
 			<c:if test="${row.state eq '0'}">
				<div class=table_content_0 id="${row.seat_no}" onclick="td_click(${row.seat_no})">${row.seat_no}</div>
			</c:if>
			<c:if test="${row.state eq '2'}">
				${row.seat_no}
			</c:if>
 			</td>
			<c:if test="${row.idx % 5 == 0}"> </tr> </c:if>
		</c:forEach>
	</table>
	<div><font class="notice_msg">* 예약 정보는 매일 자정 초기화됩니다.</font></div>
	<br>
	<form action="/eroom_web/EroomServlet?cmd=do_reserve" method="post">
		현재 선택된 좌석 - <input class="seat_no border" id="seat_no" name="seat_no" type="text" readonly>번  &nbsp;&nbsp;
		<input class="reserve_btn border" id="submit_btn" type="submit" name="submit" value="좌석 예약하기" disabled>
	</form>
	</div>
	</div>
</body>
</html>