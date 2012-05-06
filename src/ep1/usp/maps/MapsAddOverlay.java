package ep1.usp.maps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ep1.usp.R;
import ep1.usp.Loading.LoadingAddOverlay;

public class MapsAddOverlay extends Dialog
{
	private Button btnSave;
	private EditText txtName;
	private ArrayAdapter<CharSequence> mAdapterTypes;
	private Spinner spnTypes;
	private int selectedType;
	private int latitude, longitude;
	private Maps mActivity;

	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			if (msg.what == 0)
				hide();
		}
	};

	public MapsAddOverlay(Maps mActivity)
	{
		super(mActivity);
		this.mActivity = mActivity;
	}

	private void addOverlay()
	{
		try
		{
			if (latitude != 0 && longitude != 0 && selectedType != -1)
			{
				LoadingAddOverlay loading = new LoadingAddOverlay(mActivity, latitude, longitude, txtName.getText().toString(), selectedType, this);
				loading.show();
			}
			else
			{

				AlertDialog alert = new AlertDialog.Builder(getContext()).create();
				alert.setTitle("Erro");
				alert.setMessage("GPS nao encontrado!");
				alert.setButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						onBackPressed();
					}
				});
				alert.show();
			}
		}
		catch (Exception e)
		{
		}
	}

	private void fillSpinner()
	{

		mAdapterTypes = ArrayAdapter.createFromResource(getContext(), R.array.map_addOverlayTypes, android.R.layout.simple_spinner_item);

		mAdapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnTypes.setAdapter(mAdapterTypes);

		spnTypes.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				spinnerChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{

			}
		});

	}

	private void loadBinds()
	{
		btnSave = (Button) findViewById(R.id.map_add_overlay_btnSave);
		txtName = (EditText) findViewById(R.id.map_add_overlay_txt_name);

		btnSave.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addOverlay();
			}
		});

		spnTypes = (Spinner) findViewById(R.id.map_add_overlay_ddlType);
	}

	private void spinnerChanged()
	{
		selectedType = spnTypes.getSelectedItemPosition() + 1;
	}

	public void init(int latitude, int longitude)
	{

		this.latitude = latitude;
		this.longitude = longitude;

		loadBinds();
		fillSpinner();

	}
}
