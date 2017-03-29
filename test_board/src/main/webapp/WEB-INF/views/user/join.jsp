<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<script>
	$(document).ready(function() {
		$("#btnJoin").click(function() {
		$.ajax({
			type: 'post',
			async: false,
			url: '/user/join',
			data: $('#joinForm').serialize(),
			success: function(response) {
				switch(response) {			
				case 1 :
					alert("회원 가입 성공!");
					myOpener();
					break;
				case 0 :
					alert("회원 가입 실패...");
					break;
				}
			},
			error: function(data, status, error) {
				console.log(data);
				console.log(status);
				console.log(error);
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
	<div class="userJoin" align="center">
		<form id="joinForm" name="joinForm" method="post" >
			<h1>회원가입</h1>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="upw"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="uname"></td>
				</tr>
			</table>
			<input type="button" class="button btn1" id="btnJoin" value="회원가입" /> 
			<input type="button" class="button btn1" id="btnCancel" value="취소" onClick="window.close()" />
		</form>
	</div>
</div>
		