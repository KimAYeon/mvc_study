<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<header>
	<div class="nav_menu_left" onClick='location.href="/board/list"'>
		YEON'S BOARD</div>
	<div class="nav_menu_right">
		<ul>
		<c:choose>
		<c:when test="${empty login}">
			<li class="btn logIn" onClick='popup("/user/login", "로그인");return false;'>로그인</li>
			<li class="btn join" onClick='popup("/user/join", "회원가입");return false;'>회원가입</li>
		</c:when>
		<c:when test="${!empty login}">
			<li>${login.uname } 님 반갑습니다.</li>
			<li class="btn logOut" onClick='location.href="/user/logout"'>로그아웃</li>
			<li class="btn myPage" onClick='popup("/user/join", "회원가입");return false;'>마이페이지</li>
		</c:when>
		</c:choose>
		</ul>
	</div>
</header>