package nl.commerquell.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.commerquell.weather.db.dao.ReportLogDAO;
import nl.commerquell.weather.db.entity.ReportLog;

@Service
public class ReportLogServiceImpl implements ReportLogService {
	
	@Autowired
	private ReportLogDAO reportLogDAO;

	@Override
	@Transactional
	public ReportLog getReportLog(int logId) {
		return reportLogDAO.getReportLog(logId);
	}

	@Override
	@Transactional
	public List<ReportLog> getReportLogs(int cityId) {
		return reportLogDAO.getReportLogs(cityId);
	}

	@Override
	@Transactional
	public List<ReportLog> searchReportLogs(String filter) {
		return reportLogDAO.searchReportLogs(filter);
	}

	@Override
	@Transactional
	public void saveReportLog(ReportLog reportLog) {
		reportLogDAO.saveReportLog(reportLog);
	}

	@Override
	@Transactional
	public void deleteReportLog(int logId) {
		reportLogDAO.deleteReportLog(logId);
	}

}
