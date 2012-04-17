package ep1.usp.maps;

import ep1.usp.R;
import ep1.usp.access.db.MapSettingsDao;
import android.app.Activity;
import android.widget.ImageButton;

public class MapsMenusButtons
{
	private ImageButton btnRefresh = null;
	private ImageButton btnUSPCenter = null;
	private ImageButton btnBusStop = null;
	private ImageButton btnRestaurant = null;
	private ImageButton btnUniversity = null;
	private ImageButton btnMyLocation = null;
	private ImageButton btnAddOverlay = null;
	private Activity mActivity = null;

	private Boolean showBusStop = null;
	private Boolean showRestaurant = null;
	private Boolean showUniversity = null;

	private MapSettingsDao mapSettingsDao;

	public MapSettingsDao getMapSettingsDao()
	{
		if (mapSettingsDao == null)
			mapSettingsDao = new MapSettingsDao(mActivity.getApplicationContext());
		return mapSettingsDao;
	}

	public MapsMenusButtons(Activity mActivity)
	{
		this.mActivity = mActivity;
	}

	public ImageButton getBtnRefresh()
	{
		if (btnRefresh == null)
			btnRefresh = (ImageButton) mActivity.findViewById(R.id.map_btnRefresh);
		return btnRefresh;
	}

	public ImageButton getBtnUSPCenter()
	{
		if (btnUSPCenter == null)
			btnUSPCenter = (ImageButton) mActivity.findViewById(R.id.map_btnUSPCenter);
		return btnUSPCenter;
	}

	public ImageButton getBtnBusStop()
	{
		if (btnBusStop == null)
			btnBusStop = (ImageButton) mActivity.findViewById(R.id.map_btnBusStop);
		return btnBusStop;
	}

	public ImageButton getBtnRestaurant()
	{
		if (btnRestaurant == null)
			btnRestaurant = (ImageButton) mActivity.findViewById(R.id.map_btnRestaurant);
		return btnRestaurant;
	}

	public ImageButton getBtnUniversity()
	{
		if (btnUniversity == null)
			btnUniversity = (ImageButton) mActivity.findViewById(R.id.map_btnUniversity);
		return btnUniversity;
	}

	public ImageButton getBtnMyLocation()
	{
		if (btnMyLocation == null)
			btnMyLocation = (ImageButton) mActivity.findViewById(R.id.map_btnMyLocation);
		return btnMyLocation;
	}

	public ImageButton getBtnAddOverlay()
	{
		if (btnAddOverlay == null)
			btnAddOverlay = (ImageButton) mActivity.findViewById(R.id.map_btnAddOverlay);
		return btnAddOverlay;
	}

	public boolean getShowBusStop()
	{
		if (showBusStop == null)
			showBusStop = getMapSettingsDao().getBusStopEnable();

		return showBusStop;
	}

	public boolean getShowRestaurant()
	{
		if (showRestaurant == null)
			showRestaurant = getMapSettingsDao().getRestaurantEnable();

		return showRestaurant;
	}

	public boolean getShowUniversity()
	{
		if (showUniversity == null)
			showUniversity = getMapSettingsDao().getUniversityEnable();

		return showUniversity;
	}

	public void setShowBusStop(boolean value)
	{
		showBusStop = value;
	}

	public void setShowRestaurant(boolean value)
	{
		showRestaurant = value;
	}

	public void setShowUniversity(boolean value)
	{
		showUniversity = value;
	}
	
	public void onBusStopClick()
	{
		if (getShowBusStop())
		{
			setShowBusStop(false);
			getBtnBusStop().setImageResource(R.drawable.busstop_disable);
		}
		else
		{
			setShowBusStop(true);
			getBtnBusStop().setImageResource(R.drawable.busstop_enable);
		}

		getMapSettingsDao().setBusStopEnable(getShowBusStop());
		
	}

	public void onRestaurantClick()
	{
		if (getShowRestaurant())
		{
			setShowRestaurant(false);
			getBtnRestaurant().setImageResource(R.drawable.restaurant_disable);
		}
		else
		{
			setShowRestaurant(true);
			getBtnRestaurant().setImageResource(R.drawable.restaurant_enable);
		}

		getMapSettingsDao().setRestaurantEnable(getShowRestaurant());
	}

	public void onUniversityClick()
	{
		if (getShowUniversity())
		{
			setShowUniversity(false);
			getBtnUniversity().setImageResource(R.drawable.university_disable);
		}
		else
		{
			setShowUniversity(true);
			getBtnUniversity().setImageResource(R.drawable.university_enable);
		}

		getMapSettingsDao().setUniversityEnable(getShowUniversity());
	}

}
