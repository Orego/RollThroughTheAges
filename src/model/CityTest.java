package model;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class CityTest {

	private City c;
	
	@Test
	public void testCity() {
		City c = new City(0);
		assertEquals(0,c.currentPopulation());
		assertTrue(c.isFull());
		assertEquals(0,c.maxPopulation());
		c = new City(3);
		assertEquals(3,c.maxPopulation());
		assertEquals(0,c.currentPopulation());
		assertFalse(c.isFull());
		c.addWorkers(1);
		assertFalse(c.isFull());
		assertEquals(1,c.currentPopulation());
		c.addWorkers(2);
		assertTrue(c.isFull());
	}

}
