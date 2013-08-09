package by.lex.forecastinfo.managers;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import by.lex.forecast.entities.Currently;

public class WheatherManager {
	
	public static double getLatitude(JSONObject jo)  {
		double latitude = 0;
		Object latObj;
		try {
			latObj = jo.get("latitude");
		} catch (JSONException e) {
			e.printStackTrace();
			return latitude;
		}
		if (latObj instanceof Double) {
			latitude = ((Double) latObj).doubleValue();
		}
		return latitude;
	}
	
	public static double getLongitude(JSONObject jo) {
		double longitude = 0;
		Object lonObj;
		try {
			lonObj = jo.get("longitude");
		} catch (JSONException e) {
			e.printStackTrace();
			return longitude;
		}
		if (lonObj instanceof Double) {
			longitude = ((Double) lonObj).doubleValue();
		}
		return longitude;
	}
	
	public static String getTimezone(JSONObject jo) {
		String timezone = "";
		Object timObj;
		try {
			timObj = jo.get("timezone");
		} catch (JSONException e) {
			e.printStackTrace();
			return timezone;
		}
		if (timObj instanceof String) {
			timezone = (String) timObj;
		}
		return timezone;
	}
	
	public static int getOffset(JSONObject jo) {
		int offset = 0;
		Object offObj;
		try {
			offObj = jo.get("offset");
		} catch (JSONException e) {
			e.printStackTrace();
			return offset;
		}
		
		if (offObj instanceof Integer) {
			offset = ((Integer) offObj).intValue();
		}
		return offset;
	}
	
	public static double toCelcium(double forengeit) {
		return 5 * (forengeit - 32) / 9;
	}
	
	public static Currently getCurrently(JSONObject jo) {
		Object timObj, sumObj, icnObj, preIntObj, preProObj, preTypObj, temObj, dewObj, winObj, winBeaObj;
		Object cloObj, humObj, preObj, visObj, ozoObj;
		try {
			JSONObject curObj = jo.getJSONObject("currently");
			if (curObj == null) return null;
			timObj = curObj.get("time");
			sumObj = curObj.get("summary");
			icnObj = curObj.get("icon");
			preIntObj = curObj.get("precipIntensity");
			preProObj = curObj.get("precipProbability");
			preTypObj = curObj.get("precipType");
			
			temObj = curObj.get("temperature");
			dewObj = curObj.get("dewPoint");
			winObj = curObj.get("windSpeed");
			winBeaObj = curObj.get("windBearing");
			cloObj = curObj.get("cloudCover");
			humObj = curObj.get("humidity");
			preObj = curObj.get("pressure");
			visObj = curObj.get("visibility");
			ozoObj = curObj.get("ozone");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		Log.d("Wheather", "time is:" + timObj);
		if (dewObj instanceof Integer) {
			Log.d("Wheather", "Integer type");
		}
		if (dewObj instanceof Double) {
			Log.d("Wheather", "Double type");
		}
		if (dewObj instanceof Float) {
			Log.d("Wheather", "Float type");
		}
		
		
		Currently currently = new Currently(((Integer)timObj).intValue(),
				(String)sumObj,
				(String)icnObj,
				((Double)preIntObj).doubleValue(),
				((Double)preProObj).doubleValue(),
				((String)preTypObj),
				((Double)temObj).doubleValue(),
				((Double)dewObj).intValue(),
				((Double)winObj).doubleValue(),
				((Integer)winBeaObj).intValue(),
				((Double)cloObj).intValue(),
				((Double)humObj).doubleValue(),
				((Double)preObj).doubleValue(),
				((Double)visObj).doubleValue(),
				((Double)ozoObj).doubleValue());
		return currently;
	}
	
}
