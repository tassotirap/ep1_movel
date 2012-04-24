package ep1.usp.restaurant;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import ep1.usp.R;

public class RestaurantMsgView
{

	private Context context;

	public RestaurantMsgView(Context context)
	{
		this.context = context;

	}

	private View view;

	public View getView()
	{
		if (view == null)
			view = View.inflate(context, R.layout.restaurant_msg, null);
		return view;
	}

	private TextView date = null;

	public TextView getDate()
	{
		if (date == null)
			date = (TextView) getView().findViewById(R.id.restaurant_txtDate);
		return date;
	}

	private TextView comment = null;

	public TextView getComment()
	{
		if (comment == null)
			comment = (TextView) getView().findViewById(R.id.restaurant_txtMsg);
		return comment;
	}

	private ImageView status = null;

	public ImageView getStatus()
	{
		if (status == null)
			status = (ImageView) getView().findViewById(R.id.restaurant_imgStatus);
		return status;
	}
}
