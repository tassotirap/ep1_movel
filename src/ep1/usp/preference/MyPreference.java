package ep1.usp.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import ep1.usp.R;

public class MyPreference extends PreferenceActivity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
    }

}
