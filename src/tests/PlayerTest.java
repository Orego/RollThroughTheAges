package tests;
import static org.junit.Assert.*;

import models.Player;

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
		System.out.println("Is City #1 full? " + p.getCity(0).isFull());
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		System.out.println("Did we successfully add 4 workers to city #4 (max population = 3)? " + p.getCity(3).addWorkers(4));
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		System.out.println("Did we successfully add 3 workers to city #4? " + p.getCity(3).addWorkers(3));
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
	}

}
