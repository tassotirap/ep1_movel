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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import ep1.usp.R;
import ep1.usp.access.db.RestaurantCommentDao;
import ep1.usp.access.db.RestaurantsDao;
import ep1.usp.lib.DateAndTime;

public class Restaurant extends Activity
{
	int tryNumber = 0;
	int restaurantId = 0;
	
	private ImageView imgStatus = null;
	private RestaurantCommentDao restaurantCommentDao = null;
	
	private RestaurantsDao restaurantsDao = null;
	private LinearLayout txtComments = null;

	protected Spinner spnRestaurants = null;
	private Button btnRefresh = null;
	
	private ImageButton btnNewComment = null;
	protected ArrayAdapter<String> mAdapterRestaurant;

	private RestaurantAddComment dialogAddComment = null;
	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
				case 0:
					showDialog(getApplicationContext().getString(R.string.msgErrorTitle), getApplicationContext().getString(R.string.msgErrorMsg));
					break;
				case 1:
					refreshAdapterRestaurants();
					break;
				case 2:
					showDialog("OK", "Comentario enviado com sucesso!");
					if(dialogAddComment != null)
					{
						dialogAddComment.hide();
						dialogAddComment = null;		
					}
					break;
				case 3:
					fillComments();
					RestaurantStatus();
					break;
				case 4:
					showDialog("Erro", "Comentario nao pode ser nulo.");
					break;
			}
		}
	};

	private void AddComment(MessageInfo msg)
	{
		AddComment(msg, false);	
	}
	private void AddComment(MessageInfo msg, Boolean first)
	{
		RestaurantMsgView txt = new RestaurantMsgView(this);
		
		txt.getDate().setText(DateAndTime.ParseToStringPrint(msg.getDate()));
		txt.getComment().setText(msg.getMessage());			
		txt.getStatus().setImageResource(getStatusImageResource(msg.getStaus()));
		if(first)
			getTxtComments().addView(txt.getView(), 0);
		else
			getTxtComments().addView(txt.getView());
	}
	private int getStatusImageResource(int status)
	{
		switch (status)
		{
			case 1:
				return R.drawable.msg1;
			case 2:
				return R.drawable.msg2;
			case 3:
				return R.drawable.msg3;
			case 4:
				return R.drawable.msg4;
			case 5:
				return R.drawable.msg5;
		}
		return R.drawable.msg1;
	}
	
	private void refreshMsg()
	{
		LoadingGetMsg load = new LoadingGetMsg(this, restaurantId);
		load.Show();
	}
	private void RestaurantStatus()
	{
		int status = getRestaurantsDao().getStatusById(restaurantId);	
		getImgStatus().setImageResource(getStatusImageResource(status));
	}
	
	private void showAddComment()
	{
		dialogAddComment = new RestaurantAddComment(this);
		dialogAddComment.setContentView(R.layout.restaurant_add_msg);
		dialogAddComment.setTitle("Adicionar Comentario");
		dialogAddComment.init();
		dialogAddComment.show();			
	}
	
	public void bindElements()
	{
		getBtnRefresh().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				refreshMsg();
			}
		});
		
		getSpnRestaurants().setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				int restaurantPosition = getSpnRestaurants().getSelectedItemPosition();
				restaurantId = restaurantsDao.getIdByName(getAdapterRestaurant().getItem(restaurantPosition));
				refreshMsg();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				getTxtComments().removeAllViews();				
			}
		} );
		
		getBtnNewComment().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showAddComment();
			}
		});
	}

	public void fillComments()
	{
		ArrayList<MessageInfo> msgs = getRestaurantCommentDao().getAll();
		getTxtComments().removeAllViews();
		for (MessageInfo msg : msgs)
		{
			AddComment(msg);
		}
	}

	public void fillSpinners()
	{
		refreshAdapterRestaurants();
		getSpnRestaurants().setAdapter(getAdapterRestaurant());
	}
	public ArrayAdapter<String> getAdapterRestaurant()
	{
		if (mAdapterRestaurant == null)
		{
			mAdapterRestaurant = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
			mAdapterRestaurant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		}
		return mAdapterRestaurant;
	}
	
	
	public ImageButton getBtnNewComment()
	{
		if (btnNewComment == null)
			btnNewComment = (ImageButton) findViewById(R.id.restaurant_btnComment);
		return btnNewComment;
	}

	public Button getBtnRefresh()
	{
		if (btnRefresh == null)
			btnRefresh = (Button) findViewById(R.id.restaurant_BtnRefresh);
		return btnRefresh;
	}

	public ImageView getImgStatus()
	{
		if(imgStatus == null)
			imgStatus = (ImageView)findViewById(R.id.restaurant_imgStatus);
		return imgStatus;
	}

	public RestaurantCommentDao getRestaurantCommentDao()
	{
		if (restaurantCommentDao == null)
			restaurantCommentDao = new RestaurantCommentDao(getApplicationContext());
		return restaurantCommentDao;

	}
	
	public RestaurantsDao getRestaurantsDao()
	{
		if (restaurantsDao == null)
			restaurantsDao = new RestaurantsDao(getApplicationContext());
		return restaurantsDao;
	}	
	public Spinner getSpnRestaurants()
	{
		if (spnRestaurants == null)
			spnRestaurants = (Spinner) findViewById(R.id.restaurant_ids);
		return spnRestaurants;

	}

	

	public LinearLayout getTxtComments()
	{
		if (txtComments == null)
			txtComments = (LinearLayout) findViewById(R.id.textLayout);
		return txtComments;
	}
	
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);

		bindElements();
		fillSpinners();
	}

	public void refreshAdapterRestaurants()
	{
		ArrayList<String> values = getRestaurantsDao().getNames();

		if (values.size() == 0 && tryNumber < 5)
		{
			tryNumber++;
			refreshRestaurants();
			return;
		}

		tryNumber = 0;
		getAdapterRestaurant().clear();
		for (String s : values)
			getAdapterRestaurant().add(s);
		getAdapterRestaurant().notifyDataSetChanged();
	}

	public void refreshRestaurants()
	{
		LoadingRestaurants load = new LoadingRestaurants(this);
		load.Show();
	}

	public void showDialog(String title, String message)
	{
		Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setNeutralButton("OK", null);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setNeutralButton("OK", null).show();
	}

}
