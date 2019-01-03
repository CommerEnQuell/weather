package nl.commerquell.weather.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import nl.commerquell.weather.db.entity.CityReport;
import nl.commerquell.weather.entity.City;
import nl.commerquell.weather.entity.WeatherReport;
import nl.commerquell.weather.service.CityReportService;

@Controller
@RequestMapping("/api")
public class WeatherReportController {
	private String defUnits = "metric";
	
	@Autowired
	private CityReportService reportService;
	
	@Value("${weather.app.url}")
	private String url;
	
	@Value("${weather.api.key}")
	private String apiKey;

	// define endpoint for "/students"
	
	@GetMapping("/reports")
	public String getWeatherReports() {
		return "weather-reports";
	}
	
	@GetMapping("/search")
	public String search(Model theModel) {
		City aCity = new City();
		
		theModel.addAttribute("city", aCity);
		return "search-form";
	}
	
	@GetMapping("/processForm")
	public String processForm(@RequestParam String cityName, @RequestParam String countryName, @RequestParam String units, Model theModel) {
		System.out.println("Processing " + cityName + " with API key \"" + apiKey + "\"");

		RestTemplate restTemplate = new RestTemplate();
		StringBuffer buf = new StringBuffer(url);
		buf.append("?q=").append(cityName);
		if (countryName != null && countryName.length() > 0) {
			buf.append(",").append(countryName);
		}
//		String country = theCity.getCountryName();
//		if (country != null && country.length() > 0) {
//			buf.append(",").append(country);
//		}
		buf.append("&appid=").append(apiKey);
		if (units != null && units.length() > 0) {
			buf.append("&units=").append(units);
			defUnits = units;
		} else {
			buf.append("&units=").append(defUnits);
			units = defUnits;
//			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Eenheden niet geselecteerd");
		}
		buf.append("&lang=nl");
		String theUrl = buf.toString();
		System.out.println(theUrl);
//		theUrl = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
		WeatherReport report = restTemplate.getForObject(theUrl, WeatherReport.class);
		report.setUnits(units);
		theModel.addAttribute("report", report);
		
		doDatabaseUpdate(report);
		
		System.out.println("Weather report:\n" + report);
		return "city-report";
	}
	
	@GetMapping("/cities-list")
	public String citiesList(Model theModel) {
		List<CityReport> cityReports = reportService.getReports();
		System.out.println(cityReports.size() + " reports retrieved");
		theModel.addAttribute("cityReports", cityReports);
		return "cities-list";
	}
	
	@GetMapping("/processId")
	public String processId(@RequestParam int cityId, Model theModel) {
		System.out.println("Processing city #" + cityId + " with API key \"" + apiKey + "\"");

		RestTemplate restTemplate = new RestTemplate();
		StringBuffer buf = new StringBuffer(url);
		buf.append("?id=").append(cityId);
		buf.append("&appid=").append(apiKey);
		buf.append("&units=").append(defUnits);
		buf.append("&lang=nl");
		String theUrl = buf.toString();
		System.out.println(theUrl);
//		theUrl = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
		WeatherReport report = restTemplate.getForObject(theUrl, WeatherReport.class);
		report.setUnits(defUnits);
		theModel.addAttribute("report", report);
		
		doDatabaseUpdate(report);
		
		System.out.println("Weather report:\n" + report);
	
		return "city-report";
	}
	
	private void doDatabaseUpdate(WeatherReport report) {
		int cityId = report.getId();
		Timestamp timeStamp = new Timestamp(report.getDt());

		CityReport cityReport = reportService.getReport(cityId);
		if (cityReport == null) {
			cityReport = new CityReport();
			cityReport.setCityId(cityId);
			cityReport.setCityName(report.getName());
			cityReport.setCountryAbb(report.getSys().getCountry());
			cityReport.setQueryCount(0);
		}
		cityReport.setLastReport(timeStamp);
		int count = cityReport.getQueryCount();
		cityReport.setQueryCount(++count);
		
		reportService.saveReport(cityReport);
	}

	/*
	// define endpoint for "/students/{studentId} - return student at index
	
	@GetMapping("/reports/{studentId}")
	public WeatherReport getWeatherReport(@PathVariable int studentId) {
		// just index into the list ... keep it simple for now
		
		// check the studentID against list size
		if (studentId >= theWeatherReports.size() || studentId < 0) {
			throw new WeatherReportNotFoundException("WeatherReport id not found - " + studentId);
		}
		return theWeatherReports.get(studentId);
	}
	*/
	
}
