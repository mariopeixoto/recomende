<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>RecoMEnde - <fmt:message key="register" /></title>
	<link href="styles/auth/register.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="logo">
		<img src="images/logo-small-name.png"/>
	</div>
	<form:form modelAttribute="user" method="post" action="register" enctype="multipart/form-data">
		<fieldset>
			<legend>
				<fmt:message key="register" />
			</legend>
			<p>
				<form:label for="email" path="email" cssErrorClass="error"><fmt:message key="email" />:</form:label>
				<form:input path="email"/>
				<form:errors path="email"/>
			</p>
			<p>
				<form:label for="username" path="username" cssErrorClass="error"><fmt:message key="username" />:</form:label>
				<form:input path="username"/>
				<form:errors path="username"/>
			</p>
			<p>
				<form:label for="password" path="password" cssErrorClass="error"><fmt:message key="password"/>:</form:label>
				<form:password path="password"/>
				<form:errors path="password"/>
			</p>
			<p>
				<form:label for="firstName" path="firstName" cssErrorClass="error"><fmt:message key="first.name"/>:</form:label>
				<form:input path="firstName" />
				<form:errors path="firstName"/>
			</p>
			<p>
				<form:label for="lastName" path="lastName" cssErrorClass="error"><fmt:message key="last.name"/>:</form:label>
				<form:input path="lastName"/>
				<form:errors path="lastName"/>
			</p>
			<p>
				<form:label for="citationName" path="citationName" cssErrorClass="error"><fmt:message key="citation.name"/>:</form:label>
				<form:input path="citationName"/>
				<form:errors path="citationName"/>
			</p>
			<p>
				<label for="curriculum">Curriculum Lattes (XML):</label>
				<input type="file" name="curriculum" id="curriculum"/>
			</p>
			<p>
				<button><fmt:message key="register"/></button>
			</p>
		</fieldset>
	</form:form>
</body>
</html>