<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>Lijst van eerder opgevraagde plaatsen</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>
			<table width="100%">
				<tr>
					<td width="84%"><h2>Lijst van plaatsen</h2></td>
					<td width="16%" align="right"><h2>${user}</h2></td>
				</tr>
			</table>
		</h2>
		<p>Bij het opvragen van een plaats uit deze lijst worden de laatst gehanteerde eenheden gehanteerd.<br>
		Als er nog geen eenheden zijn opgegeven worden de eenheden als <invar>metrisch</invar> weergegeven.
		<p><hr>
		<p>
		<table>
			<tr>
				<th valign="top">Naam</th>
				<th align="center" valign="top">Land</th>
				<th align="right" valign="top">Laatste<br>rapport</th>
				<th align="right" valign="top">Queries</th>
			</tr>
			<c:forEach var="aCityReport" items="${cityReports}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/api/processId?cityId=${aCityReport.cityId}">${aCityReport.city.cityNameNL}</a></td>
					<td align="left"><a href="${pageContext.request.contextPath}/maint/country?countryCd=${aCityReport.country.countryCd}">${aCityReport.country.countryName}</a></td>
					<td align="right">${aCityReport.lastReport}</td>
					<td align="right">${aCityReport.queryCount}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<p>		
		<a href="${pageContext.request.contextPath}">Homepage</a>
		<p>
		<a href="${pageContext.request.contextPath}/api/search">Zoek andere plaats</a>
</html>