package ep1.usp.Loading;

import android.app.ProgressDialog;

public abstract class Loading<T>
{
	protected ProgressDialog progressDialog;
	protected T mActivity;
	protected Thread thread;
	
	public Loading(T mActivity)
	{
		this.mActivity = mActivity;
	}
	
	public abstract void show();

	public Thread getThread()
	{
		return thread;
	}
}
