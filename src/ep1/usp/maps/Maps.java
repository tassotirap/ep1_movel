package ep1.usp.maps;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.maps.MapActivity;
import ep1.usp.R;

public class Maps extends MapActivity
{
	private MapsMenu mapsMenus = null;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		
		getMapsMenus().init();		
	}
	
	public MapsMenu getMapsMenus()
	{
		if(mapsMenus == null)
			mapsMenus = new MapsMenu(this);
		return mapsMenus;
	}

	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			showErrorDialog(getApplicationContext().getString(R.string.msgErrorTitle), getApplicationContext().getString(R.string.msgErrorMsg));
		}
	};
	
	@Override
	protected void onResume()
	{
		super.onResume();
		getMapsMenus().setButtons();
		getMapsMenus().drawOverlays();
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}
	
	public void showErrorDialog(String title, String message)
	{
		Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setNeutralButton("OK", null);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setNeutralButton("OK", null).show();
	}
}
