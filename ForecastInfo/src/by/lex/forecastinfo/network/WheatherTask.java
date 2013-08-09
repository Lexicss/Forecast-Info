package by.lex.forecastinfo.network;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import by.lex.forecastinfo.interfaces.ResponseListener;

public class WheatherTask extends AsyncTask<Double, Integer, JSONObject> {
	private static final String TAG = "WheatherTask";
	private static final String API_KEY = "c444c4d5ae9df95db1adb6d1a3875416";
	private static final String GET_WEATHER_URL = "https://api.forecast.io/forecast/" + API_KEY + "/";
	private ResponseListener responseListener;

	public WheatherTask(ResponseListener rl) {
		super();
		this.responseListener = rl;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.i(TAG, "GET Request started");
	}
	
	@Override
	protected JSONObject doInBackground(Double... geoParams) {
		if (geoParams.length < 2) {
			Log.e(TAG, "Invalid request parameters");
			return null;
		}
		double latitude = geoParams[0].doubleValue();
		double longitude = geoParams[1].doubleValue();
		String url = GET_WEATHER_URL + String.valueOf(latitude) + "," + String.valueOf(longitude);
		JSONParser parser = new JSONParser();
		JSONObject jsObject = parser.getJSONFromUrl(url);
		
		return jsObject;
	}
	
	@Override
	protected void onPostExecute(JSONObject jsonObject) {
		super.onPostExecute(jsonObject);
		responseListener.onResponseReceived(jsonObject);
		Log.i(TAG, "GET request finished");
	}

}
