package ep1.usp.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import ep1.usp.R;
import ep1.usp.access.db.SettingsDao;
import ep1.usp.maps.Overlay.OverlayIcon;

public class MapsSettings
{
	private Maps mActivity;
	private SettingsDao settingsDao;

	public MapsSettings(Maps mActivity)
	{
		this.mActivity = mActivity;
		this.settingsDao = new SettingsDao(mActivity.getApplicationContext());
	}

	public void init()
	{
		getMap().setBuiltInZoomControls(true);
		resetMaps();
		mActivity.getMyLocation();
	}

	public void resetMaps()
	{
		setZoom(15);
		setCenterGeoPoint(settingsDao.GetUspCenter());
	}

	private MapController mapController = null;

	public MapController getMapController()
	{
		if (mapController == null)
		{
			mapController = getMap().getController();
		}

		return mapController;
	}

	private MapView mapView = null;

	public MapView getMap()
	{
		if (mapView == null)
		{
			mapView = (MapView) mActivity.findViewById(R.id.mapview);
			mapView.setBuiltInZoomControls(true);
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

	public void removeMapOverlay(Overlay object)
	{
		getMap().getOverlays().remove(object);
		refreshOverlay();
	}

	public void addMapOverlay(Overlay object)
	{
		getMap().getOverlays().add(object);
		refreshOverlay();
	}
	
	public void addMapOverlayFirst(Overlay object)
	{
		getMap().getOverlays().add(0, object);
		refreshOverlay();
	}

	public void addMapOverlay(OverlayIcon object)
	{
		if (object.size() > 0)
		{
			getMap().getOverlays().add(object);
			refreshOverlay();
		}
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
