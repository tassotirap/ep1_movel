package ep1.usp.restaurant;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWeb extends WebViewClient
{
	private String clearURL;
	public MyWeb(String clearURL)
	{
		this.clearURL = clearURL;		
	}
	
	@Override
	public void onPageFinished(WebView view, String url)
	{		
		view.loadUrl(clearURL);
	}

}
