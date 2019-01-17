<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Wijzig plaatsnaam in Nederlands</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>
			<table width="100%">
				<tr>
					<td width="84%"><h2>Geef plaatsnaam in</h2></td>
					<td width="16%" align="right"><h2>${user}</h2></td>
				</tr>
			</table>
		</h2>

    <form:form action="processCity" modelAttribute="city" method="GET">
    	<form:input type="hidden" path="cityId" />
		<table cellspacing=4>
		    <tr><td><label>Plaatsnaam</label></td><td><b>${city.cityReport.cityName}</b></td></tr>
 		    <tr><td><label>Land</label></td><td><b>${city.cityReport.country.countryName}</b></td></tr>
		    <tr><td><label>Plaatsnaam NL</label><td><form:input path="cityNameNL" /></td></tr>
	    </table>

	    <br><br>
	
		<input type="submit" value="Wijzigen" />
    </form:form>

	<p>		
		<a href="${pageContext.request.contextPath}">Homepage</a>
	<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
	</body>
</html>