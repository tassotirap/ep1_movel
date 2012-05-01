package ep1.usp.restaurant;

import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingGetMsg
{
	private ProgressDialog progressDialog;
	private Restaurant mActivity;
	private int restaurantId;
	
	public LoadingGetMsg(Restaurant mActivity, int restaurantId)
	{
		this.mActivity = mActivity;
		this.restaurantId = restaurantId;
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
					ArrayList<MessageInfo> msgs = new ParseJSON().getRestaurantComment(restaurantId, 10);
					RestaurantInfo restaurantInfo = new ParseJSON().getRestaurant(restaurantId);					
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

		Thread t = new Thread(runnable);
		t.start();
	}

}
