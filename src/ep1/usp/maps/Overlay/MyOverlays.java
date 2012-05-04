package ep1.usp.maps.Overlay;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.ci.geo.route.Road;
import org.ci.geo.route.RoadProvider;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import ep1.usp.R;
import ep1.usp.access.db.MapSettingsDao;
import ep1.usp.access.db.OverlayDao;
import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsAddOverlay;

public class MyOverlays
{
	private Maps mActivity;
	private OverlayIcon universityOverlays = null;
	private OverlayIcon busStopOverlays = null;
	private OverlayIcon restaurantOverlays = null;
	private RouteOverlay route1 = null;
	private RouteOverlay route2 = null;

	private OverlayDao overlayDao = null;

	public MyOverlays(Maps mActivity)
	{
		this.mActivity = mActivity;
	}

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

	public void refreshBusStopOverlay()
	{
		busStopOverlays = null;
		getBusStopOverlay();
	}

	public OverlayIcon getBusStopOverlay()
	{
		if (busStopOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.busstop);
			busStopOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title = mActivity.getString(R.string.map_lblBusStop);

			ArrayList<OverlayDto> lstOverlay = getOverlayDao().getByType(MapSettingsDao.BUS_STOP);

			for (OverlayDto info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				busStopOverlays.addOverlay(overlayitem);
			}
		}
		return busStopOverlays;
	}

	public void refreshUniversityOverlay()
	{
		universityOverlays = null;
		getUniversityOverlay();
	}

	public OverlayIcon getUniversityOverlay()
	{
		if (universityOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.university);
			universityOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title = mActivity.getString(R.string.map_lblUniversity);

			ArrayList<OverlayDto> lstOverlay = getOverlayDao().getByType(MapSettingsDao.UNIVERSITY);

			for (OverlayDto info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				universityOverlays.addOverlay(overlayitem);
			}
		}
		return universityOverlays;
	}

	public void refreshRestaurantOverlay()
	{
		restaurantOverlays = null;
		getRestaurantOverlay();
	}

	public OverlayIcon getRestaurantOverlay()
	{
		if (restaurantOverlays == null)
		{
			Drawable drawable = mActivity.getResources().getDrawable(R.drawable.restaurant);
			restaurantOverlays = new OverlayIcon(drawable, mActivity, 11, 22, true);
			String title = mActivity.getString(R.string.map_lblRestaurant);

			ArrayList<OverlayDto> lstOverlay = getOverlayDao().getByType(MapSettingsDao.RESTAURANT);
			for (OverlayDto info : lstOverlay)
			{
				GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
				OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
				restaurantOverlays.addOverlay(overlayitem);
			}
		}
		return restaurantOverlays;
	}

	public RouteOverlay getRoute1()
	{
		try
		{
			if (route1 == null)
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

	public RouteOverlay getRoute2()
	{
		try
		{
			if (route2 == null)
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
