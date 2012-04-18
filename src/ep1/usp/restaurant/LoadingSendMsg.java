package ep1.usp.restaurant;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingSendMsg
{
	private ProgressDialog progressDialog;
	private Restaurant mActivity;
	private int restaurantId;
	private String comment;
	private int statusId;
	
	public LoadingSendMsg(Restaurant mActivity, int restaurantId, String comment, int statusId)
	{
		this.mActivity = mActivity;
		this.restaurantId = restaurantId;
		this.comment = comment;
		this.statusId = statusId;
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

					new ParseJSON().setRestaurantComment(restaurantId, comment, statusId);
					mActivity.handler.sendEmptyMessage(2);
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
