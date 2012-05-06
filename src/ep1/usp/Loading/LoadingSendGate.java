package ep1.usp.Loading;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.gate.Gate;

public class LoadingSendGate extends Loading<Gate>
{
	private double dist;
	private int gateId;

	public LoadingSendGate(Gate mActivity, int gateId, double dist)
	{
		super(mActivity);
		this.gateId = gateId;
		this.dist = dist;
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

		thread = new Thread(runnable);
		thread.start();
	}
}
