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
		boolean visibilityExists = false;
		try {
			JSONObject curObj = jo.getJSONObject("currently");
			if (curObj == null) return null;
			boolean precipationsExpected = curObj.has("precipType");
			visibilityExists = curObj.has("visibility");
			
			timObj = curObj.get("time");
			sumObj = curObj.get("summary");
			icnObj = curObj.get("icon");
			preIntObj = curObj.get("precipIntensity");
			preProObj = curObj.get("precipProbability");
			if (precipationsExpected) {
			  preTypObj = curObj.get("precipType");
			} else {
				preTypObj = null;
			}
			
			temObj = curObj.get("temperature");
			dewObj = curObj.get("dewPoint");
			winObj = curObj.get("windSpeed");
			winBeaObj = curObj.get("windBearing");
			cloObj = curObj.get("cloudCover");
			humObj = curObj.get("humidity");
			preObj = curObj.get("pressure");
			if (visibilityExists) {
				visObj = curObj.get("visibility");
			} else {
				visObj = null;
			}
			
			ozoObj = curObj.get("ozone");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
//		Log.d("Wheather", "time is:" + timObj);
//		if (dewObj instanceof Integer) {
//			Log.d("Wheather", "Integer type");
//		}
//		if (dewObj instanceof Double) {
//			Log.d("Wheather", "Double type");
//		}
//		if (dewObj instanceof Float) {
//			Log.d("Wheather", "Float type");
//		}
		
		double intensity;
		if (preIntObj instanceof Double) {
			intensity = ((Double) preIntObj).doubleValue();
		} else {
			intensity = ((double) ((Integer) preIntObj).intValue());
		}
		double propability;
		if (preProObj instanceof Double) {
			propability = ((Double) preProObj).doubleValue();
		} else {
			propability = ((double) ((Integer) preProObj).intValue());
		}
		double visibility;
		if (visibilityExists) {
			visibility = ((Double)visObj).doubleValue();
		} else {
			visibility = 0;
		}
		double cloudCover;
		if (cloObj instanceof Double) {
			cloudCover = ((Double) cloObj).doubleValue();
		} else {
			cloudCover = ((double)((Integer) cloObj).intValue());
		}
		
		
		
		
		Currently currently = new Currently(((Integer)timObj).intValue(),
				(String)sumObj,
				(String)icnObj,
				intensity,
				propability,
				((String)preTypObj),
				((Double)temObj).doubleValue(),
				((Double)dewObj).intValue(),
				((Double)winObj).doubleValue(),
				((Integer)winBeaObj).intValue(),
				cloudCover,
				((Double)humObj).doubleValue(),
				((Double)preObj).doubleValue(),
				visibility,
				((Double)ozoObj).doubleValue());
		return currently;
	}
	
}
