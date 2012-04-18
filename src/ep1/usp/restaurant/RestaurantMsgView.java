package ep1.usp.restaurant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
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
		if(view == null)
		 view = View.inflate(context, R.layout.restaurant_msg, null);
		return view;
	}

	private TextView txt1;
	public TextView getText1()
	{
		if(txt1 == null)
			txt1 = (TextView)getView().findViewById(R.id.textView1);
		return txt1;		
	}
	
	private TextView txt2;
	public TextView getText2()
	{
		if(txt2 == null)
			txt2 = (TextView)getView().findViewById(R.id.textView2);
		return txt2;		
	}
	
	private TextView txt3;
	public TextView getText3()
	{
		if(txt3 == null)
			txt3 = (TextView)getView().findViewById(R.id.textView3);
		return txt3;		
	}
	}
