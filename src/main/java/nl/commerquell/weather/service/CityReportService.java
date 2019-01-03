package nl.commerquell.weather.service;

import java.util.List;

import nl.commerquell.weather.db.entity.CityReport;

public interface CityReportService {
	
	public CityReport getReport(int cityId);
	
	public List<CityReport> getReports();

	public List<CityReport> searchReports(String filter);
	
	public void saveReport(CityReport report);
	
	public void deleteReport(int cityId);

}
