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
<style>
table {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background: #ce4869 ;
}
table td {
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
</style>
<script type="text/javascript">
	var update = null;
	$(document).ready(function(){
  		$("#checkall").click(function(){
        	if($("#checkall").prop("checked")){
          		$("input[name=check]").prop("checked",true);
       		} else {
       	    	$("input[name=check]").prop("checked",false);
      	    }
    	});
  		
  		$('#insert').click(function(){
  			$('#form').attr("action", "/customer/output"); 
  			$('#form').submit();
  		});
  		$('input[name=update][type=button]').click(function(){
  			$('#form').attr("action", "/customer/updateselec"); 
  			$('input[name=customNo]').val($(this).attr('id'));
  			$('#form').submit();
  		});
  		$('#delete').click(function(){
  			$('#form').attr("action", "/customer/delete"); 
  			$('#form').submit();
  		});
  		
  		if("${message}"!="") {
  			alert("${message}");
  		}
  		
	});
</script>

<body>
	<center>
	<form id="form" method="POST">
	<h2>회원 가입</h2>
	이름 : <input type="text" name="name"/><p/>
	성별 : 남<input type="radio" name="gender" value="남"/> 
		   여<input type="radio" name="gender" value="여"/><p/>
	나이 : <input type="number" name="age"/><p/>
	<input type="button" id="insert" value="가입"/>

	<h2>회원 목록</h2>
	<table>
		<tr>
			<th><input type="checkbox" id="checkall"/></th>
			<th>번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>나이</th>
			<th>수정</th>
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
			<td><input type="checkbox" name="check" value="${list.customNo}"/></td>
			<td>${list.customNo}</td>
			<td>${list.name}</td>
			<td>${list.gender}</td>
			<td>${list.age}</td>
			<td><input type="hidden" name="customNo" value=""/>
			    <input type="button" name="update" id="${list.customNo}" value="수정"/></td>
		</tr>
		</c:forEach>
	</table>
	<input type="button" id="delete" value="삭제"/>
	</form>
	</center>
</body>
</html>