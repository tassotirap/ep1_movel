package ep1.usp.maps;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingAddOverlay
{
	private ProgressDialog progressDialog;
	private Maps mActivity;
	private int latitude;
	private int longitude;
	private String name;
	private int type;
	private MapsAddOverlay dialog;

	public LoadingAddOverlay(Maps mActivity, int latitude, int longitude, String name, int type, MapsAddOverlay dialog)
	{
		this.dialog = dialog;
		this.name = name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
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
					parseJSON.setOverlay(latitude, longitude, name, type);
					dialog.handler.sendEmptyMessage(0);
					mActivity.handler.sendEmptyMessage(1);

				}
				catch (Exception e)
				{
					mActivity.handler.sendEmptyMessage(0);
					dialog.handler.sendEmptyMessage(0);
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
