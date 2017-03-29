<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<script>
$(document).ready(function() {
	$("#btnLogin").click(function() {
	$.ajax({
		type: 'post',
		async: false,
		url: '/user/login',
		data: $('#loginForm').serialize(),
		success: function(response) {
			switch(response) {			
			case "success" :
				alert("로그인 성공!");
				myOpener();
				break;
			case "nomatch" :
				alert("아이디 또는 비밀번호가 일지하지 않습니다.");
				break;
			case "nouser" :
				alert("존재하지 않는 사용자입니다.");
				break;
			}
		},
		error: function(data, status, error) {
			alert("data: " + data +
					"status: " + status +
					"eror: " + error);
		}
	});
	});
});

</script>
<header></header>
<div class="content">
	<div class="userLogin" align="center">
		<form id="loginForm" name="loginForm" method="post" >
			<h1>로그인</h1>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="upw"></td>
				</tr>
			</table>
			<input type="button" class="button btn1" id="btnLogin" value="로그인" /> 
			<input type="button" class="button btn1" id="btnCancel" value="취소" onClick="window.close()" />
		</form>
	</div>
</div>
