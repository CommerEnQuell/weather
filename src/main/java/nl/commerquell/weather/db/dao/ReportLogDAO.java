package nl.commerquell.weather.db.dao;

import java.util.List;

import nl.commerquell.weather.db.entity.ReportLog;

public interface ReportLogDAO {

	public ReportLog getReportLog(int logId);
	
	public List<ReportLog> getReportLogs(int cityId);
	
	public List<ReportLog> searchReportLogs(String filter);
	
	public void saveReportLog(ReportLog country);
	
	public void deleteReportLog(int logId);

}
