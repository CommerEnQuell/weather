package nl.commerquell.weather.db.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.commerquell.weather.db.entity.CityReport;

@Repository
public class CityReportDAOImpl implements CityReportDAO {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public CityReport getReport(int id) {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Session obtained: " + session);
		CityReport retval = session.get(CityReport.class, id);

		return retval;
	}

	@Override
	public List<CityReport> getReports() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<CityReport> query = session.createQuery("from CityReport order by cityName", CityReport.class);
		List<CityReport> result = query.getResultList();
		return result;
	}

	@Override
	public List<CityReport> searchReports(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Query<CityReport> query = session.createQuery("from Report where " + filter + " order by cityName", CityReport.class);
		List<CityReport> result = query.getResultList();
		return result;
	}

	@Override
	public void saveReport(CityReport report) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(report);
	}

	@Override
	public void deleteReport(int id) {
		Session session = sessionFactory.getCurrentSession();
		CityReport report = session.get(CityReport.class, id);
		if (report != null) {
			session.delete(report);
		}
	}

}
