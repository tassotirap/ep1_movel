package ep1.usp.maps;
import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.maps.Overlay.OverlayInfo;


public class LoadingOverlays
{
	private ProgressDialog progressDialog;
	private MapsMenu mActivity;
	
	public LoadingOverlays(MapsMenu mActivity)
	{
		this.mActivity = mActivity;
	}

	public void Show()
	{
		progressDialog = ProgressDialog.show(mActivity.getRoot(), mActivity.getRoot().getString(R.string.msgLoadingTitle), mActivity.getRoot().getString(R.string.msgLoadingMsg), true, false);
		Runnable runnable = new Runnable()
		{
			public void run()
			{
				try
				{

					ParseJSON parseJSON = new ParseJSON();
					ArrayList<OverlayInfo> lstOverlayInfo = parseJSON.getOverlayInfo();

					if (lstOverlayInfo.size() > 0)
					{
						mActivity.getOverlayDao().clear();
						mActivity.getOverlayDao().setList(lstOverlayInfo);
						mActivity.drawOverlays();
					}
					else
					{
						throw new Exception();
					}
				}
				catch (Exception e)
				{
					mActivity.sendHandlerMessage(0);
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
