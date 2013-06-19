package tests;
import static org.junit.Assert.*;

import models.PlayerResources;

import org.junit.Before;
import org.junit.Test;

public class PlayerResourcesTest {

	private PlayerResources r;

	@Before
	public void setUp() throws Exception {
		r = new PlayerResources();
	}

	@Test
	public void testResources() {
		assertEquals(3, r.getAmount(PlayerResources.FOOD));
		for (int i = 0; i <= 4; i++) {
			assertEquals(0,r.getAmount(i));
		}
		
		for (int i = 0; i <= 5; i++) {
			r.changeAmount(i, 2);
		}
		
		assertEquals(5,r.getAmount(PlayerResources.FOOD));
		for (int i = 0; i <= 4; i++) {
			assertEquals(2,r.getAmount(i));
		}
		
		for (int i = 0; i <= 5; i++) {
			r.changeAmount(i, -1);
		}
		
		assertEquals(4,r.getAmount(PlayerResources.FOOD));
		for (int i = 0; i <= 4; i++) {
			assertEquals(1,r.getAmount(i));
		}
	}

}
