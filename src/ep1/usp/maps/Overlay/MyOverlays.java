package ep1.usp.maps.Overlay;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.ci.geo.route.Road;
import org.ci.geo.route.RoadProvider;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import ep1.usp.R;
import ep1.usp.access.db.MapSettingsDao;
import ep1.usp.access.db.OverlayDao;
import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsAddOverlay;

public class MyOverlays
{
	private Maps mActivity;
	
	public MyOverlays(Maps mActivity)
	{
		this.mActivity = mActivity;	
	}
	
	private OverlayDao overlayDao;
	public OverlayDao getOverlayDao()
	{
		if (overlayDao == null)
			overlayDao = new OverlayDao(mActivity.getApplicationContext());
		return overlayDao;

	}

	public void showDialogAddOverlay(int latitude, int longitude)
	{
		MapsAddOverlay dialog = new MapsAddOverlay(mActivity);
		dialog.setContentView(R.layout.maps_add_overlay);
		dialog.setTitle("Adicionar Overlay");
		dialog.init(latitude, longitude);
		dialog.show();
	}
	
	private OverlayIcon busStopOverlays = null;
	
	public void refreshBusStopOverlay()
	{
		busStopOverlays = null;
		getBusStopOverlay();
	}
	
	public OverlayIcon getBusStopOverlay()
	{
		if(busStopOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.busstop);
			busStopOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title  = mActivity.getString(R.string.map_settingsBusStop);
			
			ArrayList<OverlayInfo> lstOverlay =  getOverlayDao().getByType(MapSettingsDao.BUS_STOP);

			for (OverlayInfo info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				busStopOverlays.addOverlay(overlayitem);
			}
		}
		return busStopOverlays;		
	}
	
	private OverlayIcon universityOverlays = null;
	
	public void refreshUniversityOverlay()
	{
		universityOverlays = null;
		getUniversityOverlay();
	}
	
	public OverlayIcon getUniversityOverlay()
	{
		if(universityOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.university);
			universityOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title  = mActivity.getString(R.string.map_settingsUniversity);
			
			ArrayList<OverlayInfo> lstOverlay =  getOverlayDao().getByType(MapSettingsDao.UNIVERSITY);

			for (OverlayInfo info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				universityOverlays.addOverlay(overlayitem);
			}
		}
		return universityOverlays;		
	}
	
	private OverlayIcon restaurantOverlays = null;
	
	public void refreshRestaurantOverlay()
	{
		restaurantOverlays = null;
		getRestaurantOverlay();
	}
	
	public OverlayIcon getRestaurantOverlay()
	{
		if(restaurantOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.restaurant);
			restaurantOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title  = mActivity.getString(R.string.map_settingsRestaurant);
			
			ArrayList<OverlayInfo> lstOverlay =  getOverlayDao().getByType(MapSettingsDao.RESTAURANT);
			for (OverlayInfo info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				restaurantOverlays.addOverlay(overlayitem);
			}
		}
		return restaurantOverlays;		
	}
	
	private RouteOverlay route1;	
	public RouteOverlay getRoute1()
	{
		try
		{
			if(route1 == null)
			{
				InputStream is = mActivity.getAssets().open("rota1.xml");
				Road mRoad = RoadProvider.getRoute(is);
				route1 = new RouteOverlay(mRoad, mActivity.getMapsSettings().getMap(), Color.BLUE);
			}
			return route1;
		}
		catch (IOException e)
		{
			return null;
		}
	}

	
	private RouteOverlay route2;
	
	public RouteOverlay getRoute2()
	{
		try
		{
			if(route2 == null)
			{
				InputStream is = mActivity.getAssets().open("rota2.xml");
				Road mRoad = RoadProvider.getRoute(is);
				route2 = new RouteOverlay(mRoad, mActivity.getMapsSettings().getMap(), Color.RED);
			}
			return route2;
		}
		catch (IOException e)
		{
			return null;
		}
	}
}
