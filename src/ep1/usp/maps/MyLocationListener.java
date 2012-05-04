package ep1.usp.maps;

import java.util.List;

import android.content.Context;
import android.location.Location;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MyLocationListener extends MyLocationOverlay
{

	private double latitude = 0;
	private double longitude = 0;
	private MapView mapView;

	public MyLocationListener(Context mContext, MapView mapView)
	{
		super(mContext, mapView);
		this.mapView = mapView;
		enableCompass();
		enableMyLocation();
		setOverlay();
	}

	public int getLatitude()
	{
		return (int) (latitude * 1e6);
	}

	public int getLongitude()
	{
		return (int) (longitude * 1e6);
	}

	@Override
	public void onLocationChanged(Location location)
	{
		super.onLocationChanged(location);
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		super.onProviderDisabled(provider);
		this.latitude = 0;
		this.longitude = 0;
	}

	public void setOverlay()
	{
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.add(this);
		mapView.postInvalidate();
	}

}
