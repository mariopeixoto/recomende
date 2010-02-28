<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="styles/menu/admin.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript">
		function mapAdminMenu() {
			$("#harvesterbutton").click(
				function harvesterButton() {
					$.get("harvester", showContent);
				}
			);
			$("#harvestertypebutton").click(
				function harvesterTypeButton() {
					$.get("harvester/type", showContent);
				}
			);
		}
	</script>
</head>
<body>
	<div id="adminmenu">
		<p id="caption">
			<fmt:message key="manager" />
		</p>
		<p>
			<button id="harvesterbutton"><fmt:message key="harvesters" /></button>
		</p>
		<p>
			<button id="harvestertypebutton"><fmt:message key="harvester.types" /></button>
		</p>
	</div>
</body>
</html>