package nl.commerquell.weather.entity;

public class Coord {
	private float lon;
	private float lat;
	
	// Derived
	private String longitude;
	private String latitude;
	
	public Coord() {}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;

		float wlon = lon;
		char ew = 'O';
		if (wlon < 0.0f) {
			ew = 'W';
			wlon *= -1.0f;
		}
		int elon = Float.valueOf(wlon).intValue();
		float dlon= Float.valueOf(wlon - 1.0f * elon).floatValue();
		int mlon = Float.valueOf(dlon * 60.0f + 0.5f).intValue();
		this.longitude = elon + "&deg;" + (mlon < 10 ? "0" : "") + mlon + "' " + ew;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
		
		char ns = 'N';
		float wlat = lat;
		if (wlat < 0.0f) {
			ns = 'Z';
			wlat *= -1;
		}
		int elat = Float.valueOf(wlat).intValue();
		float dlat = Float.valueOf(wlat - 1.0f * elat).floatValue();
		int mlat = Float.valueOf(dlat * 60.0f + 0.5f).intValue();
		this.latitude = elat + "&deg;" + (mlat < 10 ? "0" : "") + mlat + "' " + ns;
	}

	@Override
	public String toString() {
		return "Coord [lon=" + lon + ", lat=" + lat + "]";
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

}
