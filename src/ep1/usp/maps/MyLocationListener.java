package ep1.usp.maps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import ep1.usp.R;
import ep1.usp.maps.Overlay.OverlayIcon;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener
{

	private double latitude = 0;
	private double longitude = 0;
	private Context mContext;
	private MapView mapView;
	private OverlayIcon itemizedoverlay;

	public MyLocationListener(Context mContext, MapView mapView)
	{
		this.mContext = mContext;
		this.mapView = mapView;
		InitOverlay();
	}

	private void InitOverlay()
	{
		Drawable drawable = mContext.getResources().getDrawable(R.drawable.location);
		itemizedoverlay = new OverlayIcon(drawable, mContext);
	}

	public void setOverlay()
	{
		if (getLatitude() != 0 && getLongitude() != 0)
		{
			List<Overlay> mapOverlays = mapView.getOverlays();
			mapOverlays.remove(itemizedoverlay);

			GeoPoint point = new GeoPoint(getLatitude(), getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, "Meu local", "Meu local");
			itemizedoverlay.clear();
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);

			mapView.postInvalidate();
		}
	}

	@Override
	public void onLocationChanged(Location location)
	{
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		
		setOverlay();
	}

	@Override
	public void onProviderDisabled(String provider)
	{

	}

	@Override
	public void onProviderEnabled(String provider)
	{

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{

	}

	public int getLatitude()
	{
		return (int) (latitude * 1e6);
	}

	public int getLongitude()
	{
		return (int) (longitude * 1e6);
	}

}
