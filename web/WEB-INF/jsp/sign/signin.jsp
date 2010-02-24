<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RecoMEnde - Sign in</title>
	<link href="styles/sign/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="JavaScript" src="scripts/jquery-1.4.2.js" ></script>
</head>
<body>
	<img id="logo" src="images/logo-medium.png" align="center"/>
	<form id="signin" action="j_spring_security_check" method="post">
		<ul>
			<li>
				<label for="username">Username:</label>
				<input type="text" name="j_username" id="username" size="20"/>
			</li>
			<li>
				<label for="password">Password:</label>
				<input type="text" name="j_password" id="password" size="20"/>
			</li>
			<li>
				<input id="submit" type="submit" value="Sign in"/>
			</li>
		</ul>
	</form>
</body>
</html>