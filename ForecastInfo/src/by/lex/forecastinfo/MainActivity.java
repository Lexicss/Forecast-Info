package by.lex.forecastinfo;


import org.json.JSONObject;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import by.lex.forecast.entities.Currently;
import by.lex.forecastinfo.interfaces.ResponseListener;
import by.lex.forecastinfo.managers.WheatherManager;
import by.lex.forecastinfo.network.WheatherTask;

public class MainActivity extends BaseActivity implements  ResponseListener{
	private static final String JSON_CACHE_KEY = "wheather_json";
	private static final double SLICE = 22.5;
	private static final double HALF_SLICE = SLICE / 2;
	private TextView zoneText;
	private TextView offsetText;
	private TextView timeText;
	private TextView summaryText;
	private ImageView iconImage;
	
	private TextView preIntSign;
	private TextView preProSign;
	private TextView precipIntensityText;
	private TextView precipPropabilityText;
	private TextView precipType;
	
	private TextView temperatureText;
	private TextView dewPointText;
	private TextView windSpeed;
	private TextView windBearing;
	
	//private Button button1;
	private JSONObject jsonObject;
	
	private String timezone;
	private int offset;
	private Currently currently;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViews();
		
		//button1 = (Button) findViewById(R.id.button1);
		//button1.setOnClickListener((android.view.View.OnClickListener) this);
		load();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void findViews() {
		zoneText = (TextView) findViewById(R.id.zone_text);
		offsetText = (TextView) findViewById(R.id.offset_text);
		timeText = (TextView) findViewById(R.id.timeText);
		summaryText = (TextView) findViewById(R.id.summaryText);
		iconImage = (ImageView) findViewById(R.id.iconImage);
		
		preIntSign = (TextView) findViewById(R.id.prepIntSign);
		preProSign = (TextView) findViewById(R.id.prepPropSign);
		precipIntensityText = (TextView) findViewById(R.id.precipIntensityText);
		precipPropabilityText = (TextView) findViewById(R.id.precipPropabilityText);
		precipType = (TextView) findViewById(R.id.precip_type_text);
		temperatureText = (TextView) findViewById(R.id.temperature_text);
		dewPointText = (TextView) findViewById(R.id.dew_point_text);
		windSpeed = (TextView) findViewById(R.id.wind_speed);
		windBearing = (TextView) findViewById(R.id.wind_bearing_text);
	}
	
	private void load() {

		
		WheatherTask wheatherTask = new WheatherTask(this);
		Double[] geoParams = {53.9, 27.66}; // for Minsk;
		wheatherTask.execute(geoParams);
		
	}
	
	@Override
	public void onResponseReceived(JSONObject jsonObject) {
		Log.d("Wheather", "onResponseReceived");
		if (jsonObject == null) {
			Log.e("Wheather", "Error in receiving the response");
			return;
		}
		
		this.jsonObject = jsonObject;
		timezone = WheatherManager.getTimezone(jsonObject);
		offset = WheatherManager.getOffset(jsonObject);
		currently = WheatherManager.getCurrently(jsonObject);
		if (currently == null) {
			Log.e("Wheather", "Error in receaving currently");
			return;
		}
		
		boolean precipationsExpected = currently.getPrecipType() != null;
		
		Log.i("Test", "Currently: " + currently.toString());
		Log.i("Test", "summary: " + currently.getSummary() + " temperature: " + currently.getTemperature() + " dewPoint: " + currently.getDewPoint() + " windBearing: " + currently.getWindBearing());
		Log.i("Test", "Preciptation Intensity: " + currently.getPrecipIntensity() + " time = " + currently.getTime());
		
		zoneText.setText(timezone);
		if (offset > 0) {
		  offsetText.setText("GMT (+" + offset + ")");
		} else {
		  offsetText.setText("GMT (" + offset + ")");
		}
		
		int ttime = currently.getTime();
		long timelong = ttime * 1000L;
		String dateFormat = (String) DateFormat.format("dd.MM.yyyy hh:mm:ss aa", timelong);
		timeText.setText(dateFormat);
		
		summaryText.setText(currently.getSummary());
		
		String icon = currently.getIcon();
		Log.d("Test", "icon: " + icon);
		if (icon.equals("clear-day")) {
			iconImage.setImageResource(R.drawable.icon_1clear_day);
		} else if (icon.equals("clear-night")) {
			iconImage.setImageResource(R.drawable.icon_2clear_night);
		} else if (icon.equals("rain")) {
			iconImage.setImageResource(R.drawable.icon_3rain);
		} else if (icon.equals("snow")) {
			iconImage.setImageResource(R.drawable.icon_4snow);
		} else if (icon.equals("sleet")) {
			iconImage.setImageResource(R.drawable.icon_5sleet);
		} else if (icon.equals("wind")) {
			iconImage.setImageResource(R.drawable.icon_6wind);
		} else if (icon.equals("fog")) {
			iconImage.setImageResource(R.drawable.icon_7fog);
		} else if (icon.equals("cloudy")) {
			iconImage.setImageResource(R.drawable.icon_8cloudy);
		} else if (icon.equals("partly-cloudy-day")) {
			iconImage.setImageResource(R.drawable.icon_9party_cloudy_day);
		} else if (icon.equals("partly-cloudy-night")) {
			iconImage.setImageResource(R.drawable.icon_10party_cloudy_night);
		} else if (icon.equals("hail")) {
			iconImage.setImageResource(R.drawable.icon_11hail);
		} else if (icon.equals("thunderstorm")) {
			iconImage.setImageResource(R.drawable.icon_12thunderstorm);
		} else if (icon.equals("tornado")) {
			iconImage.setImageResource(R.drawable.icon_13tornado);
		}
		
		if (precipationsExpected) {
			preIntSign.setVisibility(View.VISIBLE);
			preProSign.setVisibility(View.VISIBLE);
			precipIntensityText.setVisibility(View.VISIBLE);
			precipPropabilityText.setVisibility(View.VISIBLE);
			precipIntensityText.setText(String.valueOf(currently.getPrecipIntensity()));
			precipPropabilityText.setText(" " + 
			
					(int)(currently.getPrecipProbability()*100) + "%");
			precipType.setText(currently.getPrecipType());	
		} else {
			preIntSign.setVisibility(View.GONE);
			preProSign.setVisibility(View.GONE);
			precipIntensityText.setVisibility(View.GONE);
			precipPropabilityText.setVisibility(View.GONE);
			precipType.setText("No preciptations");
		}
		
		double celciumTemperature = WheatherManager.toCelcium(currently.getTemperature());
		temperatureText.setText(String.valueOf(Math.round(celciumTemperature)));
		
		double celciumDew = WheatherManager.toCelcium(currently.getDewPoint());
		
		dewPointText.setText(String.valueOf(Math.round(celciumDew)));
		windSpeed.setText(String.valueOf(currently.getWindSpeed()));
		
		String windSource;
		int bearing = currently.getWindBearing();
		
		if (bearing < getValueByZone(0) || bearing > getValueByZone(16)) {
			windSource = "N";
		} else if (bearing < getValueByZone(1)) {
			windSource = "N-NE";
		} else if (bearing < getValueByZone(2)) {
			windSource = "NE";
		} else if (bearing < getValueByZone(3)) {
			windSource = "E-NE";
		} else if (bearing < getValueByZone(4)) {
			windSource = "E";
		} else if (bearing < getValueByZone(5)) {
			windSource = "E-SE";
		} else if (bearing < getValueByZone(6)) {
			windSource = "SE";
		} else if (bearing < getValueByZone(7)) {
			windSource = "S-SE";
		} else if (bearing < getValueByZone(8)) {
			windSource = "S";
		} else if (bearing < getValueByZone(9)) {
			windSource = "S-SW";
		} else if (bearing < getValueByZone(10)) {
			windSource = "SW";
		} else if (bearing < getValueByZone(11)) {
			windSource = "W-SW";
		} else if (bearing < getValueByZone(12)) {
			windSource = "W";
		} else if (bearing < getValueByZone(13)) {
			windSource = "W-NW";
		} else if (bearing < getValueByZone(14)) {
			windSource = "NW";
		} else windSource = "N-NW";
		
		windBearing.setText(windSource);
	}

	private int getValueByZone(int zoneNum) {
		if (zoneNum < 16) {
		    return (int) Math.round(HALF_SLICE + zoneNum * SLICE);
		} else {
			return (int)Math.round(360 - HALF_SLICE);
		}
	}
	
}
