<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('[name="writer"]').val("${boardVO.writer}");
		$('[name="title"]').val("${boardVO.title}");
		$('[name="content"]').val("${boardVO.content}");
		$('[name="bpw"]').val("${boardVO.bpw}");

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
	function formSubmit(type) {
		document.boardForm.action = "/board/" + type + "/" + $
		{
			boardVO.bno
		}
		;
		document.boardForm.submit();
	}
</script>

<title></title>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<div class="boardRead" align="center">
				<h2>글읽기</h2>
				<form name="boardForm" method="post">
					<table>
						<tr>
							<td>글쓴이</td>
							<td><input type="text" name="writer"></td>
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
							<td>비밀번호</td>
							<td><input type="password" name="bpw"></td>
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
					<input type="button" class="button btn1" name="modify" value="수정" onClick='formSubmit("modify")' /> 
					<input type="button" class="button btn1" name="remove" value="삭제" onClick='formSubmit("remove")' /> 
					<input type="button" class="button btn1" name="cancel" value="취소" onClick="window.close()" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>