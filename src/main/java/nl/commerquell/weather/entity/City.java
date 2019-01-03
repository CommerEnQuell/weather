package nl.commerquell.weather.entity;

import java.util.LinkedHashMap;

public class City {
	private String cityName;
	private String countryName = "";
	private String units;
	
	private LinkedHashMap<String, String> unitOptions;
	
	public City() {
		unitOptions = new LinkedHashMap<String, String>();
		unitOptions.put("", "standaard");
		unitOptions.put("metric", "metrisch");
		unitOptions.put("imperial", "imperiaal");
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String name) {
		this.cityName = name;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public LinkedHashMap<String, String> getUnitOptions() {
		return unitOptions;
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", countryName=" + countryName + "]";
	}

}
