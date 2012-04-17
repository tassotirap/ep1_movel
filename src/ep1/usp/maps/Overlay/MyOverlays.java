package ep1.usp.maps.Overlay;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import ep1.usp.R;
import ep1.usp.maps.MapsAddOverlay;

public class MyOverlays
{
	private Activity mActivity;
	
	public MyOverlays(Activity mActivity)
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
		OverlayIcon itemizedoverlay = new OverlayIcon(drawable, mActivity);

		for (OverlayInfo info : lstOverlay)
		{
			GeoPoint point = new GeoPoint(info.getLatitude(), info.getLongitude());
			OverlayItem overlayitem = new OverlayItem(point, info.getName(), info.getName());
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
		}
	}	
}
