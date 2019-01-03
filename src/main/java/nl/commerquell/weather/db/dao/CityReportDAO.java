package nl.commerquell.weather.db.dao;

import java.util.List;

import nl.commerquell.weather.db.entity.CityReport;

public interface CityReportDAO {
	
	public CityReport getReport(int id);
	
	public List<CityReport> getReports();

	public List<CityReport> searchReports(String filter);
	
	public void saveReport(CityReport report);
	
	public void deleteReport(int id);
}
