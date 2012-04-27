package ep1.usp.maps;
import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.maps.Overlay.OverlayInfo;


public class LoadingOverlays
{
	private ProgressDialog progressDialog;
	private Maps mActivity;
	
	public LoadingOverlays(Maps mActivity)
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
					ArrayList<OverlayInfo> lstOverlayInfo = parseJSON.getOverlayInfo();

					if (lstOverlayInfo.size() > 0)
					{
						mActivity.getOverlayDao().clear();
						mActivity.getOverlayDao().setList(lstOverlayInfo);
						mActivity.getMapsMenusButtons().removeOverlays();
						mActivity.getMyOverlays().refreshBusStopOverlay();
						mActivity.getMyOverlays().refreshRestaurantOverlay();
						mActivity.getMyOverlays().refreshUniversityOverlay();
						mActivity.getMapsMenusButtons().initDraw();
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
