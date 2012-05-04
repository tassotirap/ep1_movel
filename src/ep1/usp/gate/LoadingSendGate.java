package ep1.usp.gate;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingSendGate
{
	private ProgressDialog progressDialog;
	private Gate mActivity;
	private double dist;
	private int gateId;

	public LoadingSendGate(Gate mActivity, int gateId, double dist)
	{
		this.mActivity = mActivity;
		this.gateId = gateId;
		this.dist = dist;
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
					new ParseJSON().setGates(gateId, dist);
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
