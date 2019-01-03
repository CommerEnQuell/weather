package nl.commerquell.weather.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.commerquell.weather.db.dao.CityReportDAO;
import nl.commerquell.weather.db.entity.CityReport;

@Service
public class CityReportServiceImpl implements CityReportService {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private CityReportDAO reportDAO;

	@Override
	@Transactional
	public CityReport getReport(int cityId) {
		logger.info("Retrieving last report timestamp for city #" + cityId);
		return reportDAO.getReport(cityId);
	}

	@Transactional
	@Override
	public List<CityReport> getReports() {
		logger.info("Retrieving last report timestamp for all available cities");
		return reportDAO.getReports();
	}

	@Transactional
	@Override
	public List<CityReport> searchReports(String filter) {
		logger.info("Retrieving last report timestamp for selected cities");
		return reportDAO.searchReports(filter);
	}

	@Transactional
	@Override
	public void saveReport(CityReport report) {
		logger.info("Creating or updating record for " + report.getCityName() + ", " + report.getCountryAbb() + " (cityId=" + report.getCityId() + ")");
		reportDAO.saveReport(report);
	}

	@Transactional
	@Override
	public void deleteReport(int cityId) {
		logger.info("Deleting record for city #" + cityId);
		reportDAO.deleteReport(cityId);
	}
}
