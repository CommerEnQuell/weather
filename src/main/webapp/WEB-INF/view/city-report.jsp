<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Weerrapport voor ${report.name}, ${report.sys.country}</title>
		<link type="text/css"
		      rel="stylesheet"
		      href="${pageContext.request.contextPath}/resources/css/report.css" />
	</head>

	<body>
	<table>
		<tr>
			<td width="84%"><h2>Weerrapport voor ${report.name}, ${report.sys.country}, geldig voor ${report.currentTime}</h2></td>
			<td width="16%" align="right"><h2>${user}</h2></td>
		</tr>
	</table>
		<p>
		Co&ouml;rdinaten: <outvar>${report.coord.latitude}</outvar>, <outvar>${report.coord.longitude}</outvar>
		<p>
		<table border="0" cellpadding="4" cellspacing="4">
			<tr>
				<td>Zon op: <outvar>${report.sys.sunriseTime}</outvar></td>
				<td>Zon onder: <outvar>${report.sys.sunsetTime}</outvar></td>
			</tr>
		</table>
		
		
		<h3>Algemeen</h3>
		<table border="0" cellpadding="4" cellspacing="4">
			<tr><td>Actuele temperatuur (<invar>
			<c:choose>
				<c:when test = "${report.units == 1}">
					&deg;C
				</c:when>
				<c:when test = "${report.units == 2}">
					&deg;F
				</c:when>
				<c:otherwise>K</c:otherwise>
			</c:choose>
			</invar>)</td><td align="right"><outvar>${report.main.temp}</outvar></td></tr>
			<tr><td>Luchtdruk (hPa)</td><td align="right"><outvar>${report.main.pressure}</outvar></td></tr>
			<tr><td>Relatieve vochtigheid (%)</td><td align="right"><outvar>${report.main.humidity}</outvar></td></tr>
			<tr><td>Minimum temperatuur (<invar>
			<c:choose>
				<c:when test = "${report.units == 1}">
					&deg;C
				</c:when>
				<c:when test = "${report.units == 2}">
					&deg;F
				</c:when>
				<c:otherwise>K</c:otherwise>
			</c:choose>
			</invar>)</td><td align="right"><outvar>${report.main.temp_min}</outvar></td></tr>
			<tr><td>Maximum temperatuur (<invar>
						<c:choose>
				<c:when test = "${report.units == 1}">
					&deg;C
				</c:when>
				<c:when test = "${report.units == 2}">
					&deg;F
				</c:when>
				<c:otherwise>K</c:otherwise>
			</c:choose>
			</invar>)</td><td align="right"><outvar>${report.main.temp_max}</outvar></td></tr>
		</table>
		
		<h3>Weer</h3>
<!-- 
		<table border="1" cellpadding="4" cellspacing="4">
			<c:forEach var="tempWeather" items="${report.weather}">
				<tr>
					<td>
						<img src="//openweathermap.org/img/w/${tempWeather.icon}.png" alt="${tempWeather.main}" />
					</td>
					<td>
						${tempWeather.description}
					</td>
				<tr>
			</c:forEach>
		</table>
		<p>
-->
		<table border="0" cellpadding="4" cellspacing="4">
			<c:forEach var="tempWeather" items="${report.weather}">
				<tr>
					<td>
						<img src="//openweathermap.org/img/w/${tempWeather.icon}.png" alt="${tempWeather.main}" />
					</td>
					<td>
						<outvar>${tempWeather.description}</outvar>
					</td>
				</tr>
			</c:forEach>
			<tr><td>Zicht</td><td><outvar>${report.visibility}</outvar> m</td></tr>
			<tr><td>Windsnelheid</td><td><outvar>${report.wind.speed}</outvar>
					<c:choose>
						<c:when test = "${report.units == 2}">
							<invar>mph</invar>
						</c:when>
						<c:otherwise><invar>m/s</invar></c:otherwise>
					</c:choose>
				</td></tr>
			<tr><td>Windrichting</td><td><outvar>${report.wind.deg}</outvar>&deg; (<outvar>${report.wind.direction}</outvar>)</td></tr>
			<tr><td>Bewolkingsgraad</td><td><outvar>${report.clouds.all}</outvar>%</td></tr>
			<c:if test="${not empty report.rain}">
				<c:if test="${not empty report.rain.hours1}">
					<tr><td>Regen laatste uur</td><td><outvar>${report.rain.hours1}</outvar> mm</td></tr>
				</c:if>
				<c:if test="${not empty report.rain.hours3}">
					<tr><td>Regen laatste 3 uur</td><td><outvar>${report.rain.hours3}</outvar> mm</td></tr>
				</c:if>
			</c:if>
			<c:if test="${not empty report.snow}">
				<c:if test="${not empty report.snow.hours1}">
					<tr><td>Sneeuw laatste uur</td><td><outvar>${report.snow.hours1}</outvar> cm</td></tr>
				</c:if>
				<c:if test="${not empty report.snow.hours3}">
					<tr><td>Sneeuw laatste 3 uur</td><td><outvar>${report.snow.hours3}</outvar> cm</td></tr>
				</c:if>
			</c:if>
		</table>
		<p>
		<a href="${pageContext.request.contextPath}/api/search">Ander weerrapport</a>
		<p>
		<a href="${pageContext.request.contextPath}/api/cities-list">Lijst van plaatsen</a>
		<p>
		<security:authorize access="hasRole('ADMIN')">
			<a href="${pageContext.request.contextPath}/maint/city?cityId=${report.id}&cityName=${report.name}">Wijzig plaatsnaam</a>
			<p>
			<a href="${pageContext.request.contextPath}/maint/requests?cityId=${report.id}">Opvragingen voor ${report.name}</a>
		</security:authorize>
		<hr>
		<i>Dit weerrapport is afkomstig van <a href="http://www.openweathermap.org">openweathermap.org</a></i>

	</body>
</html>