<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Opvragingen voor ${cityName}</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>Lijst van opvragingen voor ${cityName}, ${countryName}</h2>
		<p>De tijden worden in de <invar>Nederlandse</invar> tijdzone weergegeven.
		<p><hr>
		<p>
		<table>
			<tr>
				<th align="right" valign="top">Seq#</th>
				<th align="right" valign="top">Tijdstip<br>weerrapport</th>
				<th align="right" valign="top">Tijdstip<br>opgevraagd</th>
			</tr>
			<c:forEach var="aLogging" items="${loggings}">
				<tr>
					<td align="right">${aLogging.logId}</a></td>
					<td align="right">${aLogging.reportTime}</td>
					<td align="right">${aLogging.requestTime}</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<p>		
		<a href="${pageContext.request.contextPath}">Homepage</a>
		<p>
		<a href="${pageContext.request.contextPath}/api/search">Zoek andere plaats</a>
		<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
</html>