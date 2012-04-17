package ep1.usp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import ep1.usp.maps.Maps;

@RunWith(RobolectricTestRunner.class)
public class MapTest {

	private Maps maps;
	@Before
	public void setUp() throws Exception {
		maps = new Maps();
		maps.onCreate(null);
	}
	
	@Test
	public void start() throws Exception {
				
	}
}
