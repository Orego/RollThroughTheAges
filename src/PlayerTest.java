import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
	
	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p = new Player();
	}

	@Test
	public void testGetNumCities() {
		assertEquals(3,p.getNumCities());
	}

}
