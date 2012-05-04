package ep1.usp.maps;

import android.view.View;

import com.google.android.maps.GeoPoint;

import ep1.usp.R;

public class MapsMenu
{
	private Maps mActivity;

	public MapsMenu(Maps mActivity)
	{
		this.mActivity = mActivity;
	}

	private void onBusStopClick()
	{
		if (mActivity.getMapsMenusButtons().getShowBusStop())
		{
			mActivity.getMapsMenusButtons().setShowBusStop(false);
			mActivity.getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		}
		else
		{
			mActivity.getMapsMenusButtons().setShowBusStop(true);
			mActivity.getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getBusStopOverlay());
		}

		mActivity.getMapsMenusButtons().getMapSettingsDao().setBusStopEnable(mActivity.getMapsMenusButtons().getShowBusStop());

	}

	private void onMyLocationClick()
	{
		if (mActivity.getMyLocation().getLatitude() != 0 && mActivity.getMyLocation().getLongitude() != 0)
		{
			GeoPoint geoPointCenter = new GeoPoint(mActivity.getMyLocation().getLatitude(), mActivity.getMyLocation().getLongitude());
			mActivity.getMapsSettings().getMapController().animateTo(geoPointCenter);
		}
		else
			mActivity.showDialog(mActivity.getString(R.string.msgErrorTitle), mActivity.getString(R.string.msgLocalErrorMsg));
	}

	private void onRefreshOverlayClick()
	{
		LoadingOverlays loading = new LoadingOverlays(mActivity);
		loading.Show();
	}

	private void onRestaurantClick()
	{
		if (mActivity.getMapsMenusButtons().getShowRestaurant())
		{
			mActivity.getMapsMenusButtons().setShowRestaurant(false);
			mActivity.getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		}
		else
		{
			mActivity.getMapsMenusButtons().setShowRestaurant(true);
			mActivity.getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getRestaurantOverlay());
		}

		mActivity.getMapsMenusButtons().getMapSettingsDao().setRestaurantEnable(mActivity.getMapsMenusButtons().getShowRestaurant());
	}

	private void onRoute1Click()
	{
		if (mActivity.getMapsMenusButtons().getShowRoute1())
		{
			mActivity.getMapsMenusButtons().setShowRoute1(false);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRoute1());
		}
		else
		{
			mActivity.getMapsMenusButtons().setShowRoute1(true);
			mActivity.getMapsSettings().addMapOverlayFirst(mActivity.getMyOverlays().getRoute1());
		}

		mActivity.getMapsMenusButtons().getMapSettingsDao().setRoute1Enable(mActivity.getMapsMenusButtons().getShowRoute1());
	}

	private void onRoute2Click()
	{
		if (mActivity.getMapsMenusButtons().getShowRoute2())
		{
			mActivity.getMapsMenusButtons().setShowRoute2(false);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getRoute2());
		}
		else
		{
			mActivity.getMapsMenusButtons().setShowRoute2(true);
			mActivity.getMapsSettings().addMapOverlayFirst(mActivity.getMyOverlays().getRoute2());
		}

		mActivity.getMapsMenusButtons().getMapSettingsDao().setRoute2Enable(mActivity.getMapsMenusButtons().getShowRoute2());
	}

	private void onUniversityClick()
	{
		if (mActivity.getMapsMenusButtons().getShowUniversity())
		{
			mActivity.getMapsMenusButtons().setShowUniversity(false);
			mActivity.getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_disable);
			mActivity.getMapsSettings().removeMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());

		}
		else
		{
			mActivity.getMapsMenusButtons().setShowUniversity(true);
			mActivity.getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_enable);
			mActivity.getMapsSettings().addMapOverlay(mActivity.getMyOverlays().getUniversityOverlay());
		}

		mActivity.getMapsMenusButtons().getMapSettingsDao().setUniversityEnable(mActivity.getMapsMenusButtons().getShowUniversity());
	}

	private void setBinds()
	{
		mActivity.getMapsMenusButtons().getBtnRefresh().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onRefreshOverlayClick();
			}
		});

		mActivity.getMapsMenusButtons().getBtnUSPCenter().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.getMapsSettings().resetMaps();
			}
		});

		mActivity.getMapsMenusButtons().getBtnBusStop().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBusStopClick();
			}

		});

		mActivity.getMapsMenusButtons().getBtnRestaurant().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onRestaurantClick();
			}

		});

		mActivity.getMapsMenusButtons().getBtnUniversity().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onUniversityClick();
			}

		});

		mActivity.getMapsMenusButtons().getBtnRoute1().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onRoute1Click();
			}

		});

		mActivity.getMapsMenusButtons().getBtnRoute2().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onRoute2Click();
			}

		});

		mActivity.getMapsMenusButtons().getBtnMyLocation().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onMyLocationClick();
			}
		});

		mActivity.getMapsMenusButtons().getBtnAddOverlay().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mActivity.getMyLocation().getLatitude() != 0 && mActivity.getMyLocation().getLongitude() != 0)
				{
					mActivity.getMyOverlays().showDialogAddOverlay(mActivity.getMyLocation().getLatitude(), mActivity.getMyLocation().getLongitude());
				}
				else
					mActivity.showDialog(mActivity.getString(R.string.msgErrorTitle), mActivity.getString(R.string.msgLocalErrorMsg));
			}
		});
	}

	public void init()
	{
		setBinds();
		setButtons();
	}

	public void setButtons()
	{

		if (mActivity.getMapsMenusButtons().getShowBusStop())
		{
			mActivity.getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_enable);
		}
		else
		{
			mActivity.getMapsMenusButtons().getBtnBusStop().setImageResource(R.drawable.busstop_disable);
		}

		if (mActivity.getMapsMenusButtons().getShowRestaurant())
		{
			mActivity.getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_enable);
		}
		else
		{
			mActivity.getMapsMenusButtons().getBtnRestaurant().setImageResource(R.drawable.restaurant_disable);
		}

		if (mActivity.getMapsMenusButtons().getShowUniversity())
		{
			mActivity.getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_enable);
		}
		else
		{
			mActivity.getMapsMenusButtons().getBtnUniversity().setImageResource(R.drawable.university_disable);
		}
	}

}
