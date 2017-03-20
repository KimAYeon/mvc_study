<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean class="com.info.jsp.PersonalInfo" id="myinfo2" scope="request"/>
	
	이름 : <jsp:getProperty property="name" name="myinfo2"/><br/>
	성별 : <jsp:getProperty property="gender" name="myinfo2"/><br/>
	나이 : <jsp:getProperty property="age" name="myinfo2"/><br/>

</body>
</html>