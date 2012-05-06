package ep1.usp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowDialog;

import ep1.usp.restaurant.Restaurant;

@RunWith(RobolectricTestRunner.class)
public class RestaurantTest {
	
	Restaurant restaurant;
	
	@Before
	public void setUp() throws Exception {
		restaurant = new Restaurant();
		restaurant.onCreate(null);		
	}
	
	@Test
	public void SendComment() {
		restaurant.getBtnNewComment().performClick();
		ShadowDialog dialog = ShadowDialog.getLatestDialog();
		
		assertThat(dialog.isShowing(), equalTo(true));        
        assertThat(dialog.getLayoutId(), equalTo(R.layout.restaurant_add_msg));		
	}
	
	@Test
	public void GetComments() {
		restaurant.getBtnRefresh().performClick();
	}
	
	@Test
	public void GetAdapterRestaurant()
	{
		ArrayAdapter<String> result = restaurant.getAdapterRestaurant();
		assertNotNull(result);		
	}

}
