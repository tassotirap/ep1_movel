package ep1.usp.gate;

import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class LoadingGetGate
{
	private ProgressDialog progressDialog;
	private Gate mActivity;
	
	public LoadingGetGate(Gate mActivity)
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
					ArrayList<GateInfo> gates = parseJSON.getGates();
					mActivity.getGatesDao().setList(gates);
					mActivity.handler.sendEmptyMessage(1);
					
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