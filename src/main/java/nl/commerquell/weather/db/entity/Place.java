package nl.commerquell.weather.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="placenames_nl", uniqueConstraints=@UniqueConstraint(name="psnl_uk", columnNames= {"name_nl"}))
public class Place {

	@Id
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="name_nl")
	private String cityNameNL;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="city_id")
	private CityReport cityReport;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityNameNL() {
		return cityNameNL;
	}

	public void setCityNameNL(String cityNameNL) {
		this.cityNameNL = cityNameNL;
	}

	public CityReport getCityReport() {
		return cityReport;
	}

	public void setCityReport(CityReport cityReport) {
		this.cityReport = cityReport;
	}

	@Override
	public String toString() {
		return "City [cityNameNL=" + cityNameNL + ", cityId=" + cityId + "]";
	}
	
}
