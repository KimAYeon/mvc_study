<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css?ver=11" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/jquery-1.12.0.js"></script>
<script type="text/javascript" src="/css/jquery.paging.js"></script>
<script>
	$(document).ready(function() {
	})
	function popup(url, title) { 
		var windowW = 450; // 창의 가로 길이
		var windowH = 650; // 창의 세로 길이
		var left = ((window.screen.width - windowW) / 2);
		var top = ((window.screen.height - windowH) / 2);
		window.open(url, title, "top=" + top + ", left="
				+ left + ", height=" + windowH + ", width=" + windowW +', scrollbars=yes');
	}
	function myOpener() {
		opener.parent.location.reload();
		window.close();
	}
</script>
