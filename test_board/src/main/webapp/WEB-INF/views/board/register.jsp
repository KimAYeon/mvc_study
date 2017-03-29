<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		if("${result}"=="1") {
			alert("새 글이 작성되었습니다.");
			myOpener();
		}
		if("${result}"=="0") {
			alert("새 글이 작성되지 않았습니다.");
		}
	});
</script>
<title></title>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<div class="boardRegister" align="center">
				<h2>글쓰기</h2>
				<form name="register" method="post" action="/board/register">
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
					</table>
					<input type="submit" class="button btn1" name="register" value="저장">
					<input type="button" class="button btn1" name="cancel" value="취소"
						onClick="window.close()">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
		