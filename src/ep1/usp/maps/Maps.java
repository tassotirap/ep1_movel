package ep1.usp.maps;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.maps.MapActivity;

import ep1.usp.R;
import ep1.usp.access.db.OverlayDao;
import ep1.usp.lib.ShowDialog;
import ep1.usp.maps.Overlay.MyOverlays;

public class Maps extends MapActivity
{
	private MapsMenu mapsMenus = null;
	private MapsMenusButtons mapsMenusButtons = null;
	private MapsSettings mapsSettings = null;
	private MyLocationListener myLocationOverlay = null;
	private MyOverlays myOverlays = null;
	private OverlayDao overlayDao = null;

	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if (msg.what == 0)
				showDialog(getString(R.string.msgErrorTitle), getString(R.string.msgErrorMsg));
			else
				showDialog(getString(R.string.msgSucess), getString(R.string.msgSucessAddPoint));
		}
	};

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}

	public MapsMenu getMapsMenus()
	{
		if (mapsMenus == null)
			mapsMenus = new MapsMenu(this);
		return mapsMenus;
	}

	public MapsMenusButtons getMapsMenusButtons()
	{
		if (mapsMenusButtons == null)
			mapsMenusButtons = new MapsMenusButtons(this);
		return mapsMenusButtons;
	}

	public MapsSettings getMapsSettings()
	{
		if (mapsSettings == null)
			mapsSettings = new MapsSettings(this);
		return mapsSettings;
	}

	public MyLocationListener getMyLocation()
	{
		if (myLocationOverlay == null)
			myLocationOverlay = new MyLocationListener(this, getMapsSettings().getMap());
		return myLocationOverlay;
	}

	public MyOverlays getMyOverlays()
	{
		if (myOverlays == null)
			myOverlays = new MyOverlays(this);
		return myOverlays;
	}

	public OverlayDao getOverlayDao()
	{
		if (overlayDao == null)
			overlayDao = new OverlayDao(getApplicationContext());
		return overlayDao;
	}

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		getMapsMenus().init();
		getMapsMenusButtons().initDraw();
	}

	public void showDialog(String title, String message)
	{
		ShowDialog.show(title, message, this);
	}
}
