package ep1.usp.maps.Overlay;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class OverlayIcon extends ItemizedOverlay<OverlayItem>
{

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public OverlayIcon(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));
	}

	public OverlayIcon(Drawable defaultMarker, Context context, int width, int height, Boolean center)
	{
		super(boundCenterBottom(defaultMarker));
		if(center)
			defaultMarker.setBounds(-width,-height, width, height);
		else
			defaultMarker.setBounds(0,0, width,height);			
		mContext = context;
	}
	
	@Override
	public void draw( Canvas c, MapView m, boolean shadow ) {
		super.draw( c, m, false );
		
		
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		return mOverlays.get(i);
	}

	@Override
	public int size()
	{
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay)
	{
		mOverlays.add(overlay);
		populate();
	}
	
	public void clear()
	{
		mOverlays.clear();
		populate();
	}

	@Override
	protected boolean onTap(int index)
	{
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
}
