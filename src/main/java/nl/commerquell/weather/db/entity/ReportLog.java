package nl.commerquell.weather.db.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reports_log")
public class ReportLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_id")
	private int logId;

	@Column(name="city_id")
	private int cityId;
	
	@Column(name="request_time")
	private Timestamp requestTime;
	
	@Column(name="report_time")
	private Timestamp reportTime;
	
	@Column(name="requested_by")
	private String requestedBy;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="city_id", insertable=false, updatable=false)
	private CityReport cityReport;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String getRequestedBy) {
		this.requestedBy = getRequestedBy;
	}

	public CityReport getCityReport() {
		return cityReport;
	}

	public void setCityReport(CityReport cityReport) {
		this.cityReport = cityReport;
	}

	@Override
	public String toString() {
		return "ReportLog [logId=" + logId + ", cityId=" + cityId + ", requestTime=" + requestTime + ", reportTime="
				+ reportTime + "]";
	}
	
	
	
}
