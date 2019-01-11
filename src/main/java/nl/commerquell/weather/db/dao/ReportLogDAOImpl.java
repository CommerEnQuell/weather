package nl.commerquell.weather.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.commerquell.weather.db.entity.ReportLog;

@Repository
public class ReportLogDAOImpl implements ReportLogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ReportLog getReportLog(int logId) {
		Session session = sessionFactory.getCurrentSession();
		ReportLog retval = session.get(ReportLog.class, logId);
		return retval;
	}

	@Override
	public List<ReportLog> getReportLogs(int cityId) {
		return searchReportLogs("cityId = " + cityId);
	}

	@Override
	public List<ReportLog> searchReportLogs(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Query<ReportLog> query = session.createQuery("from ReportLog where " + filter + " order by cityId, logId desc", ReportLog.class);
		List<ReportLog> retval = query.getResultList();
		return retval;
	}

	@Override
	public void saveReportLog(ReportLog reportLog) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reportLog);
	}

	@Override
	public void deleteReportLog(int logId) {
		Session session = sessionFactory.getCurrentSession();
		ReportLog reportLog = getReportLog(logId);
		if (reportLog != null) {
			session.delete(reportLog);
		} else {
			throw new RuntimeException("There does not exist a report log with id=" + logId + " and therefore it cannot be deleted");
		}
	}

}
