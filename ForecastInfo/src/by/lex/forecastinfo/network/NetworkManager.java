package by.lex.forecastinfo.network;

import android.util.Log;

public class NetworkManager {
	private static final String API_KEY = "c444c4d5ae9df95db1adb6d1a3875416";
	private static final String GET_WEATHER_URL = "https://api.forecast.io/forecast/" + API_KEY + "/";
	
	public static GetRequest<SimpleResponse> getWheather(double latitude, double longitude) {
		Log.d("Wheather", "url: " + GET_WEATHER_URL + latitude + ","  +longitude);
		// http://staging-webapi.lealtamedia.com/webapi/restServices/mob/states
		return new  GetRequest<SimpleResponse>(SimpleResponse.class, GET_WEATHER_URL + latitude + ","  +longitude);
		//return new  GetRequest<SimpleResponse>(SimpleResponse.class," http://staging-webapi.lealtamedia.com/webapi/restServices/mob/states");
	}
}
