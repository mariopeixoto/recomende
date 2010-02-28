<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>RecoMEnde - <fmt:message key="signin" /></title>
	<link href="styles/auth/signin.css" rel="stylesheet" type="text/css">
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
	<div id="out">
		<form action="j_spring_security_check" method="post">
			<ul>
				<li>
					<label for="username">
						<fmt:message key="username" />:
					</label>
					<input type="text" name="j_username" id="username"/>
				</li>
				<li>
					<label for="password">
						<fmt:message key="password" />:
					</label>
					<input type="password" name="j_password" id="password"/>
				</li>
				<li>
					<button id="submit"><fmt:message key="signin" /></button>
				</li>
			</ul>
			<a id="register" href="register"><fmt:message key="register" /></a>
		</form>
	</div>
</body>
</html>