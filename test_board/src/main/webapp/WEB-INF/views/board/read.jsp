<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script>
	var removeFiles = new Array();
	var login_str = "<input type='button' name='btnReplyToggle' onClick='replyToggle(" + this.rno + ");' value='답글'/>"
				+ "<div class='replyToggle"+this.rno+"'><input type='text' class='replytext' name='"+this.replyer+"' />"
				+ "<input type='button' name='btnReplyRegister' onClick='replyRegister(" + this.rno + ");' value='추가'/></div>"
				+ "<br/><a href='#' onClick='reReplyList("+this.rno+");return false;'>>></a><div class='reReply"+this.rno+"'></div>";
	
	function replyRegister(rno, object) {
		
		$.ajax({
			type: 'post',
			url: '/board/reply/register',
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType: 'text',
			data: JSON.stringify({
				bno: "${boardVO.bno}",
				replyer: "${login.uname}",
				replytext: $(object).parent().children(".replytext").val(),
				upper: rno
			}),
			success: function(response) {
				if(response == "SUCCESS") {
					alert("댓글 등록 완료");
					window.location = "/board/read/${boardVO.bno}";
				}
			}
		}); 
	}

	
	function replyModify(rno) {
		$.ajax({
			type: 'post',
			url: '/board/reply/modify',
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType: 'text',
			data: JSON.stringify({
				rno: rno,
				bno: "${boardVO.bno}",
				replyer: "${login.uname}",
				replytext: $('#replytext'+rno).val()
			}),
			success: function(response) {
				if(response == "SUCCESS") {
					alert("댓글 수정 완료");
					window.location = "/board/read/${boardVO.bno}";
				}
			}
		});
	}
	
	function reReplyList(rno) {
		
		console.log(rno);
		$.getJSON("/board/reply/replyList/"+rno, function(data) {
			console.log(data);
			var str = "";
			$(data).each(function() {
				str += "<tr><td>" + this.replyer + "</td>"
					+ "<td>" + this.replytext 
					+ "<input type='button' name='btnReplyToggle' onClick='replyToggle(" + this.rno + ");' value='답글'/>"
					+ "<div class='replyToggle"+this.rno+"'>"
					+ "<input type='text' class='replytext' name='"+this.replyer+"' />"
					+ "<input type='button' name='btnReplyRegister' onClick='replyRegister(" + this.rno + ", this);' value='추가'/></div><br/>"
					+ "<a href='#' onClick='reReplyList("+this.rno+");return false;'>>></a>"
					+ "<div class='reReply"+this.rno+"'></div></td></tr>";
			});
		
			$('[class=reReply'+rno+']').append(str);
			$('[class^=replyToggle]').hide();
			
		});
		
	}
	
	function replyToggle(rno) {
		console.log(this);
		$('[class=replyToggle'+rno+']').slideToggle(333);
	}

	$(document).ready(function() {
		
		
		
		
		$.getJSON("/board/reply/list/${boardVO.bno}", function(data) {
			var str = "";
			console.log(data.length);
			
			$(data).each(function() {
				str += "<tr><td>" + this.replyer + "</td>"
					+ "<td><input type='text' name='"+this.replyer+"' id='replytext"+this.rno+"' readOnly='readOnly' value='" + this.replytext + "'>";
					
				if("${login}"!="") {
					str += "<input type='button' name='btnReplyToggle' onClick='replyToggle(" + this.rno + ");' value='답글'/>"
						+ "<div class='replyToggle"+this.rno+"'><input type='text' class='replytext' name='"+this.replyer+"' />"
						+ "<input type='button' name='btnReplyRegister' onClick='replyRegister(" + this.rno + ", this);' value='추가'/></div>"
						+ "<br/><a href='#' onClick='reReplyList("+this.rno+");return false;'>>></a><div class='reReply"+this.rno+"'></div>";
					
					if('${login.uname}'==this.replyer) {
						str += "</td><td><input type='button' name='btnReplyModify' onClick='replyModify(" + this.rno + ");' value='수정'/></td></tr>";
					}
				}
			});
			
			$('#replyTable > tbody:last').append(str);
			$('[class^=replyToggle]').hide();

			if("${login}"!="") {
				$('input[name="${login.uname}"]').attr('readOnly', false);
			}
		});
		
		
		
		
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
		
		var bno = ${boardVO.bno};
		var template = Handlebars.compile($("#templateAttach").html());
		$.getJSON("/board/listAttach/"+bno, function(list) {
			$(list).each(function() {
				var fileInfo = getFileInfo(this);
				var html = template(fileInfo);
				$("#uploadedList").append(html);
			});
		});
		
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
					$("#uploadedList").append(html);
				}
			});
		});
		
		$("#uploadedList").on("click", ".mailbox-attachment-name", function(event) {
			var fileLink = $(this).attr("href");
			if(checkImageType(fileLink)) {
				event.preventDefault();
				var imgTag = $("#popup_img");
				imgTag.attr("src", fileLink);
				$(".popup").show('slow');
				imgTag.addClass("show");
			}
		});
		
		$("#uploadedList").on("click", ".remove", function() {
			removeFiles.push($(this).attr("data-src"));
			$(this).closest("li").remove();
			return false;
		});
	});
	
	$("#removeBtn").on("click", function() {
		var replyCnt = $("#replycntSmall").html().replace(/[^0-9]/g, "");
		if(replyCnt > 0) {
			alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
			return;
		}
		var files = [];
		$("#uploadedList li").each(function(index) {
			files.push($(this).attr("data-src"));
		});
		
		if(files.length > 0) {
			$.ajax({
				type: 'post',
				async: false,
				url: '/board/deleteAllFiles',
				data: files,
				success: function(response) {
					alert(response);
				}
			});
		}
		
	});
	
	
	
	$('#popup_img').on('click', function() {
		$(".popup").hide('slow');
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
	        						removeFiles2();
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
		console.log("submit");
		var str;
		$("#uploadedList .remove").each(function(index) {
			str += "<input type='hidden' name='files["+index+"]' value='" +$(this).attr("data-src")+ "'>";
		});
		$("#boardForm").append(str);
		document.boardForm.action = "/board/" + type + "/" + ${boardVO.bno};
		document.boardForm.submit();
	}
	
	function removeFiles2() {
		$.ajaxSettings.traditional = true;
		$.ajax({
			url:"/board/deleteFile",
			async: false,
			type:'POST',
			data: {removeFiles:removeFiles}
		});
	}
	
	
	
	
</script>

	<header></header>
		<div class="content">
			<div class="boardRead" align="center">
			<div class="popup back" style="display:none;"></div>
			<div id="popup_front" class="popup front" style="display:none;">
			<img id="popup_img"></div>
				<h2>글읽기</h2>
				<form name="boardForm" id="boardForm" method="post">
					<table>
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="writer" readOnly="readOnly"><input type="hidden" name="bpw"></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" readOnly="readOnly" ></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" cols="21" rows="10" readOnly="readOnly"></textarea></td>
						</tr>
						<tr>
							<td>첨부 파일</td>
							<td><div class="fileDrop"></div>
								<div class="fileBox">
								<ul id="uploadedList" >
								<script id="templateAttach" type="text/x-handlebars-template">
										<li data-src='{{fname}}'>
											<span class="mailbox-attachment-icon has-img"><img src="{{imgSrc}}" alt="Attachment"></span>
											<div class="mailbox-attachment-info">
												<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
												<button data-src="{{fname}}" class="remove">X</button>
											</div>
										</li>
								</script>
								</ul>
								</div>
							</td>
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
				<p/>
				<h3>댓글</h3>
					<table id="replyTable">
						<tr>
							<th style="width:60px">아이디</th>
							<th style="width:222px">내용</th>
							<c:if test="${!empty login}">
							<th>수정</th>
							</c:if>
						</tr>
						<tbody></tbody>
					</table>
					<c:if test="${!empty login}">
						댓글 <input type="text" class="replytext" />
						<input type="button" onClick="replyRegister(0, this);" value="추가" />
					</c:if>
				
			</div>
		</div>
		
		<div id="dialogPwMat" title="비밀번호 확인" align="center">
			<label style="font-size:17px;">비밀번호&nbsp;&nbsp;<input type="password" name="matbpw" size="15"/></label>
		</div>
		

<%@ include file="/WEB-INF/views/include/footer.jsp"%>