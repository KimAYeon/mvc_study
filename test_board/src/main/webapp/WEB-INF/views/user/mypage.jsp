<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script>
	$(document).ready(function() {
		$('[name="upw"]').val("${login.upw}");
		$('[name="uname"]').val("${login.uname}");
		if("${login.urole}"==1) {
			$('#urole').text("회원");
		}
		if("${login.urole}"==2) {
			$('#urole').text("관리자");
		}
		
		if ("${resultModify}" == "1") {
			alert("회원 정보가 수정되었습니다.");
		}
		if ("${resultModify}" == "0") {
			alert("회원 정보가 수정되지 않았습니다.");
		}
		if ("${resultLeave}" == "0") {
			alert("회원 탈퇴가 되지 않았습니다.");
		}
		
	});
	
	function dialogPwMat2(type) {
		$("#dialogPwMat2").dialog({
            modal: true,
            resizable: false,
            buttons:{
                "확인": function(){
               	 	$.ajax({
        				type: 'post',
        				async: false,
        				url: '/user/mypage/pwmatch/${login.uid}',
        				data: $('[name="matupw"]').serialize(),
        				success: function(response) {
        					if(response) {
        						document.userForm.action = "/user/" + type + "/${login.uid}";
        						document.userForm.submit();
        					} else {
        						alert("비밀번호가 맞지 않습니다.");
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
        			            alert('Unknow Error.n ' + data + error);
        			        }
        				}
        			});                 
               	},
               	"취소": function(){
                    $(this).dialog("close");
                }
            }
        });
	}
</script>

<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="content">
		<div class="userPage" align="center">
				<h2>회원 정보</h2>
				<form name="userForm" method="post">
					<table>
						<tr>
							<td>아이디</td>
							<td>${login.uid}</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="upw"></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input type="text" name="uname"></td>
						</tr>
						<tr>
							<td>회원 등급</td>
							<td id="urole"></td>
						</tr>
						<tr>
							<td>가입날짜</td>
							<td><fmt:formatDate value="${login.regdate}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
						<tr>
							<td>수정날짜</td>
							<td><fmt:formatDate value="${login.moddate}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
					</table>
					<input type="button" class="button btn1" name="modify" value="수정" onClick='dialogPwMat2("modify")' />
					<input type="button" class="button btn1" name="leave" value="탈퇴" onClick='dialogPwMat2("leave")' /> 
				</form>
			</div>
		</div>
		<div id="dialogPwMat2" title="비밀번호 확인" align="center">
			<label style="font-size:17px;">비밀번호&nbsp;&nbsp;<input type="password" name="matupw" size="15"/></label>
		</div>
		
<%@ include file="/WEB-INF/views/include/footer.jsp"%>