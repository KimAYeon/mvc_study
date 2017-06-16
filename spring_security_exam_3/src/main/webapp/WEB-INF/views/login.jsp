<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="/WEB-INF/views/errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>LogIn Page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript"> 
		function doLogin() {
			if(frm.j_username.value == "") {
				alert("아이디를 입력해주세요.");
				return;
			}
			if(frm.j_password.value == "") {
				alert("패스워드를 입력해주세요.");
				return;
			}
			frm.submit();
		}
	</script>	
</head>
<body>
<!--------------------------------- 탑 메뉴  ------------------------------------>
<br>
<br>
<div class="top_menu">
	<div class="top_banner_left">&nbsp;&nbsp;<Font style="font-size:30px;">Spring Security</B></Font></div>
	&nbsp;&nbsp;&nbsp;&nbsp;
</div>

<!--------------------------------- 로그인 ------------------------------------>

<br>
<br>
<br>
<br>
<br>
	<section class="loginform cf">
		<form name="frm" action="/check" method="post">
			<table>
				<tr>
					<td>
						<ul>
							<li>
								<label for="username">ID</label>
								<input id = "username" type="username" name="username" placeholder="ID" required>
							</li>
							<li>
								<label for="password">Password</label>
								<input id = "password" type="password" name="password" placeholder="PASSWORD" required></li>
							<li>
								<input type="submit" value="로그인" onclick="doLogin()"/>
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<input id="remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>Remeber me
					</td>
				</tr>
	 		</table>
	 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
	</section>
		<c:if test="${not empty param.error}">
			<div class="error"><h3 style="color: red;" >${param.error}</h3></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
</body>
</html>
