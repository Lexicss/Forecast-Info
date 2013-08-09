package by.lex.forecastinfo.interfaces;

import org.json.JSONObject;

public interface ResponseListener {
	
	void onResponseReceived(JSONObject jsonObject);

}
