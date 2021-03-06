<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>RecoMEnde - <fmt:message key="home" /></title>
	<link href="styles/home/home.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript">
		function configureAjaxLoading() {
			$('div#box').hide();
			
			$('div#box').ajaxStart(function() {
				hideContent();
				$(this).show();
			});
			
			$('div#box').ajaxComplete(function() {
				$(this).hide();
			});
		}

		function hideContent() {
			$("div#contentHolder").html('');
			$("div#contents").removeClass("bordered");
		}
		
		function showContent(data) {
			$("div#contentHolder").html(data);
			$("div#contents").addClass("bordered");
		}

		function loadMenu(data) {
			$("div#menu").append(data);
		}

		function loadRecommendations(msg) {
			var answer = confirm(msg);
			if(answer) {
				$.get("recommend", showContent);
			}
		}
		
		$(document).ready(function(){
			configureAjaxLoading();
			
			<c:forEach items="${roles}" var="role">
				$.ajax({
					url: 'home/menu/${role}',
					async: false,
					success: loadMenu
				});
			</c:forEach>
	    });
	</script>
</head>
<body onload="loadRecommendations('<fmt:message key="get.recommendations" />')">
	<div id="holder">
		<div id="menu">
			<img src="images/logo-small.png"/>
		</div>
		<div id="contents">
			<div id="box">
				<img src="images/loading.gif" />
			</div>
			<div id="contentHolder">
			</div>
		</div>
	</div>
</body>
</html>