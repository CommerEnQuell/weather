package nl.commerquell.weather.json.entity;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

import nl.commerquell.weather.utils.DateUtils;

@JsonRootName("WeatherReport")
public class WeatherReport {
	private Coord coord;
	private Weather[] weather;
	private String base;
	private Main main;
	private int visibility;
	private Wind wind;
	private Clouds clouds;
	private Rain rain;
	private Snow snow;
	private long dt;
	private Sys sys;
	private int id;
	private String name;
	private int cod;
	
	// Derived fields
	private String currentTime;
	private int units;
	
	public WeatherReport() {}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Weather[] getWeather() {
		return weather;
	}

	public void setWeather(Weather[] weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public Rain getRain() {
		return rain;
	}

	public void setRain(Rain rain) {
		this.rain = rain;
	}

	public Snow getSnow() {
		return snow;
	}

	public void setSnow(Snow snow) {
		this.snow = snow;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long date) {
		this.dt = 1000L * date;
		Date currentDate = new Date(dt);
		currentTime = DateUtils.format(currentDate, "DD-MM-YYYY hh:mm:ss");
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = 0;
		if (units != null && units.length() > 0) {
			if ("metric".equals(units)) {
				this.units = 1;
			} else if ("imperial".equals(units)) {
				this.units = 2;
			}
		}
		System.out.println("Units set to " + this.units);
	}
	
	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "WeatherReport [coord=" + coord + ", weather=" + Arrays.toString(weather) + ", base=" + base + ", main="
				+ main + ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", rain=" + rain
				+ ", snow=" + snow + ", dt=" + dt + ", sys=" + sys + ", id=" + id + ", name=" + name + ", cod=" + cod
				+ "]";
	}
	
	
}
