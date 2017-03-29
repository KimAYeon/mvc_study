<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<script>
	$(document).ready(function() {
		if ("${resultRemove}" != "" && "${resultRemove}" != "0") {
			alert("${resultRemove} 개의 글이 삭제되었습니다.");
			myOpener();
		}
		if ("${resultRemove}" == "0") {
			alert("글이 삭제되지 않았습니다.");
			myOpener();
		}
		$("#checkall").click(function(){
        	if($("#checkall").prop("checked")){
          	  $("input[name=check]").prop("checked",true);
       		} else {
       	     $("input[name=check]").prop("checked",false);
      	    }
    	});
	});
</script>

<title></title>
</head>
<body>
	<div class="wrapper">
		<div class="content">
			<div class="boardList" align="center">
				<form method="post" action="/board/remove">
				<h1>게시판 목록</h1>
				<table>
					<tr>
						<th><input type="checkbox" id="checkall" /></th>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>등록일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="list" items="${list}" varStatus="status" end="${fn:length(list)}" >
						<tr>
							<td><input type="checkbox" name="check" value="${list.bno}" /></td>
							<td>${(status.end)-(status.index)}</td>
							<td><a href="/board/read/${list.bno}" onClick='popup(this.href, "글읽기");return false;' target="_blank">${list.title}</a></td>
							<td>${list.writer}</td>
							<td><fmt:formatDate value="${list.regdate}" pattern="yyyy/MM/dd HH:mm" /></td>
							<td>${list.viewcnt}</td>
						</tr>
					</c:forEach>
				</table>
				<input class="button btn1" type="submit" name="remove" value="삭제" />
				<div class="button btn1" onClick='popup("/board/register", "글쓰기")'>글쓰기</div>
				</form>
			</div>
		</div>
		</div>
</body>
</html>
		