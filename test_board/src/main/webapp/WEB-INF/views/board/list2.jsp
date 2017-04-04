<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		if ("${resultLeave}" == "1") {
			alert("회원 탈퇴 되었습니다.");
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
<%@ include file="/WEB-INF/views/include/header.jsp"%>
		<div class="content">
			<div class="boardList" align="center">
				<form name="removeForm" method="post" action="/board/remove">
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
							<td>${pageVO.totalCount-(((pageVO.cri.page-1)*pageVO.cri.perPageNum)+status.index)}</td>
							<td><a href="/board/read/${list.bno}" onClick='popup(this.href, "글읽기");return false;' target="_blank">${list.title}</a></td>
							<td>${list.writer}</td>
							<td><fmt:formatDate value="${list.regdate}" pattern="yyyy/MM/dd HH:mm" /></td>
							<td>${list.viewcnt}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="paging">
					<c:if test="${pageVO.prev}">
						<a href="list?page=1">[처음]</a>
						<a href="list?page=${pageVO.startPage-1}">&laquo;</a>
					</c:if>
					<c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" var="idx">
						<a href="list?page=${idx}">[${idx}]</a>
					</c:forEach>
					<c:if test="${pageVO.next && pageVO.endPage > 0 }">
						<a href="list?page=${pageVO.endPage+1 }">&raquo;</a>
						<a href="list?page=${pageVO.totalPage}">[끝]</a>
					</c:if>
				</div><br/>
				<c:if test="${login.urole > 1}">
				<input class="button btn1" type="submit" name="remove" value="삭제" />
				<input class="button btn1" type="button" name="removeAll" value="전체삭제" onClick='location.href="/board/removeAll"' />
				</c:if>
				<div class="button btn1" onClick='popup("/board/register", "글쓰기")'>글쓰기</div>
				</form>
			</div>
		</div>
		
<jsp:include page="/WEB-INF/views/include/footer.jsp" flush="false"></jsp:include>