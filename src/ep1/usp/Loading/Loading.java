package ep1.usp.Loading;

import android.app.ProgressDialog;

public abstract class Loading<T>
{
	protected ProgressDialog progressDialog;
	protected T mActivity;
	
	public Loading(T mActivity)
	{
		this.mActivity = mActivity;
	}
	
	public abstract void show();
}
