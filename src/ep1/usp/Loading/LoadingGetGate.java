package ep1.usp.Loading;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.gate.Gate;
import ep1.usp.gate.GateDto;

public class LoadingGetGate extends Loading<Gate>
{	
	public LoadingGetGate(Gate mActivity)
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
					SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getBaseContext());
					String minutosGate = prefs.getString("lstGateMin", "5");
					ParseJSON parseJSON = new ParseJSON();
					ArrayList<GateDto> gates = parseJSON.getGates(minutosGate);
					mActivity.getGatesDao().clear();
					mActivity.getGatesDao().setList(gates);
					mActivity.handler.sendEmptyMessage(1);		
				}
				catch (Exception e)
				{
					mActivity.handler.sendEmptyMessage(0);
				}
				finally
				{
					if(progressDialog != null)
						progressDialog.dismiss();
				}
			}
		};

		thread = new Thread(runnable);
		thread.start();
	}

}
