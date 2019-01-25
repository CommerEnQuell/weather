<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Wijzig landomschrijving</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>
			<table width="100%">
				<tr>
					<td width="84%"><h2>Geef landomschrijving in</h2></td>
					<td width="16%" align="right"><h2>${user}</h2></td>
				</tr>
			</table>
		</h2>

    <form:form action="processCountry" modelAttribute="country" method="GET">
    	<form:input type="hidden" path="countryCd" />
		<table cellspacing=4>
		    <tr><td><label>Landafkorting</label></td><td><b>${country.countryCd}</b></td></tr>
		    <tr><td><label>Omschrijving</label><td><form:input path="countryName" /></td></tr>
	    </table>

	    <br><br>

		<sec:authorize access="hasRole('ADMIN')">	
			<input type="submit" value="Wijzigen" />
		</sec:authorize>
	
    </form:form>

	<p>		
		<a href="${pageContext.request.contextPath}">Homepage</a>
	<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
	</body>
</html>