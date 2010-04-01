<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="styles/search/result.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h3><fmt:message key="result" /></h3>
	<div id="documents">
		<c:forEach items="${documents}" var="document">
			<div class="document">
				<h4>
					<c:choose>
						<c:when test="${document.source eq null}">
							${document.title}
						</c:when>
						<c:otherwise>
							<a href="${document.source}">${document.title}</a>
						</c:otherwise>
					</c:choose>
				</h4>
				<div class="properties">
					<c:forEach items="${document.properties}" var="property">
						<h5><fmt:message key="${property.key}" />: ${property.value}</h5>
					</c:forEach>
				</div>
				<div class="description">${document.description}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>