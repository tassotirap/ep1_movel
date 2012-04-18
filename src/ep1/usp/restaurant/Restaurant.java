package ep1.usp.restaurant;

import java.util.ArrayList;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import ep1.usp.R;
import ep1.usp.access.db.RestaurantCommentDao;
import ep1.usp.access.db.RestaurantsDao;

public class Restaurant extends Activity
{
	
	private RestaurantCommentDao restaurantCommentDao = null;
	public RestaurantCommentDao getRestaurantCommentDao()
	{
		if(restaurantCommentDao == null)
			restaurantCommentDao = new RestaurantCommentDao(getApplicationContext());
		return restaurantCommentDao;	
		
	}

	private LinearLayout txtComments = null;
	public LinearLayout getTxtComments()
	{
		if(txtComment == null)
			txtComments = (LinearLayout)findViewById(R.id.textLayout);
		return txtComments;		
	}
	
	
	public void fillComments()
	{
		
		ArrayList<MessageInfo> msgs = getRestaurantCommentDao().getAll();
		getTxtComments().removeAllViews();
		for(MessageInfo msg : msgs)
		{
			TextView txt = new TextView(this);
			txt.setText(msg.getMessage()+ " - " + msg.getDate());
			getTxtComments().addView(txt);			
		}		
	}
	
	protected Spinner spnRestaurants = null;
	public Spinner getSpnRestaurants()
	{
		if(spnRestaurants == null)
			spnRestaurants = (Spinner) findViewById(R.id.restaurant_ids);
		return spnRestaurants;
	
	}
	protected Spinner spnStatus = null;
	public Spinner getSpnStatus()
	{
		if(spnStatus == null)
			spnStatus = (Spinner) findViewById(R.id.restaurant_status);
		return spnStatus;
	
	}
	
	
	protected ArrayAdapter<String> mAdapterIds;
	protected ArrayAdapter<CharSequence> mAdapterStatus;
	
	private TextView txtComment = null;
	public TextView getTxtComment()
	{
		if(txtComment == null)
			txtComment = (TextView)findViewById(R.id.restaurant_comment);
		return txtComment;
	}
	
	private Button btnRefresh2 = null;	
	public Button getBtnRefresh2()
	{
		if(btnRefresh2 == null)
			btnRefresh2 = (Button)findViewById(R.id.restaurant_BtnRefresh2);
		return btnRefresh2;
	}
	
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
	public RestaurantsDao getRestaurantsDao()
	{
		if (restaurantsDao == null)
			restaurantsDao = new RestaurantsDao(getApplicationContext());
		return restaurantsDao;
	}

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
	
	private void refreshMsg()
	{
		int restaurantPosition = getSpnRestaurants().getSelectedItemPosition();
		int restaurantId = restaurantsDao.getIdByName(mAdapterIds.getItem(restaurantPosition));
		LoadingGetMsg load = new LoadingGetMsg(this, restaurantId);
		load.Show();		
	}

	public void bindElements()
	{
		
		spnStatus = (Spinner) findViewById(R.id.restaurant_status);
		
		getBtnRefresh2().setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				refreshMsg();				
			}
		});
		
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
				AddMessageClick();				
			}
		});
		
	}
	
	private void AddMessageClick()
	{
		int restaurantPosition = getSpnRestaurants().getSelectedItemPosition();
		int restaurantId = restaurantsDao.getIdByName(mAdapterIds.getItem(restaurantPosition));
		String comment = getTxtComment().getText().toString();
		int statusId = getSpnStatus().getSelectedItemPosition() + 1;
		LoadingSendMsg loading = new LoadingSendMsg(this, restaurantId, comment, statusId);
		loading.Show();		
	}
	
	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if(msg.what == 0)
				showDialog(getApplicationContext().getString(R.string.msgErrorTitle), getApplicationContext().getString(R.string.msgErrorMsg));
			else if(msg.what == 1)
				refreshAdapterIds();
			else if(msg.what == 2)
				showDialog("OK", "OK");
			else if(msg.what == 3)
				fillComments();
		}
	};
	
	public void showDialog(String title, String message)
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
		
		getSpnRestaurants().setAdapter(getAdapterIds());
		
		mAdapterStatus = ArrayAdapter.createFromResource(this, R.array.restaurants_status, android.R.layout.simple_spinner_item);

		mAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnStatus.setAdapter(mAdapterStatus);

		getSpnRestaurants().setOnItemSelectedListener(new OnItemSelectedListener()
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


}
