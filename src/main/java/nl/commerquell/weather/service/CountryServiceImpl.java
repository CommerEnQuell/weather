package nl.commerquell.weather.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.commerquell.weather.db.dao.CountryDAO;
import nl.commerquell.weather.db.entity.Country;

@Service
public class CountryServiceImpl implements CountryService {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional
	public Country getCountry(String countryCd) {
		logger.fine("Querying for country with code \"" + countryCd + "\"");
		return countryDAO.getCountry(countryCd);
	}

	@Override
	@Transactional
	public List<Country> getCountries() {
		logger.fine("Querying for all countries");
		return countryDAO.getCountries();
	}

	@Override
	@Transactional
	public List<Country> searchCountries(String filter) {
		logger.fine("Querying for countries matching \"" + filter + "\"");
		return countryDAO.searchCountries(filter);
	}

	@Override
	@Transactional
	public void saveCountry(Country country) {
		logger.info("Inserting or updating country \"" + country.getCountryCd() + "\"");
		countryDAO.saveCountry(country);
	}

	@Override
	@Transactional
	public void deleteCountry(String countryCd) {
		logger.info("Deleting country \"" + countryCd + "\"");
		countryDAO.deleteCountry(countryCd);
	}

}
