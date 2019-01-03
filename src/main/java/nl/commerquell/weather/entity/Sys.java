package nl.commerquell.weather.entity;

import java.util.Date;

import nl.commerquell.weather.utils.DateUtils;

public class Sys {
	private int type;
	private int id;
	private float message;
	private String country;
	private long sunrise;
	private long sunset;
	
	// Derived
	private String sunriseTime;
	private String sunsetTime;
	
	public Sys() {}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMessage() {
		return message;
	}

	public void setMessage(float message) {
		this.message = message;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getSunrise() {
		return sunrise;
	}

	public void setSunrise(long sunriseMsec) {
		this.sunrise = 1000L * sunriseMsec;
		Date date = new Date(sunrise);
		this.sunriseTime = DateUtils.format(date, "hh:mm");
	}

	public long getSunset() {
		return sunset;
	}

	public void setSunset(long sunsetMsec) {
		this.sunset = 1000L * sunsetMsec;
		Date date = new Date(sunset);
		this.sunsetTime = DateUtils.format(date, "hh:mm");
	}

	@Override
	public String toString() {
		return "Sys [type=" + type + ", id=" + id + ", message=" + message + ", country=" + country + ", sunrise="
				+ sunrise + ", sunset=" + sunset + "]";
	}

	public String getSunriseTime() {
		return sunriseTime;
	}

	public String getSunsetTime() {
		return sunsetTime;
	}
	
	
}
