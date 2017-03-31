<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

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
			}
		},
		error: function(data, status, error) {
			if(status == 0){
	            alert('You are offline!!n Please Check Your Network.');
	        }else if(status == 404){
	            alert('Requested URL not found.');
	        }else if(status == 500){
	            alert('Internel Server Error.');
	        }else if(error =='parsererror'){
	            alert('Error.nParsing Request failed.');
	        }else if(error =='timeout'){
	            alert('Request Time out.');
	        }else {
	            alert('Unknow Error.n'+ data);
	        }
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

<%@ include file="/WEB-INF/views/include/footer.jsp"%>