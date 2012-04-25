package ep1.usp.restaurant;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import ep1.usp.R;

public class RestaurantAddComment extends Dialog
{
	
	protected Spinner spnStatus = null;
	public Spinner getSpnStatus()
	{
		if (spnStatus == null)
			spnStatus = (Spinner) findViewById(R.id.restaurant_status);
		return spnStatus;

	}
	
	private TextView txtComment = null;
	public TextView getTxtComment()
	{
		if (txtComment == null)
			txtComment = (TextView) findViewById(R.id.restaurant_comment);
		return txtComment;
	}
	
	private Button btnSend = null;
	public Button getBtnSend()
	{
		if (btnSend == null)
			btnSend = (Button) findViewById(R.id.restaurant_btnSend);

		return btnSend;
	}
	
	protected ArrayAdapter<CharSequence> mAdapterStatus;
	public ArrayAdapter<CharSequence> getAdapterStatus()
	{
		if(mAdapterStatus == null)
		{
			mAdapterStatus = ArrayAdapter.createFromResource(mActivity, R.array.restaurants_status, android.R.layout.simple_spinner_item);
			mAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		}
		mAdapterStatus.notifyDataSetChanged();
		return mAdapterStatus;		
	}
	
	private Restaurant mActivity;
	public RestaurantAddComment(Restaurant mActivity)
	{
		super(mActivity);
		this.mActivity = mActivity;
	}

	public void init()
	{
		loadBinds();
		fillSpinner();
	}

	private void loadBinds()
	{
		getBtnSend().setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				AddMessageClick();
			}
		});
	}
	
	public int restaurantId;
	public String comment;
	int statusId;
	
	private void AddMessageClick()
	{
		int restaurantPosition = mActivity.getSpnRestaurants().getSelectedItemPosition();
		restaurantId = mActivity.getRestaurantsDao().getIdByName(mActivity.getAdapterRestaurant().getItem(restaurantPosition));
		comment = getTxtComment().getText().toString();
		statusId = getSpnStatus().getSelectedItemPosition() + 1;
		LoadingSendMsg loading = new LoadingSendMsg(mActivity, restaurantId, comment, statusId);
		loading.Show();
	}
	
	private void fillSpinner()
	{
		getSpnStatus().setAdapter(getAdapterStatus());		
	}
}
