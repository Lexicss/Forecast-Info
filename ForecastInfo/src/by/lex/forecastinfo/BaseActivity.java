package by.lex.forecastinfo;

import android.app.Activity;
import android.util.Log;
import by.lex.forecastinfo.services.NetworkSpiceService;

import com.octo.android.robospice.SpiceManager;

public abstract class BaseActivity extends Activity {
	private SpiceManager spiceManager = new SpiceManager(NetworkSpiceService.class);

	
	@Override
	protected void onStart() {
		Log.d("Wheather", "BaseActivity onStart");
		super.onStart();
		spiceManager.start(this);
	}
	
	@Override 
	protected void onStop() {
		Log.d("Wheather", "BaseActivity onStop");
		spiceManager.shouldStop();
		super.onStop();
	}
	
	public SpiceManager getSpiceManager() {
		return spiceManager;
	}
	
}
