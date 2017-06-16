<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
</head>
<body ng-controller="moniteringCtrl"  class="pad">



	<c:url value="/logout" var="logoutUrl" />

 

	<!-- csrt for log out-->

	<form action="${logoutUrl}" method="post" id="formLogOut">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<form action="/admin" method="get" id="formAdmin">
	</form>

 

 	<script>

		function formLogOut() {
			document.getElementById("formLogOut").submit();
		}
		
		function formAdmin() {
			document.getElementById("formAdmin").submit();
		}

	</script>

 

 	<!------------------------     navigation   ------------------------>

	<h1>USER PAGE</h1>

         <form class="navbar-form navbar-right" >

       	     <span style="color: gray;" ><h3> ${user.username} 님 반갑습니다. <br/>
			 ${cookie.SPRING_SECURITY_REMEMBER_ME_COOKIE}<br/>
			 ${cookie}<br/>
             <a href = "javascript:formLogOut()"> 로그아웃 </a> <br/>
             <a href = "javascript:formAdmin()"> ADMIN PAGE </a> </h3></span>

   	  </form>
</body>
</html>
