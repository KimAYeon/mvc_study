<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[name="name"]').val("${person.name}");
		$('input:radio[id="${person.gender}"]').attr('checked', true);
		$('input[name="age"]').val("${person.age}");
	});
</script>

<body>
	<form id="form" action="/customer/update" method="POST">
		<h2>회원 수정</h2>
		번호 : <input type="hidden" name="customNo" value="${person.customNo}" />${person.customNo }<p/>
		이름 : <input type="text" name="name" /><p/>
		성별 : 남<input type="radio" name="gender" id="남" value="남" /> 
		       여<input type="radio" name="gender" id="여" value="여" /><p/>
		나이 : <input type="number" name="age" /><p/>
		<input type="submit" name="update" value="수정" />
		<input type="button" name="cancle" value="취소" onclick="history.back()();"/>
	</form>
</body>
</html>