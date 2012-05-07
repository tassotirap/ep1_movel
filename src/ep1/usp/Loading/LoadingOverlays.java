package ep1.usp.Loading;

import java.util.ArrayList;

import android.app.ProgressDialog;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.maps.Maps;
import ep1.usp.maps.Overlay.OverlayDto;

public class LoadingOverlays extends Loading<Maps>
{
	public LoadingOverlays(Maps mActivity)
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
					ParseJSON parseJSON = new ParseJSON();
					ArrayList<OverlayDto> lstOverlayInfo = parseJSON.getOverlayInfo();

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
					if(progressDialog != null)
						progressDialog.dismiss();
				}
			}
		};

		thread = new Thread(runnable);
		thread.start();
	}
}
