<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<script>
	$(document).ready(function() {
		
		var joinable = false; // 회원가입 가능여부
		
		if($('[name="uid"]').is(":empty")) {
			if(!$('[name="upw"]').is(":empty") || !$('[name="uname"]').is(":empty")) {
				$("#msgId").text("아이디를 입력해주세요.");
				$('[name="uid"]').focus();
			}
		} else if($('[name="upw"]').is(":empty")) {
			//$('[name="upw"]').attr('disabled', false);
			if(!$('[name="uname"]').is(":empty")) {
				$("#msgPw").text("비밀번호를 입력해주세요.");
				$('[name="upw"]').focus();
			}
		}

		function txtFieldCheck(){
			
			// form안의 모든 text type 조회
			var txtEle = $("#joinForm input[type=text]");
			joinable= true;
		 
			for(var i = 0; i < txtEle.length; i ++){
				if("" == $(txtEle[i]).val() || null == $(txtEle[i]).val()){
					var label = $("label[for='input" + (i+1) + "']").text();
					alert(label + "을 입력해주세요.");
					$(txtEle[i]).focus();
					joinable = false;
				}
			}
			
			
		}
		
		$("#btnJoin").click(function() {
			txtFieldCheck();
			if(joinable) {
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
					            alert('Unknow Error.n ' + data);
					        }
						}
					});
			}
		});
			
		
		
		$("#btnIdDuplChk").click(function() {
			$.ajax({
				type: 'post',
				async: false,
				url: '/user/join/idduplchk',
				data: $('[name="uid"]').serialize(),
				success: function(response) {
					if(!response) {
						$("#msgId").css('color', 'green');
						$("#msgId").text("사용 가능한 아이디입니다.");
						$("#btnJoin").attr('disabled', false);
					} else {
						$("#msgId").css('color', 'red');
						$("#msgId").text("사용 불가능한 아이디입니다.");
						$("#btnJoin").attr('disabled', true);
					}
				},
				error: function(data, status, error) {
					if(status == 0){
			            alert('You are offline!!n Please Check Your Network.');
			        }else if(status == 404){
			            alert('Requested URL not found.');
			        }else if(status == 500){
			            alert('Internel Server Error.');
			        }
					
			        if(error =='parsererror'){
			            alert('Error.nParsing Request failed.');
			        }else if(error =='timeout'){
			            alert('Request Time out.');
			        }else {
			            alert('Unknow Error.n ' + data);
			        }
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
					<td><label for="input1">아이디</label></td>
					<td><input type="text" name="uid" size="10">
					<input type="button" id="btnIdDuplChk" value="중복체크" />
					<div id="msgId" style="font-size:13px;"></div></td>
				</tr>
				<tr>
					<td><label for="input2">비밀번호</label></td>
					<td><input type="password" name="upw" >
					<div id="msgPw" style="font-size:13px;"></div></td>
				</tr>
				<tr>
					<td><label for="input3">이름</label></td>
					<td><input type="text" name="uname" >
					<div id="msgName" style="font-size:13px;"></div></td>
				</tr>
			</table>
			<input type="button" class="button btn1" id="btnJoin" value="회원가입" />
			<input type="button" class="button btn1" id="btnCancel" value="취소" onClick="window.close()" />
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp"%>	