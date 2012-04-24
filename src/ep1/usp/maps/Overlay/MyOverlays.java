package ep1.usp.maps.Overlay;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import ep1.usp.R;
import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsAddOverlay;

public class MyOverlays
{
	private Maps mActivity;
	
	public MyOverlays(Maps mActivity)
	{
		this.mActivity = mActivity;	
	}

	public void showDialogAddOverlay(int latitude, int longitude)
	{
		MapsAddOverlay dialog = new MapsAddOverlay(mActivity);
		dialog.setContentView(R.layout.maps_add_overlay);
		dialog.setTitle("Adicionar Overlay");
		dialog.init(latitude, longitude);
		dialog.show();
	}
	
	public void drawOverlay(int id, ArrayList<OverlayInfo> lstOverlay, MapView mapView)
	{
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = mActivity.getResources().getDrawable(id);
		OverlayIcon itemizedoverlay = new OverlayIcon(drawable, mActivity, 11, 22, true);
		String title = null;
		
		switch (id)
		{
			case R.drawable.busstop:
				title = mActivity.getString(R.string.map_settingsBusStop);
				break;
			case R.drawable.restaurant:
				title = mActivity.getString(R.string.map_settingsRestaurant);
				break;
			case R.drawable.university:
				title = mActivity.getString(R.string.map_settingsUniversity);
				break;
		}
		

		for (OverlayInfo info : lstOverlay)
		{
			GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, title, info.getName());
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
		}
	}	
}
