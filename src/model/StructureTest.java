package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class StructureTest {
	
	@Test
	public void testCity() {
		Structure c = new Structure(0,0);
		assertEquals(0,c.currentPopulation());
		assertTrue(c.isFull());
		assertEquals(0,c.maxPopulation());
		c = new Structure(3,0);
		assertEquals(3,c.maxPopulation());
		assertEquals(0,c.currentPopulation());
		assertFalse(c.isFull());
		c.addWorkers(1);
		assertFalse(c.isFull());
		assertEquals(1,c.currentPopulation());
		c.addWorkers(2);
		assertTrue(c.isFull());
	}
	
	@Test
	public void testMonument() {
		Structure m = new Structure(5,2);
		assertEquals(0, m.getScore());
		assertEquals(1,m.addWorkers(6));
		assertEquals(2, m.getScore());
		m.setOtherPlayerHasFinished(true);
		assertEquals(1,m.getScore());
	}

}
