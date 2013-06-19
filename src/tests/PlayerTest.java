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
	
	@Test
	public void testAddGoods() {
		assertEquals("Resources:\nSpearheads: 0\nCloth: 0\nPottery: 0\nStone: 0\nWood: 0\nFood: 3",p.getResourcesInfo());
		p.addGoods(0);
		assertEquals("Resources:\nSpearheads: 0\nCloth: 0\nPottery: 0\nStone: 0\nWood: 0\nFood: 3",p.getResourcesInfo());
		p.addGoods(3);
		assertEquals("Resources:\nSpearheads: 0\nCloth: 0\nPottery: 1\nStone: 1\nWood: 1\nFood: 3",p.getResourcesInfo());
		p.addGoods(7);
		assertEquals("Resources:\nSpearheads: 1\nCloth: 1\nPottery: 2\nStone: 3\nWood: 3\nFood: 3",p.getResourcesInfo());
		p.addGoods(13);
		assertEquals("Resources:\nSpearheads: 3\nCloth: 3\nPottery: 5\nStone: 6\nWood: 6\nFood: 3",p.getResourcesInfo());
		p.addGoods(10);
		assertEquals("Resources:\nSpearheads: 4\nCloth: 5\nPottery: 6\nStone: 7\nWood: 8\nFood: 3",p.getResourcesInfo());
	}
	
	@Test
	public void testAddFood(){
		assertEquals(3, p.getFood());
		assertEquals(0,p.addFood(4));
		assertEquals(7, p.getFood());
		assertEquals(3,p.addFood(11));
		assertEquals(15,p.getFood());
		assertEquals(-2,p.addFood(-17));
	}

}
