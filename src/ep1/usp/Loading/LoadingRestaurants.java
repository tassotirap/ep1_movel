package ep1.usp.Loading;

import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.restaurant.Restaurant;
import ep1.usp.restaurant.RestaurantDto;

public class LoadingRestaurants extends Loading<Restaurant>
{	
	public LoadingRestaurants(Restaurant mActivity)
	{
		super(mActivity);
	}

	public void show()
	{
		progressDialog = ProgressDialog.show(mActivity, mActivity.getString(R.string.msgLoadingTitle), mActivity.getString(R.string.msgLoadingMsg), true, false);
		Runnable runnable = new Runnable()
		{
			public void run()
			{
				try
				{

					ParseJSON parseJSON = new ParseJSON();
					ArrayList<RestaurantDto> lstRestaurantInfo = parseJSON.getRestaurantInfo();

					if (lstRestaurantInfo.size() > 0)
					{
						mActivity.getRestaurantsDao().clear();
						mActivity.getRestaurantsDao().setList(lstRestaurantInfo);
						mActivity.handler.sendEmptyMessage(1);
					}
					else
					{
						throw new Exception();
					}
				}
				catch (Exception e)
				{
					mActivity.handler.sendEmptyMessage(0);
				}
				finally
				{
					progressDialog.dismiss();
				}
			}
		};

		thread = new Thread(runnable);
		thread.start();
	}
	

}
