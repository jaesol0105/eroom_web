<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
		<div class="helper"></div>
		<span id=menu-bar>
			<c:if test="${sessionScope.cookie == null}">
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=login_required" target="_self">좌석 예약</a></span>		
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=login_required" target="_self">MY PAGE</a></span>
				<span class=menu-icon><a class=btn href="login.jsp">로그인</a></span>
			</c:if>
		
			<c:if test="${sessionScope.cookie != null}">
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=reserve" target="_self">좌석 예약</a></span>
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=reserve_cancel" target="_self">예약 취소</a></span>		
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=mypage" target="_self">MY PAGE</a></span>
				<span class=menu-icon><a class=btn href="/eroom_web/EroomServlet?cmd=logout">로그아웃</a></span>
			</c:if>
		</span>
		<span class=logo><a href="main_page.jsp" target="_self"><image id=ci src="resources/ci.png"></a></span>
		<HR class=clear>
</div>