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
	<div class="harvester" id="${harvester.id}">
		<button hId="${harvester.id}"><fmt:message key="delete" /></button>
		<div>${harvester.name}</div>
		<input type="text" value="${harvester.endPoint}" name="endpoint"/>
		<select>
			<c:forEach items="${types}" var="type">
				<c:choose>
					<c:when test="${harvester.harvesterType eq type}">
						<option value="${type}" selected="selected">${type}</option>
					</c:when>
					<c:otherwise>
						<option value="${type}">${type}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>		
</body>
</html>