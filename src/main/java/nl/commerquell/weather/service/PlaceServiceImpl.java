package nl.commerquell.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.commerquell.weather.db.dao.PlaceDAO;
import nl.commerquell.weather.db.entity.Place;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired
	private PlaceDAO cityDAO;

	@Override
	@Transactional
	public Place getPlace(int cityId) {
		return cityDAO.getPlace(cityId);
	}

	@Override
	@Transactional
	public Place getPlace(String cityNameNL) {
		return cityDAO.getPlace(cityNameNL);
	}
	
	@Override
	@Transactional
	public List<Place> getPlaces() {
		return cityDAO.getPlaces();
	}

	@Override
	@Transactional
	public List<Place> searchPlaces(String filter) {
		return cityDAO.searchPlaces(filter);
	}

	@Override
	@Transactional
	public void savePlace(Place place) {
		cityDAO.savePlace(place);
	}
	
	@Override
	@Transactional
	public void deletePlace(int cityId) {
		cityDAO.deletePlace(cityId);
	}
}
