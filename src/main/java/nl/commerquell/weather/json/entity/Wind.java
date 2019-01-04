package nl.commerquell.weather.json.entity;

public class Wind {
	private static String[] directions = new String[]
			{"N", "NNO", "NO", "ONO", "O", "OZO", "ZO", "ZZO", "Z", "ZZW", "ZW", "WZW", "W", "WNW", "NW", "NNW"};
	private float speed;
	private int deg;
	
	// Derived
	private String direction = directions[0];
	
	public Wind() {}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
		
		int idx = ((4 * deg + 45) % 1440) / 90;
		System.out.println("Degrees: " + deg + ", idx=" + idx);
		this.direction = directions[idx];
	}
	
	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", deg=" + deg + "]";
	}
	
	public String getDirection() {
		return direction;
	}
}
