<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script>
	$(document).ready(function() {
		
		$('[name="writer"]').val("${boardVO.writer}");
		$('[name="title"]').val("${boardVO.title}");
		$('[name="content"]').val("${boardVO.content}");
		$('[name="bpw"]').val("${boardVO.bpw}");
		if("${login}"!="") {
			$('[name="title"]').attr('readOnly', false);
			$('[name="content"]').attr('readOnly', false);
		}
		
		if ("${resultModify}" == "1") {
			alert("글이 수정되었습니다.");
			myOpener();
		}
		if ("${resultModify}" == "0") {
			alert("글이 수정되지 않았습니다.");
		}
		if ("${resultRemove}" == "0") {
			alert("글이 삭제되지 않았습니다.");
		}
		 
	});
	
	function dialogPwMat(type) {
		if("${login.urole}"<2) {
			$("#dialogPwMat").dialog({
	            modal: true,
	            resizable: false,
	            buttons:{
	                "확인": function(){
	               	 	$.ajax({
	        				type: 'post',
	        				async: false,
	        				url: '/board/read/pwmatch/${boardVO.bno}',
	        				data: $('[name="matbpw"]').serialize(),
	        				success: function(response) {
	        					if(response) {
	        						onSubmit(type);
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
		} else {
			onSubmit(type);
		}
	}
	
	function onSubmit(type) {
		document.boardForm.action = "/board/" + type + "/" + ${boardVO.bno};
		document.boardForm.submit();
	}
	
</script>

	<header></header>
		<div class="content">
			<div class="boardRead" align="center">
				<h2>글읽기</h2>
				<form name="boardForm" method="post">
					<table>
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="writer" readOnly="readOnly"></td>
							<td><input type="hidden" name="bpw"></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" readOnly="readOnly"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" cols="21" rows="10" readOnly="readOnly"></textarea></td>
						</tr>
						<tr>
							<td>조회수</td>
							<td>${boardVO.viewcnt}</td>
						</tr>
						<tr>
							<td>등록날짜</td>
							<td><fmt:formatDate value="${boardVO.regdate}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
						<tr>
							<td>수정날짜</td>
							<td><fmt:formatDate value="${boardVO.moddate}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
					</table>
					<c:if test="${!empty login}">
						<input type="button" class="button btn1" name="modify" value="수정" onClick='dialogPwMat("modify")' />
						<input type="button" class="button btn1" name="remove" value="삭제" onClick='dialogPwMat("remove")' />
					</c:if>
					<input type="button" class="button btn1" name="cancel" value="취소" onClick="window.close()" />
				</form>
			</div>
		</div>
		
		<div id="dialogPwMat" title="비밀번호 확인" align="center">
			<label style="font-size:17px;">비밀번호&nbsp;&nbsp;<input type="password" name="matbpw" size="15"/></label>
		</div>
		

<%@ include file="/WEB-INF/views/include/footer.jsp"%>