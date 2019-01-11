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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import nl.commerquell.weather.db.entity.CityReport;
import nl.commerquell.weather.db.entity.Country;
import nl.commerquell.weather.db.entity.ReportLog;
import nl.commerquell.weather.json.entity.City;
import nl.commerquell.weather.json.entity.WeatherReport;
import nl.commerquell.weather.service.CityReportService;
import nl.commerquell.weather.service.CountryService;
import nl.commerquell.weather.service.ReportLogService;

@Controller
@RequestMapping("/api")
public class WeatherReportController {
	private String defUnits = "metric";
	
	@Autowired
	private CityReportService reportService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ReportLogService reportLogService;
	
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
		String fullCityName = cityName + ((countryName != null && countryName.length() > 0) ? "," + countryName : "");
		appendParam(buf, '?', "q", fullCityName);
		appendParam(buf, '&', "appid", apiKey);
		appendParam(buf, '&', "units", (units != null && units.length() > 0 ? units : defUnits));
		appendParam(buf, '&', "lang", "nl");
		String fullUrl = buf.toString();
		System.out.println(fullUrl);
		
//		Map<String, String> urlParms = new HashMap<String, String>();
//		String fullUrl = url + "&appid=" + apiKey;
/*		
		
//		if (units != null && units.length() > 0)
			urlParms.put("units", (units != null && units.length() > 0 ? units : defUnits));
			urlParms.put("lang", "nl");
			urlParms.put("appid", apiKey);
			urlParms.put("q", fullCityName);
		
//		theUrl = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
//		WeatherReport report = restTemplate.getForObject(theUrl, WeatherReport.class);
*/
		WeatherReport report = restTemplate.getForObject(fullUrl, WeatherReport.class);
		report.setUnits(units);
		theModel.addAttribute("report", report);
		
		doDatabaseUpdate(report);
		
		System.out.println("Weather report:\n" + report);
		return "city-report";
	}
	
	@GetMapping("/country")
	private String getCountry(@RequestParam String countryCd, Model theModel) {
		Country aCountry = countryService.getCountry(countryCd);
		if (aCountry == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Land \"" + countryCd + "\" niet in database");
		}
		
		theModel.addAttribute("country", aCountry);
		return "country-form";
	}
	
	@GetMapping("/processCountry")
	private String processCountry(@RequestParam String countryCd, @RequestParam String countryName, Model theModel) {
		Country theCountry = new Country();
		theCountry.setCountryCd(countryCd);
		theCountry.setCountryName(countryName);
		countryService.saveCountry(theCountry);
		return citiesList(theModel);
	}
	
	private void appendParam(StringBuffer buf, char addIt, String param, String value ) {
		buf.append(addIt).append(param + "=" + value);
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
		Timestamp now = new Timestamp(System.currentTimeMillis());

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
		
		String countryCd = cityReport.getCountryAbb();
		Country country = countryService.getCountry(countryCd);
		if (country == null) {
			country = new Country();
			country.setCountryCd(countryCd);
			country.setCountryName("<< " + countryCd + " >>");
			countryService.saveCountry(country);
		}
		
		reportService.saveReport(cityReport);

		ReportLog reportLog = new ReportLog();
		reportLog.setCityId(cityId);
		reportLog.setReportTime(timeStamp);
		reportLog.setRequestTime(now);
		reportLogService.saveReportLog(reportLog);
		
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
