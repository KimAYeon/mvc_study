<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<span style="color: gray;"><h3>에러가 발생했습니다.500</h3></span><br/>
			<%= exception.getClass().getName() %><br/>
			<%= exception.getStackTrace().toString() %><br/>
			${msg}
		</div>
	</div>
</body>
</html>