package by.lex.forecast.entities;

public class Currently {
	private int time;
	private String summary;
	private String icon;
	private double precipIntensity;
	private double precipProbability;
	private String precipType;
	private double temperature;
	private int dewPoint;
	private double windSpeed;
	private int windBearing;
	private double cloudCover;
	private double humidity;
	private double pressure;
	private double visibiliy;
	private double ozone;
	
	public Currently(int time, String summary, String icon,
			double precipIntensity, double precipProbability, String precipType, double temperature,
			int dewPoint, double windSpeed, int windBearing, double cloudCover,
			double humidity, double pressure, double visibiliy, double ozone) {
		super();
		this.time = time;
		this.summary = summary;
		this.icon = icon;
		this.precipIntensity = precipIntensity;
		this.precipProbability = precipProbability;
		this.precipType = precipType;
		this.temperature = temperature;
		this.dewPoint = dewPoint;
		this.windSpeed = windSpeed;
		this.windBearing = windBearing;
		this.cloudCover = cloudCover;
		this.humidity = humidity;
		this.pressure = pressure;
		this.visibiliy = visibiliy;
		this.ozone = ozone;
	}

	public String getPrecipType() {
		return precipType;
	}

	public int getTime() {
		return time;
	}

	public String getSummary() {
		return summary;
	}

	public String getIcon() {
		return icon;
	}

	public double getPrecipIntensity() {
		return precipIntensity;
	}

	public double getPrecipProbability() {
		return precipProbability;
	}

	public double getTemperature() {
		return temperature;
	}

	public int getDewPoint() {
		return dewPoint;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public int getWindBearing() {
		return windBearing;
	}

	public double getCloudCover() {
		return cloudCover;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public double getVisibiliy() {
		return visibiliy;
	}

	public double getOzone() {
		return ozone;
	}

	
	
}
