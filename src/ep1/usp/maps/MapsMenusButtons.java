package ep1.usp.maps;

import android.widget.Button;
import android.widget.ImageButton;
import ep1.usp.R;
import ep1.usp.access.db.MapSettingsDao;
import ep1.usp.maps.Overlay.MyOverlays;

public class MapsMenusButtons
{
	private ImageButton btnRefresh = null;
	private ImageButton btnUSPCenter = null;
	private ImageButton btnBusStop = null;
	private ImageButton btnRestaurant = null;
	private ImageButton btnUniversity = null;
	private ImageButton btnMyLocation = null;
	private ImageButton btnAddOverlay = null;
	private Button btnRoute1 = null;
	private Button btnRoute2 = null;
	private Maps mActivity = null;
	private Boolean showBusStop = null;
	private Boolean showRestaurant = null;
	private Boolean showUniversity = null;
	private Boolean route1 = null;
	private Boolean route2 = null;

	private MapSettingsDao mapSettingsDao;

	public MapSettingsDao getMapSettingsDao()
	{
		if (mapSettingsDao == null)
			mapSettingsDao = new MapSettingsDao(mActivity.getApplicationContext());
		return mapSettingsDao;
	}

	public MapsMenusButtons(Maps mActivity)
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

	public Button getBtnRoute1()
	{
		if (btnRoute1 == null)
			btnRoute1 = (Button) mActivity.findViewById(R.id.map_btnRoute1);
		return btnRoute1;
	}

	public Button getBtnRoute2()
	{
		if (btnRoute2 == null)
			btnRoute2 = (Button) mActivity.findViewById(R.id.map_btnRout2);
		return btnRoute2;
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

	public boolean getShowRoute1()
	{
		if (route1 == null)
			route1 = getMapSettingsDao().getRoute1Enable();

		return route1;
	}

	public boolean getShowRoute2()
	{
		if (route2 == null)
			route2 = getMapSettingsDao().getRoute1Enable();
		return route2;
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

	public void setShowRoute1(boolean value)
	{
		route1 = value;
	}

	public void setShowRoute2(boolean value)
	{
		route2 = value;
	}

	public void removeOverlays()
	{
		mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());
	}

	public void initDraw()
	{
		removeOverlays();
		if (getShowBusStop())
		{
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		}
		if (getShowRestaurant())
		{

			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		}
		if (getShowUniversity())
		{

			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());
		}
	}

	public void onBusStopClick()
	{
		if (getShowBusStop())
		{
			setShowBusStop(false);
			getBtnBusStop().setImageResource(R.drawable.busstop_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		}
		else
		{
			setShowBusStop(true);
			getBtnBusStop().setImageResource(R.drawable.busstop_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		}

		getMapSettingsDao().setBusStopEnable(getShowBusStop());

	}

	public void onRestaurantClick()
	{
		if (getShowRestaurant())
		{
			setShowRestaurant(false);
			getBtnRestaurant().setImageResource(R.drawable.restaurant_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		}
		else
		{
			setShowRestaurant(true);
			getBtnRestaurant().setImageResource(R.drawable.restaurant_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		}

		getMapSettingsDao().setRestaurantEnable(getShowRestaurant());
	}

	public void onUniversityClick()
	{
		if (getShowUniversity())
		{
			setShowUniversity(false);
			getBtnUniversity().setImageResource(R.drawable.university_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());

		}
		else
		{
			setShowUniversity(true);
			getBtnUniversity().setImageResource(R.drawable.university_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());
		}

		getMapSettingsDao().setUniversityEnable(getShowUniversity());
	}

	public void onRoute1Click()
	{
		if (getShowRoute1())
		{
			setShowRoute1(false);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRoute1());
		}
		else
		{
			setShowRoute1(true);
			mActivity.getMapsSettings().addMapOverlayFirst(mActivity.getMyOverlays().getRoute1());
		}

		getMapSettingsDao().setRoute1Enable(getShowRoute1());
	}

	public void onRoute2Click()
	{
		if (getShowRoute2())
		{
			setShowRoute2(false);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRoute2());
		}
		else
		{
			setShowRoute2(true);
			mActivity.getMapsSettings().addMapOverlayFirst(mActivity.getMyOverlays().getRoute2());
		}

		getMapSettingsDao().setRoute1Enable(getShowRoute2());
	}

}
