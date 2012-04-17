package ep1.usp;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.widget.Button;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import ep1.usp.gate.Gate;
import ep1.usp.maps.Maps;
import ep1.usp.restaurant.Restaurant;

@RunWith(RobolectricTestRunner.class)
public class MainTest {

	private Main main;
	private Button btnMap, btnRestaurant, btnGate;

	@Before
	public void setUp() throws Exception {
		main = new Main();
		main.onCreate(null);
		btnMap = (Button)main.findViewById(R.id.btn_map);
		btnRestaurant = (Button)main.findViewById(R.id.btn_retaurant);
		btnGate = (Button)main.findViewById(R.id.btn_gate);
	}

	@Test
	public void appName() throws Exception {
		String appName = main.getResources().getString(R.string.app_name);
		assertThat(appName, equalTo("EP1"));
	}

	@Test
	public void mapClick()throws Exception {
		btnMap.performClick();	
		ShadowActivity shadowActivity = shadowOf(main);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(Maps.class.getName()));
	}
	
	@Test
	public void restaurantClick()throws Exception {
		btnRestaurant.performClick();	
		ShadowActivity shadowActivity = shadowOf(main);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(Restaurant.class.getName()));
	}
	
	@Test
	public void gateClick()throws Exception {
		btnGate.performClick();	
		ShadowActivity shadowActivity = shadowOf(main);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(Gate.class.getName()));
	}
}
