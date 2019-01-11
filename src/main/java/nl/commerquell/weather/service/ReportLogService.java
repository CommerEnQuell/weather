package nl.commerquell.weather.service;

import java.util.List;

import nl.commerquell.weather.db.entity.ReportLog;

public interface ReportLogService {

	public ReportLog getReportLog(int logId);
	
	public List<ReportLog> getReportLogs(int cityId);
	
	public List<ReportLog> searchReportLogs(String filter);
	
	public void saveReportLog(ReportLog reportLog);
	
	public void deleteReportLog(int logId);

}
