package ep1.usp.maps;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import ep1.usp.R;
import ep1.usp.access.db.MapSettingsDao;
import ep1.usp.access.db.OverlayDao;
import ep1.usp.access.db.SettingsDao;
import ep1.usp.maps.Overlay.MyOverlays;

public class MapsSettings
{
	private Maps mActivity;
	private MapController mapController;
	private MapView mapView = null;
	private MyOverlays myOverlays;
	private SettingsDao settingsDao;
	private OverlayDao overlayDao;
	private MyLocationListener myLocationListener;

	public MapsSettings(Maps mActivity)
	{
		this.mActivity = mActivity;
		myOverlays = new MyOverlays(mActivity);
		settingsDao = new SettingsDao(mActivity.getApplicationContext());
		overlayDao = new OverlayDao(mActivity.getApplicationContext());
	}

	public void init()
	{
		getMap().setBuiltInZoomControls(true);
		resetMaps();
		enableGPS();
	}
	
	public void resetMaps()
	{
		setZoom(15);
		setCenterGeoPoint(settingsDao.GetUspCenter());
	}

	public MapController getMapController()
	{
		if (mapController == null)
		{
			mapController = getMap().getController();
		}

		return mapController;
	}

	public MapView getMap()
	{
		if (mapView == null)
		{
			mapView = (MapView) mActivity.findViewById(R.id.mapview);
		}

		return mapView;
	}

	public void setZoom(int zoom)
	{
		getMapController().setZoom(zoom);
	}

	public void setCenterGeoPoint(GeoPoint point)
	{
		getMapController().animateTo(point);
	}

	public void enableGPS()
	{
		LocationManager mlocManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
		myLocationListener = new MyLocationListener(mActivity, mapView);
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
	}

	public int getMyLatitude()
	{
		if (myLocationListener == null)
			enableGPS();

		return myLocationListener.getLatitude();
	}

	public int getMyLongitude()
	{
		if (myLocationListener == null)
			enableGPS();

		return myLocationListener.getLongitude();
	}

	public void drawBusStopOverlay()
	{
		myOverlays.drawOverlay(R.drawable.busstop, overlayDao.getByType(MapSettingsDao.BUS_STOP), getMap());
	}

	public void drawRestaurantOverlay()
	{
		myOverlays.drawOverlay(R.drawable.restaurant, overlayDao.getByType(MapSettingsDao.RESTAURANT), getMap());
	}

	public void drawUniversityOverlay()
	{
		myOverlays.drawOverlay(R.drawable.university, overlayDao.getByType(MapSettingsDao.UNIVERSITY), getMap());
	}

	public void drawGPSOverlay()
	{
		if (myLocationListener != null)
			myLocationListener.setOverlay();
	}

	public void clearOverlay()
	{
		getMap().getOverlays().clear();
	}

	public void refreshOverlay()
	{
		getMap().postInvalidate();
	}

}
