package ep1.usp.restaurant;

import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingRestaurants
{
	private ProgressDialog progressDialog;
	private Restaurant mActivity;
	
	public LoadingRestaurants(Restaurant mActivity)
	{
		this.mActivity = mActivity;
	}

	public void Show()
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

		Thread t = new Thread(runnable);
		t.start();
	}
	

}
