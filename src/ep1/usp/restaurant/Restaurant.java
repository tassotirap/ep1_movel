package ep1.usp.restaurant;

import java.util.ArrayList;

import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;
import ep1.usp.access.db.RestaurantsDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Restaurant extends Activity
{

	protected Spinner spnIds, spnStatus;
	protected ArrayAdapter<String> mAdapterIds;
	protected ArrayAdapter<CharSequence> mAdapterStatus;
	private Button btnRefresh = null;
	
	public Button getBtnRefresh()
	{
		if(btnRefresh == null)
			btnRefresh = (Button)findViewById(R.id.restaurant_btnRefresh);
		return btnRefresh;
	}

	private Button btnSend = null;
	
	public Button getBtnSend()
	{
		if(btnSend == null)
			btnSend = (Button)findViewById(R.id.restaurant_btnSend);
		
		return btnSend;		
	}
	
	
	private RestaurantsDao restaurantsDao = null;

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);

		bindElements();
		fillSpinners();
	}


	public void refresh()
	{
		LoadingRestaurants load = new LoadingRestaurants(this);
		load.Show();
	}

	public void bindElements()
	{
		spnIds = (Spinner) findViewById(R.id.restaurant_ids);
		spnStatus = (Spinner) findViewById(R.id.restaurant_status);
		
		getBtnRefresh().setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				refresh();				
			}
		});
		
		getBtnSend().setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				new ParseJSON().setRestaurantComment(2, "teste", 2);
				
			}
		});
		
	}
	
	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if(msg.what == 0)
				showErrorDialog(getApplicationContext().getString(R.string.msgErrorTitle), getApplicationContext().getString(R.string.msgErrorMsg));
			else
				refreshAdapterIds();
		}
	};
	
	public void showErrorDialog(String title, String message)
	{
		Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setNeutralButton("OK", null);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setNeutralButton("OK", null).show();
	}
	
	public ArrayAdapter<String> getAdapterIds()
	{
		if(mAdapterIds == null)
			mAdapterIds = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		return mAdapterIds;		
	}
	
	public void refreshAdapterIds()
	{
		ArrayList<String> values = getRestaurantsDao().getNames();
		getAdapterIds().clear();
		for(String s : values)
			getAdapterIds().add(s);
		getAdapterIds().notifyDataSetChanged();
	}


	public void fillSpinners()
	{
		refreshAdapterIds();
		getAdapterIds().setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spnIds.setAdapter(getAdapterIds());
		
		mAdapterStatus = ArrayAdapter.createFromResource(this, R.array.restaurants_status, android.R.layout.simple_spinner_item);

		mAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnStatus.setAdapter(mAdapterStatus);

		spnIds.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{

			}
		});
	}

	public RestaurantsDao getRestaurantsDao()
	{
		if (restaurantsDao == null)
			restaurantsDao = new RestaurantsDao(getApplicationContext());
		return restaurantsDao;
	}
}
