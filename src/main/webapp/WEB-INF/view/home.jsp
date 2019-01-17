<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Welkom bij het weer</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	
	<body>
		<p>
		<table cellspacing=8>
			<tr>
				<td width=76%><header>Opvraagmodule weerrapporten</header></td>
				<td width=24% align="right"><header>
					<c:choose>
						<c:when test = "${user != null}">
							<b>${user}</b>
						</c:when>
					
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/showLoginPage">Login</a>
						</c:otherwise>
					</c:choose>
				</header></td>
			</tr>
		</table>
		<c:if test="${user != null}">
			Logged in as <b>${user}</b>
		</c:if>
		<p>
		<a href="${pageContext.request.contextPath}/api/search">Zoek plaats</a>
		<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
		<c:if test="${user != null}">
			<p>
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="logout" />
			</form:form>
		</c:if>
	</body>
</html>