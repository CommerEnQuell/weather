package nl.commerquell.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import nl.commerquell.weather.db.entity.CityReport;
import nl.commerquell.weather.db.entity.Country;
import nl.commerquell.weather.db.entity.Place;
import nl.commerquell.weather.db.entity.ReportLog;
import nl.commerquell.weather.service.CityReportService;
import nl.commerquell.weather.service.CountryService;
import nl.commerquell.weather.service.PlaceService;
import nl.commerquell.weather.service.ReportLogService;
import nl.commerquell.weather.utils.JspUtils;

@Controller
@RequestMapping("/maint")
public class MaintenanceController {

	@Autowired
	private CityReportService reportService;
	
	@Autowired
	private PlaceService cityService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ReportLogService reportLogService;

	
	@GetMapping("/country")
	private String getCountry(@RequestParam String countryCd, Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		Country aCountry = countryService.getCountry(countryCd);
		if (aCountry == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Land \"" + countryCd + "\" niet in database");
		}
		
		theModel.addAttribute("country", aCountry);
		return "country-form";
	}
	
	@GetMapping("/processCountry")
	private String processCountry(@RequestParam String countryCd, @RequestParam String countryName, Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		Country theCountry = new Country();
		theCountry.setCountryCd(countryCd);
		theCountry.setCountryName(countryName);
		countryService.saveCountry(theCountry);
		return "cities-list-redir";
	}

	@GetMapping("/city")
	private String getCity(@RequestParam int cityId, @RequestParam String cityName, Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		CityReport theCityReport = reportService.getReport(cityId); 
		Place theCity = cityService.getPlace(cityId);
		if (theCity == null) {
			theCity = new Place();
			theCity.setCityId(cityId);
			theCity.setCityNameNL(cityName);
			theCity.setCityReport(theCityReport);
		}
		
		theModel.addAttribute("city", theCity);
		return "city-form";
	}
	
	@GetMapping("/processCity")
	private String processCity(@RequestParam String cityNameNL, @RequestParam int cityId, Model theModel) {
		Place theCity = new Place();
		theCity.setCityNameNL(cityNameNL);
		theCity.setCityId(cityId);
		cityService.savePlace(theCity);
		return "cities-list-redir";
	}

	
	@GetMapping("/requests")
	public String getLoggings(@RequestParam int cityId, Model theModel) {
		JspUtils.addUsernameToModel(theModel);
		CityReport cityReport = reportService.getReport(cityId);
		String cityName = cityReport.getCityName();
		String countryAbb = cityReport.getCountryAbb();
		Country country = countryService.getCountry(countryAbb);
		String countryName = countryAbb;
		if (country != null) {
			countryName = country.getCountryName();
		}
		List<ReportLog> loggings = reportLogService.getReportLogs(cityId);
		if (loggings != null) {
			cityReport.setLoggings(loggings);
		}
		theModel.addAttribute("cityName", cityName);
		theModel.addAttribute("countryName", countryName);
		theModel.addAttribute("loggings", loggings);
		return "requests";
	}
}
