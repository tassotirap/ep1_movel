package ep1.usp.maps;

import android.view.View;

import com.google.android.maps.GeoPoint;

import ep1.usp.R;
import ep1.usp.maps.Overlay.MyOverlays;

public class MapsMenu
{
	
	
	private MyOverlays myOverlays;
	public MyOverlays getMyOverlays()
	{
		if(myOverlays == null)
			myOverlays = new MyOverlays(mActivity);
		return myOverlays;			
	}

	private MapsSettings mapsSettings = null;
	public MapsSettings getMapsSettings()
	{
		if(mapsSettings == null)
			mapsSettings = new MapsSettings(mActivity);
		return mapsSettings;				
	}
	
	private MapsMenusButtons mapsMenusButtons;
	public MapsMenusButtons getMapsMenusButtons()
	{
		if(mapsMenusButtons == null)
			mapsMenusButtons = new MapsMenusButtons(mActivity);
		return mapsMenusButtons;
		
	}
	
	private Maps mActivity;
	public MapsMenu(Maps mActivity)
	{
		this.mActivity = mActivity;		
		this.init();		
	}
	
	public void init()
	{
		setBinds();	
		setButtons();
	}
	
	private void setBinds()
	{
		getMapsMenusButtons().getBtnRefresh().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				refreshOverlayClick();
			}
		});

		getMapsMenusButtons().getBtnUSPCenter().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mapsSettings.resetMaps();
			}
		});

		getMapsMenusButtons().getBtnBusStop().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				getMapsMenusButtons().onBusStopClick();
				drawOverlays();
			}

		});

		getMapsMenusButtons().getBtnRestaurant().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				getMapsMenusButtons().onRestaurantClick();
				drawOverlays();
			}

		});

		getMapsMenusButtons().getBtnUniversity().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				getMapsMenusButtons().onUniversityClick();
				drawOverlays();
			}

		});

		getMapsMenusButtons().getBtnMyLocation().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				myLocationClick();
			}
		});

		getMapsMenusButtons().getBtnAddOverlay().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (getMapsSettings().getMyLatitude() != 0 && getMapsSettings().getMyLongitude() != 0)
				{
					getMyOverlays().showDialogAddOverlay(getMapsSettings().getMyLatitude(), getMapsSettings().getMyLongitude());
				}
				else
					mActivity.showDialog(mActivity.getString(R.string.msgErrorTitle), mActivity.getString(R.string.msgLocalErrorMsg));
			}
		});
	}	

	private void myLocationClick()
	{
		if (getMapsSettings().getMyLatitude() != 0 && getMapsSettings().getMyLongitude()!= 0)
		{
			GeoPoint geoPointCenter = new GeoPoint(getMapsSettings().getMyLatitude(), getMapsSettings().getMyLongitude());
			getMapsSettings().getMapController().animateTo(geoPointCenter);
		}
		else
			mActivity.showDialog(mActivity.getString(R.string.msgErrorTitle), mActivity.getString(R.string.msgLocalErrorMsg));
	}
	
	private void refreshOverlayClick()
	{
		LoadingOverlays loading = new LoadingOverlays(mActivity);
		loading.Show();		
	}
	
	public void sendHandlerMessage(int what)
	{
		this.mActivity.handler.sendEmptyMessage(what);		
	}
	
	public void setButtons()
	{

		if (getMapsMenusButtons().getShowBusStop())
		{
			getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_enable);
		}
		else
		{
			getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_disable);
		}

		if (getMapsMenusButtons().getShowRestaurant())
		{
			getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_enable);
		}
		else
		{
			getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_disable);
		}

		if (getMapsMenusButtons().getShowUniversity())
		{
			getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_enable);
		}
		else
		{
			getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_disable);
		}
	}
	
	public void drawOverlays()
	{
		getMapsSettings().clearOverlay();

		if (getMapsMenusButtons().getShowBusStop())
		{
			getMapsSettings().drawBusStopOverlay();
		}

		if (getMapsMenusButtons().getShowRestaurant())
		{
			getMapsSettings().drawRestaurantOverlay();
		}

		if (getMapsMenusButtons().getShowUniversity())
		{
			getMapsSettings().drawUniversityOverlay();
		}

		getMapsSettings().drawGPSOverlay();
		
		getMapsSettings().refreshOverlay();
	}
}
