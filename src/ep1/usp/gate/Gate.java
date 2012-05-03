package ep1.usp.gate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ep1.usp.R;
import ep1.usp.access.db.GatesDao;
import ep1.usp.maps.MyLocationListener;

public class Gate extends Activity implements LocationListener
{
	private Button btnRefresh = null;
	private Button btnSend = null;
	private GatesDao gatesDao = null;
	private LocationManager myManager;

	private ImageView img1 = null;
	private ImageView img2 = null;
	private ImageView img3 = null;

	private TextView txtDistance1 = null;
	private TextView txtDistance2 = null;
	private TextView txtDistance3 = null;

	private int latitude;
	private int longitude;

	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
				case 0:
					// error
					break;
				case 1:
					refreshGates();
					break;

				default:
					break;
			}

		}
	};

	private void bindListeners()
	{

	}

	private void setElements()
	{
		getBtnRefresh().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				loadGates();
			}
		});

	}

	private void loadGates()
	{
		LoadingGetGate loadingGetGates = new LoadingGetGate(this);
		loadingGetGates.Show();
	}

	private void setIntents()
	{

	}

	public Button getBtnRefresh()
	{
		if (btnRefresh == null)
			btnRefresh = (Button) findViewById(R.id.gate_btnRefresh);
		return btnRefresh;
	}

	public Button getBtnSend()
	{
		if (btnSend == null)
			btnSend = (Button) findViewById(R.id.gate_btnSend);
		return btnSend;
	}

	public GatesDao getGatesDao()
	{
		if (gatesDao == null)
			gatesDao = new GatesDao(getApplicationContext());
		return gatesDao;
	}

	public ImageView getImg1()
	{
		if (img1 == null)
			img1 = (ImageView) findViewById(R.id.gates_img1);
		return img1;
	}

	public ImageView getImg2()
	{

		if (img2 == null)
			img2 = (ImageView) findViewById(R.id.gates_img2);
		return img2;
	}

	public ImageView getImg3()
	{
		if (img3 == null)
			img3 = (ImageView) findViewById(R.id.gates_img3);
		return img3;
	}

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.gate);

		myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		setIntents();
		setElements();
		bindListeners();
		refreshGates();
	}

	public void refreshGates()
	{
		ArrayList<GateInfo> gates = getGatesDao().getAll();
		if (gates.size() > 0)
		{
			refreshImage(getImg1(), gates.get(0).getStatus());
			refreshImage(getImg2(), gates.get(1).getStatus());
			refreshImage(getImg3(), gates.get(2).getStatus());
		}
	}

	public void refreshImage(ImageView img, int status)
	{
		switch (status)
		{
			case 1:
				img.setImageResource(R.drawable.msg1);
				break;
			case 2:
				img.setImageResource(R.drawable.msg2);
				break;
			case 3:
				img.setImageResource(R.drawable.msg3);
				break;
			case 4:
				img.setImageResource(R.drawable.msg4);
				break;
			case 5:
				img.setImageResource(R.drawable.msg5);
				break;
		}

	}

	@Override
	public void onLocationChanged(Location location)
	{
		latitude = (int) (location.getLatitude() * 1e6);
		longitude = (int) (location.getLongitude() * 1e6);
		refreshDistance(location);
	}

	private void refreshDistance(Location location)
	{
		ArrayList<GateInfo> gates = getGatesDao().getAll();
		if (gates.size() > 0)
		{
			double d1 = refreshDistanceGate(location, gates.get(0));
			double d2 = refreshDistanceGate(location, gates.get(1));
			double d3 = refreshDistanceGate(location, gates.get(2));

			writeDistance(getTxtDistance1(), d1);
			writeDistance(getTxtDistance2(), d2);
			writeDistance(getTxtDistance3(), d3);
		}
	}

	private void writeDistance(TextView text, double dist)
	{
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String sDist;
		if (dist > 10000)
			sDist = df.format(dist / 1000) + "km";
		else if (dist > 1)
			sDist = df.format(dist) + "m";
		else
			sDist = df.format(dist * 100) + "cm";

		sDist = sDist.replace('.', '#');
		sDist = sDist.replace(',', '.');
		sDist = sDist.replace('#', ',');
		text.setText(sDist);

	}

	private double refreshDistanceGate(Location location, GateInfo gate)
	{
		Location locationGate1 = new Location("reverseGeocoded");
		locationGate1.setLatitude(gate.getLatitude() / 1e6);
		locationGate1.setLongitude(gate.getLongitude() / 1e6);
		return location.distanceTo(locationGate1);
	}

	@Override
	public void onProviderDisabled(String arg0)
	{
	}

	@Override
	public void onProviderEnabled(String arg0)
	{
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
	{
	}

	private void startListening()
	{
		myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	private void stopListening()
	{
		if (myManager != null)
			myManager.removeUpdates(this);
	}

	@Override
	protected void onDestroy()
	{
		stopListening();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		stopListening();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		startListening();
		super.onResume();
	}

	public TextView getTxtDistance1()
	{
		if (txtDistance1 == null)
			txtDistance1 = (TextView) findViewById(R.id.gate_txtDistance1);
		return txtDistance1;
	}

	public TextView getTxtDistance2()
	{
		if (txtDistance2 == null)
			txtDistance2 = (TextView) findViewById(R.id.gate_txtDistance2);
		return txtDistance2;
	}

	public TextView getTxtDistance3()
	{
		if (txtDistance3 == null)
			txtDistance3 = (TextView) findViewById(R.id.gate_txtDistance3);
		return txtDistance3;
	}

}
