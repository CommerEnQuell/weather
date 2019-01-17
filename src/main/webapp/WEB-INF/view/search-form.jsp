<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Zoek stad</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>
	<body>
		<h2>
			<table width="100%">
				<tr>
					<td width="84%"><h2>Geef stad en eventueel landafkorting in</h2></td>
					<td width="16%" align="right"><h2>${user}</h2></td>
				</tr>
			</table>
		</h2>

    <form:form action="processForm" modelAttribute="city" method="GET">
		<table>
		    <tr><td><label>Plaatsnaam</label></td><td><form:input path="cityName" /></td></tr>
		    <tr><td><label>Land</label><td><form:input path="countryName" /></td></tr>
		    <tr>
		    	<td valign="top"><label>Eenheden</label></td>
		    	<td><form:select path="units">
		    			<form:option value="metric" label="Metrisch" />
		    			<form:option value="" label="Standaard" />
		    			<form:option value="imperial" label="Imperial" />
		    		</form:select>
<!-- 
		    		<table>
		    			<tr><td width="4%"><form:radiobutton label="Standaard" path="units" value="" /></td></tr>
		   	 			<tr><td width="4%"><form:radiobutton label="Metrisch" path="units" value="metric" /></td></tr>
		   	 			<tr><td width="4%"><form:radiobutton label="Imperial" path="units" value="imperial" /></td></tr>
		   	 		</table>
 -->
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