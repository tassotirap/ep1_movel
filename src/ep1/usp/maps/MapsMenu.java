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
				mActivity.getMapsMenusButtons().onBusStopClick();
			}

		});

		mActivity.getMapsMenusButtons().getBtnRestaurant().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.getMapsMenusButtons().onRestaurantClick();
			}

		});

		mActivity.getMapsMenusButtons().getBtnUniversity().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.getMapsMenusButtons().onUniversityClick();
			}

		});
		
		mActivity.getMapsMenusButtons().getBtnRoute1().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.getMapsMenusButtons().onRoute1Click();
			}

		});		
		
		mActivity.getMapsMenusButtons().getBtnRoute2().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.getMapsMenusButtons().onRoute2Click();
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
}
