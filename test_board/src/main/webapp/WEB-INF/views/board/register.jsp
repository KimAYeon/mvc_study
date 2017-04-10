<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$('[name="writer"]').val("${login.uname}");
		if("${result}"=="1") {
			alert("새 글이 작성되었습니다.");
			myOpener();
		}
		if("${result}"=="0") {
			alert("새 글이 작성되지 않았습니다.");
		}
		
		var template = Handlebars.compile($("#template").html());
		
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event) {
			console.log("drop");
			console.log(event);
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url: '/board/uploadAjax',
				data: formData,
				dataType: 'text',
				processData: false,
				contentType: false,
				type: 'POST',
				success: function(data) {
					alert(data);
					var fileInfo = getFileInfo(data);
					var html = template(fileInfo);
					$(".uploadedList").append(html);
				}
			});
		});
		
		$("#registerForm").submit(function(event) {
			event.preventDefault();
			var str = "";
			$(".uploadedList .remove").each(function(index) {
				str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") + "'>";
			});
			$(this).append(str);
			$(this).get(0).submit();
		});
	});
	
</script>



	<header></header>
		<div class="content">
			<div class="boardRegister" align="center">
				<h2>글쓰기</h2>
				<form id="registerForm" name="register" method="post" action="/board/register">
					<table>
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="writer" readOnly="readOnly"></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" cols="21" rows="10"></textarea></td>
						</tr>
						<tr>
							<td>첨부 파일</td>
							<td><div class="fileDrop"></div>
								<div class="fileBox">
									<div><hr></div>
									<ul class="mailbox-attachments clearfix uploadedList">
										<script id="template" type="text/x-handlebars-template">
										<li>
											<span class="mailbox-attachment-icon has-img"><img src="{{imgSrc}}" alt="Attachment"></span>
											<div class="mailbox-attachment-info">
												<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
												<a href="{{fname}}" class="btn remove">X</a>
											</div>
										</li>
										</script>
									</ul>
								</div>
							</td>
						</tr>
										
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="bpw"></td>
						</tr>
					</table>
					<input type="submit" class="button btn1" name="register" value="저장">
					<input type="button" class="button btn1" name="cancel" value="취소"
						onClick="window.close()">
				</form>
			</div>
		</div>

<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		