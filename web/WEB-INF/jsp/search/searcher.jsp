<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="styles/search/searcher.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="scripts/jquery.js" ></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#search").click(
				function search() {
					var q = $("#q").val();
					var t = $("#t").val();
					var url = $("form#searchForm").attr("action");
					var data = { q: q, t: t };
					$.get(url, data, showResults);
				}
			);
	    });
	</script>
</head>
<body>
	<h3><fmt:message key="search" /></h3>
	<form action="search" id="searchForm">
		<div>
			<label for="t"><fmt:message key="type"/></label>
			<select name="t" id="t">
				<option value="br.recomende.model.document.Document"><fmt:message key="any"/></option>
				<c:forEach items="${types}" var="type">
					<option value="${type.canonicalName}"><fmt:message key="${type.simpleName}"/></option>
				</c:forEach>
			</select>
		</div>
		<ul>
			<li>
				<input type="text" name="q" id="q" />
			</li>
			<li>
				<button id="search"><fmt:message key="toSearch"/></button>
			</li>
		</ul>
	</form>
</body>
</html>