<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="term" id="${term}">
		<button term="${term}" class="small"><fmt:message key="delete"/></button>
		<div class="description">${term}</div>
		<div class="score small">
			<div term="${term}"></div>
			<input type="text" term="${term}" value="${weight}"/>
		</div>
	</div>
</body>
</html>