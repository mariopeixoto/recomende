<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>RecoMEnde - Sign in</title>
	<link href="styles/sign/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="JavaScript" src="scripts/jquery-1.4.2.js" ></script>
</head>
<body>
	<div id="logo">
		<img src="images/logo-medium.png"/>
	</div>
	<div id="error">
		<c:if test="${error}">
			<strong><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}"/></strong>
		</c:if>
	</div>
	<form id="signin" action="j_spring_security_check" method="post">
		<ul>
			<li>
				<label for="username">
					<fmt:message key="username" />:
				</label>
				<input type="text" name="j_username" id="username" size="20"/>
			</li>
			<li>
				<label for="password">
					<fmt:message key="password" />:
				</label>
				<input type="password" name="j_password" id="password" size="20"/>
			</li>
			<li>
				<input id="submit" type="submit" value="<fmt:message key="signin" />"/>
			</li>
		</ul>
	</form>
</body>
</html>