package nl.commerquell.weather.db.dao;

import java.util.List;

import nl.commerquell.weather.db.entity.Place;

public interface PlaceDAO {

	public Place getPlace(int cityId);
	
	/**
	 * Convenience method to get a place by its unique city name
	 * @param cityNameNL The city name according to Dutch standards
	 * @return The name of this place on the web, or <code>null</code> if it has not been found.
	 */
	public Place getPlace(String cityNameNL);
	
	public List<Place> getPlaces();

	public List<Place> searchPlaces(String filter);
	
	public void savePlace(Place place);
	
	public void deletePlace(int cityId);
}
