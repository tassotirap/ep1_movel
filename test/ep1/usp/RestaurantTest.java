package ep1.usp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.AlertDialog;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;

import ep1.usp.restaurant.MessageDto;
import ep1.usp.restaurant.Restaurant;
import ep1.usp.restaurant.RestaurantDto;

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
		assertNotNull(restaurant.getAdapterRestaurant());		
	}
	
	@Test
	public void getBtnMenu()
	{
		assertNotNull(restaurant.getBtnMenu());		
	}
	
	@Test
	public void getBtnNewComment()
	{
		assertNotNull(restaurant.getBtnNewComment());		
	}
	
	@Test
	public void getBtnRefresh()
	{
		assertNotNull(restaurant.getBtnRefresh());		
	}
	
	@Test
	public void getImgStatus()
	{
		assertNotNull(restaurant.getImgStatus());		
	}
	
	@Test
	public void getRestaurantCommentDao()
	{
		assertNotNull(restaurant.getRestaurantCommentDao());		
	}
	
	@Test
	public void getRestaurantsDao()
	{
		assertNotNull(restaurant.getRestaurantsDao());		
	}
	
	@Test
	public void getSpnRestaurants()
	{
		assertNotNull(restaurant.getSpnRestaurants());		
	}
	
	@Test
	public void showDialog() {
		restaurant.showDialog("Teste", "Teste");
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));
        assertThat(((String) alertDialog.getTitle()), equalTo("Teste"));
        assertThat(((String) alertDialog.getMessage()), equalTo("Teste"));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void DBCAllsRestaurantsDao()
	{
		restaurant.getRestaurantsDao().set(new RestaurantDto(1, "teste", 1));
		restaurant.getRestaurantsDao().getAll();		
	}
	
	@Test
	public void DBCAllsRestaurantCommentDao()
	{
		restaurant.getRestaurantCommentDao().set(new MessageDto(1, "teste", new Date(), 1));
		restaurant.getRestaurantCommentDao().getAll();		
	}
	
}
