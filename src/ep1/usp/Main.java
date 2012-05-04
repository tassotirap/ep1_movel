package ep1.usp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import ep1.usp.gate.Gate;
import ep1.usp.maps.Maps;
import ep1.usp.preference.MyPreference;
import ep1.usp.restaurant.Restaurant;

public class Main extends Activity
{

	private Intent intentMap, intentRestaurant, intentGate, intentActivity;
	private FrameLayout btnMap, btnRestaurant, btnGate, btnSettings;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setIntents();
		setElements();
		bindListeners();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Editor editor = prefs.edit();
		editor.clear();
        editor.commit();
	}

	private void setIntents()
	{
		intentMap = new Intent(this, Maps.class);
		intentRestaurant = new Intent(this, Restaurant.class);
		intentGate = new Intent(this, Gate.class);
		intentActivity = new Intent(this, MyPreference.class);
	}

	private void setElements()
	{
		btnMap = (FrameLayout) findViewById(R.id.btnMap);
		btnRestaurant = (FrameLayout) findViewById(R.id.btnRetaurant);
		btnGate = (FrameLayout) findViewById(R.id.btnGate);
		btnSettings = (FrameLayout) findViewById(R.id.btnSettings);
	}

	private void bindListeners()
	{

		btnMap.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				startActivity(intentMap);
			}
		});

		btnRestaurant.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				startActivity(intentRestaurant);
			}
		});

		btnGate.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				startActivity(intentGate);
			}
		});
		
		btnSettings.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				startActivity(intentActivity);			
			}
		});

	}
}