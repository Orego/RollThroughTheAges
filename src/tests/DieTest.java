package tests;
import static org.junit.Assert.*;
import models.Die;

import org.junit.Before;
import org.junit.Test;

public class DieTest {

	private Die die;
	
	@Before
	public void setUp() throws Exception {
		die = new Die();
	}

	@Test
	public void testRoll() {
		int[] counts = new int[6];
		for (int i = 0; i < 6000; i++) {
			die.roll();
			counts[die.getTopFace()]++;
		}
		for (int i = 0; i < counts.length; i++) {
			assertTrue(counts[i] >= 750);
		}
	}
	
	@Test
	public void testFoodWorker() {
		assertTrue(die.isCountedAsFood());
		die.countAsFood(false);
		assertFalse(die.isCountedAsFood());
	}

}
