package nl.commerquell.weather.db.dao;

import java.util.List;

import nl.commerquell.weather.db.entity.Country;

public interface CountryDAO {
	
	public Country getCountry(String countryCd);
	
	public List<Country> getCountries();
	
	public List<Country> searchCountries(String filter);
	
	public void saveCountry(Country country);
	
	public void deleteCountry(String countryCd);

}
