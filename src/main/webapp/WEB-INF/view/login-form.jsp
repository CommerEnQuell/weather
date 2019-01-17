<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Login for the admin zone</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
		<style type="text/css">
			.failed {
				font-family: Century Schoolbook;
				color: white;
				background: #6a0010;
			}
		</style>
		<style type="text/css">
			.gone {
				font-family: Century Schoolbook;
				color: white;
				background: #00a900;
			}
		</style>
	</head>

	<body>
		<h2>Login for the admin zone</h2>
		
		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			<c:if test="${param.error != null}">
				<div class="failed">Login mislukt: Gebruikersnaam en/of Wachtwoord onjuist</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="gone">Wellicht tot een volgende keer ...</div>
			</c:if>
			<p>
			<table>
				<tr><td>Username:</td><td><input type="text" name="username" /></td></tr>
				<tr><td>Password:</td><td><input type="password" name="password" /></td></tr>
			</table>
			<p>
			<input type="submit" value="Login" />
		</form:form>
		

	</body>
</html>