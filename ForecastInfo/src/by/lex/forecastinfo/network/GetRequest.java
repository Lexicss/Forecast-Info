package by.lex.forecastinfo.network;

import android.util.Log;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class GetRequest<T extends ResponseObject> extends
		SpringAndroidSpiceRequest<T> {
	private Class<T> clazz;
	private String url;

	
	
	public GetRequest(Class<T> clazz, String url) {
		super(clazz);
		this.clazz = clazz;
		this.url = url;
		Log.d("Wheather", "GetRequest constructor");
	}


	@Override
	public T loadDataFromNetwork() throws Exception {
		Log.d("Wheather", "loadDataFromNetwork executed");
		return getRestTemplate().getForObject(url, clazz);
	}

}
