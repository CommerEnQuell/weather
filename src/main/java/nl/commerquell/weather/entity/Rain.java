package nl.commerquell.weather.entity;

public class Rain {
	private Integer hours1 = null;
	private Integer hours3 = null;
	
	public Rain() {}

	public Integer getHours1() {
		return hours1;
	}

	/**
	 * Since Java does not allow a variable starting with a digit, its name is changed to <code>hours1</code>.<br>
	 * The setter name is <code>set1h</code> because the variable is set from a JSON variable named <code>1h</code>
	 * @param hours1 The amount of rain during the last hour
	 */
	public void set1h(Integer hours1) {
		this.hours1 = hours1;
	}

	public Integer getHours3() {
		return hours3;
	}

	/**
	 * Since Java does not allow a variable starting with a digit, its name is changed to <code>hours3</code>.<br>
	 * The setter name is <code>set3h</code> because the variable is set from a JSON variable named <code>3h</code>
	 * @param hours3 The amount of rain during the last three hours
	 */
	public void set3h(Integer hours3) {
		this.hours3 = hours3;
	}

	@Override
	public String toString() {
		return "Rain [1h=" + hours1 + ", 3h=" + hours3 + "]";
	}
	
	
}
