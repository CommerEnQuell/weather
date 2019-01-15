package nl.commerquell.weather.db.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="city_reports")
public class CityReport {
	
	@Id
	@Column(name="city_id")
	private int cityId;

	@Column(name="city_name")
	private String cityName;
	
	@Column(name="country_abb")
	private String countryAbb;
	
	@Column(name="last_report")
	private Timestamp lastReport;
	
	@Column(name="query_count")
	private int queryCount;
	
	@ManyToOne
	@JoinColumn(name="country_abb", nullable=false, insertable=false, updatable=false)
	private Country country;

	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy = "cityReport",
			   cascade = {CascadeType.DETACH, CascadeType.MERGE,
					   	  CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ReportLog> loggings;

	@OneToOne(mappedBy="cityReport",
	  cascade=CascadeType.ALL)
	private Place city;
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryAbb() {
		return countryAbb;
	}

	public void setCountryAbb(String countryAbb) {
		this.countryAbb = countryAbb;
	}

	public Timestamp getLastReport() {
		return lastReport;
	}

	public void setLastReport(Timestamp lastReport) {
		this.lastReport = lastReport;
	}
	
	public int getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<ReportLog> getLoggings() {
		return loggings;
	}

	public void setLoggings(List<ReportLog> loggings) {
		this.loggings = loggings;
	}

	public Place getCity() {
		return city;
	}

	public void setCity(Place city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Report [id=" + cityId + ", cityName=" + cityName + ", countryAbb=" + countryAbb + ", lastReport="
				+ lastReport + "]";
	}
}
