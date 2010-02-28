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
	<div class="type" id="${type.type}">
		<button hId="${type.type}"><fmt:message key="delete" /></button>
		<div>${type.type}</div>
		<select>
			<c:forEach items="${classes}" var="clazz">
				<c:choose>
					<c:when test="${type.harvesterClass eq clazz}">
						<option value="${clazz.name}" selected="selected">${clazz}</option>
					</c:when>
					<c:otherwise>
						<option value="${clazz.name}">${clazz}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>		
</body>
</html>