package nl.commerquell.weather.error;

public class WeatherErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
	
	public WeatherErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public String toString() {
		return "WeatherErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
