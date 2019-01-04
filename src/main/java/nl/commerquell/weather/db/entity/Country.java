package nl.commerquell.weather.db.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {

	@Id
	@Column(name="country_cd")
	private String countryCd;
	
	@Column(name="name")
	private String countryName;
	
	@OneToMany(mappedBy="country")
	private Set<CityReport> cityReports;

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryCd=\"" + countryCd + "\", countryName=\"" + countryName + "\"]";
	}
}
