package nl.commerquell.weather.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.commerquell.weather.db.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Country getCountry(String countryCd) {
		Session session = sessionFactory.getCurrentSession();
		Country retval = session.get(Country.class, countryCd);
		return retval;
	}

	@Override
	public List<Country> getCountries() {
		Session session = sessionFactory.getCurrentSession();
		Query<Country> theQuery = session.createQuery("from Country order by countryCd", Country.class);
		List<Country> retval = theQuery.getResultList();
		
		return retval;
	}

	@Override
	public List<Country> searchCountries(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Query<Country> theQuery = session.createQuery("from Country where " + filter + " order by countryCd", Country.class);
		List<Country> retval = theQuery.getResultList();
		return retval;
	}

	@Override
	public void saveCountry(Country country) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(country);
	}

	@Override
	public void deleteCountry(String countryCd) {
		Session session = sessionFactory.getCurrentSession();
		Country country = getCountry(countryCd);
		if (country != null) {
			session.delete(country);
		} else {
			throw new RuntimeException("Country \"" + countryCd + "\" does not exist and therefore cannot be deleted");
		}
	}

}
