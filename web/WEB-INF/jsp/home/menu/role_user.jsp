<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="styles/menu/user.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript">
		$("#homebutton").click(
			function homeButton() {
				$.get("recommend", showContent);
			}
		);
		$("#searchbutton").click(
				function searchButton() {
					$.get("searcher", showContent);
				}
			);
		$("#profilebutton").click(
			function profileButton() {
				$.get("profile", showContent);
			}
		);
		$("#signoutbutton").click(
			function signoutButton() {
				location.href = "./signout";
			}
		);
	</script>
</head>
<body>
	<div id="usermenu">
		<p id="caption">
			<fmt:message key="menu" />
		</p>
		<p>
			<button id="homebutton"><fmt:message key="recommend" /></button>
		</p>
		<p>
			<button id="profilebutton"><fmt:message key="profile" /></button>
		</p>
		<p>
			<button id="signoutbutton"><fmt:message key="signout" /></button>
		</p>
	</div>
</body>
</html>