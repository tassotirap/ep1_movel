package ep1.usp.restaurant;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import ep1.usp.R;
import ep1.usp.access.db.RestaurantsDao;

public class RestaurantMenu extends Activity
{
	private WebView myWebView;
	private int restaurantId;
	private RestaurantsDao restaurantsDao = null;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_menu);

		Bundle bundle = this.getIntent().getExtras();

		restaurantId = bundle.getInt("restaurantId");

		myWebView = (WebView) findViewById(R.id.webviewMenu);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setBuiltInZoomControls(true);
		myWebView.getSettings().setUseWideViewPort(true);

		loadWeb(restaurantId);
	}

	private void loadWeb(int id)
	{
		RestaurantInfo restaurant = getRestaurantsDao().get(id);
		if (restaurant != null)
		{
			myWebView.setWebViewClient(new MyWeb(restaurant.getClearUrl()));
			myWebView.loadUrl(restaurant.getUrl());
		}

	}

	public RestaurantsDao getRestaurantsDao()
	{
		if (restaurantsDao == null)
			restaurantsDao = new RestaurantsDao(getApplicationContext());
		return restaurantsDao;
	}

}
