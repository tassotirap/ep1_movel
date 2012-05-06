package ep1.usp.restaurant;

import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import ep1.usp.R;
import ep1.usp.Loading.LoadingSendMsg;

public class RestaurantAddComment extends Dialog
{

	protected Spinner spnStatus = null;
	private TextView txtComment = null;

	private Button btnSend = null;
	protected ArrayAdapter<String> mAdapterStatus;

	private Restaurant mActivity;
	public int restaurantId;

	public String comment;
	int statusId;

	public RestaurantAddComment(Restaurant mActivity, int restaurantId)
	{
		super(mActivity);
		this.mActivity = mActivity;
		this.restaurantId = restaurantId;
	}

	private void AddMessageClick()
	{
		comment = getTxtComment().getText().toString();
		statusId = getSpnStatus().getSelectedItemPosition() + 1;
		LoadingSendMsg loading = new LoadingSendMsg(mActivity, restaurantId, comment, statusId);
		loading.show();
	}

	private void fillSpinner()
	{
		getSpnStatus().setAdapter(getAdapterStatus());
	}

	private void loadBinds()
	{
		getBtnSend().setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if (getTxtComment().getText().toString().length() > 0)
				{
					AddMessageClick();
				}
				else
				{
					mActivity.handler.sendEmptyMessage(4);
				}
			}
		});
	}

	public ArrayAdapter<String> getAdapterStatus()
	{
		if (mAdapterStatus == null)
		{
			String[] items = mActivity.getResources().getStringArray(R.array.restaurant_status);				
			mAdapterStatus =  new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, items);
			mAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		}
		mAdapterStatus.notifyDataSetChanged();
		return mAdapterStatus;
	}

	public Button getBtnSend()
	{
		if (btnSend == null)
			btnSend = (Button) findViewById(R.id.restaurant_btnSend);

		return btnSend;
	}

	public Spinner getSpnStatus()
	{
		if (spnStatus == null)
			spnStatus = (Spinner) findViewById(R.id.restaurant_status);
		return spnStatus;

	}

	public TextView getTxtComment()
	{
		if (txtComment == null)
			txtComment = (TextView) findViewById(R.id.restaurant_comment);
		return txtComment;
	}

	public void init()
	{
		loadBinds();
		fillSpinner();
	}
}
