<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Zoek stad</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>Geef stad en eventueel landafkorting in</h2>

    <form:form action="processForm" modelAttribute="city" method="GET">
		<table>
		    <tr><td><label>Plaatsnaam</label></td><td><form:input path="cityName" /></td></tr>
		    <tr><td><label>Land</label><td><form:input path="countryName" /></td></tr>
		    <tr>
		    	<td valign="top"><label>Eenheden</label></td>
		    	<td>
		    		<table>
		    			<tr><td width="4%"><form:radiobutton path="units" value="" /></td><td>Standaard</td></tr>
		   	 			<tr><td width="4%"><form:radiobutton path="units" value="metric" /></td><td>Metrisch</td></tr>
		   	 			<tr><td width="4%"><form:radiobutton path="units" value="imperial" /></td><td>Imperial</td></tr>
		   	 		</table>
		    	</td>
		    </tr>
	    </table>

	    <br><br>
	
		<input type="submit" value="Zoek" />
    </form:form>

	<p>		
		<a href="${pageContext.request.contextPath}">Homepage</a>
	<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
	</body>
</html>