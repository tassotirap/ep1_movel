package ep1.usp.Loading;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.restaurant.MessageDto;
import ep1.usp.restaurant.Restaurant;
import ep1.usp.restaurant.RestaurantDto;

public class LoadingGetMsg extends Loading<Restaurant>
{
	private int restaurantId;
	
	public LoadingGetMsg(Restaurant mActivity, int restaurantId)
	{
		super(mActivity);
		this.restaurantId = restaurantId;
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
					SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getBaseContext());
					String minutosRestaurant = prefs.getString("listRestauratMin", "5");
					String qtdeRestaurant = prefs.getString("listRestauratqtde", "5");
					ArrayList<MessageDto> msgs = new ParseJSON().getRestaurantComment(restaurantId, qtdeRestaurant);
					RestaurantDto restaurantInfo = new ParseJSON().getRestaurant(restaurantId, minutosRestaurant);					
					mActivity.getRestaurantCommentDao().clear();
					mActivity.getRestaurantCommentDao().setList(msgs);
					mActivity.getRestaurantsDao().update(restaurantInfo);
					mActivity.handler.sendEmptyMessage(3);
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
		mActivity.handler.post(runnable);
	}

}
