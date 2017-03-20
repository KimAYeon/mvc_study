<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean class="com.info.jsp.PersonalInfo" id="myinfo2" scope="request"/>

    <jsp:setProperty name="myinfo2" property="name" value="KimAyeon"/>
	<jsp:setProperty name="myinfo2" property="gender" value="여"/>
	<jsp:setProperty name="myinfo2" property="age" value="26"/>
	
	<jsp:forward page="CustomerInfoViewer.jsp"/>
	
	