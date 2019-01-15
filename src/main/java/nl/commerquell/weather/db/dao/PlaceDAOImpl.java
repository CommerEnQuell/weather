package nl.commerquell.weather.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.commerquell.weather.db.entity.Place;

@Repository
public class PlaceDAOImpl implements PlaceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Place getPlace(int cityId) {
		Session session = sessionFactory.getCurrentSession();
		Place retval = session.get(Place.class, cityId);
		return retval;
	}
	
	@Override
	public Place getPlace(String cityNameNL) {
		List<Place> aList =  searchPlaces("cityNameNL = '" + cityNameNL + "'");
		Place retval = null;
		if (aList != null && !aList.isEmpty()) {
			retval = aList.get(0);
		}
		return retval;
	}

	@Override
	public List<Place> getPlaces() {
		Session session = sessionFactory.getCurrentSession();
		Query<Place> aQuery = session.createQuery("from Place order by cityNameNL", Place.class);
		List<Place> retval = aQuery.getResultList();
		return retval;
	}

	@Override
	public List<Place> searchPlaces(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Query<Place> aQuery = session.createQuery("from Place where " + filter + " order by cityNameNL", Place.class);
		List<Place> retval = aQuery.getResultList();
		return retval;
	}

	@Override
	public void savePlace(Place place) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(place);
	}

	@Override
	public void deletePlace(int cityId) {
		Session session = sessionFactory.getCurrentSession();
		Place theCity = getPlace(cityId);
		if (theCity != null) {
			session.delete(theCity);
		} else {
			throw new RuntimeException("A city with id=" + cityId + " does not exist and therefore cannot be deleted");
		}
	}

}
