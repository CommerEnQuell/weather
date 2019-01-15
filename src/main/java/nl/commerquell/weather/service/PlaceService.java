package nl.commerquell.weather.service;

import java.util.List;

import nl.commerquell.weather.db.entity.Place;

public interface PlaceService {

	public Place getPlace(int placeId);
	
	public Place getPlace(String cityNameNL);
	
	public List<Place> getPlaces();

	public List<Place> searchPlaces(String filter);
	
	public void savePlace(Place place);
	
	public void deletePlace(int cityId);
}
