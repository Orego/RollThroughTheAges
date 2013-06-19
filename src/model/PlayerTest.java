package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player p;

	@Before
	public void setUp() throws Exception {
		p = new Player("example", 1);
	}

	@Test
	public void testGetNumCities() {
		assertEquals(3, p.getNumCities());
		assertTrue(p.getCity(0).isFull());
		assertFalse(p.getCity(3).isFull());
		assertEquals(1, p.buyCityWorkers(4, 3));
		assertTrue(p.getCity(3).isFull());
	}

	@Test
	public void testAddGoods() {
		assertEquals(
				"Resources:\nSpearheads: 0\nCloth: 0\nPottery: 0\nStone: 0\nWood: 0\nFood: 3",
				p.getResourcesInfo());
		p.addGoods(0);
		assertEquals(
				"Resources:\nSpearheads: 0\nCloth: 0\nPottery: 0\nStone: 0\nWood: 0\nFood: 3",
				p.getResourcesInfo());
		p.addGoods(3);
		assertEquals(
				"Resources:\nSpearheads: 0\nCloth: 0\nPottery: 1\nStone: 1\nWood: 1\nFood: 3",
				p.getResourcesInfo());
		p.addGoods(7);
		assertEquals(
				"Resources:\nSpearheads: 1\nCloth: 1\nPottery: 2\nStone: 3\nWood: 3\nFood: 3",
				p.getResourcesInfo());
		p.addGoods(13);
		assertEquals(
				"Resources:\nSpearheads: 3\nCloth: 3\nPottery: 5\nStone: 6\nWood: 6\nFood: 3",
				p.getResourcesInfo());
		p.addGoods(10);
		assertEquals(
				"Resources:\nSpearheads: 4\nCloth: 5\nPottery: 6\nStone: 7\nWood: 8\nFood: 3",
				p.getResourcesInfo());
	}

	@Test
	public void testAddFood() {
		assertEquals(3, p.getFood());
		assertEquals(0, p.addFood(4));
		assertEquals(7, p.getFood());
		assertEquals(3, p.addFood(11));
		assertEquals(15, p.getFood());
		assertEquals(-2, p.addFood(-17));
	}

	@Test
	public void testPlayerNames() {
		p = new Player("hi", 1);
		assertEquals("hi", p.getName());
	}

	@Test
	public void testMonuments() {
		p = new Player("hi", 2);
		assertEquals(1, p.buyMonumentWorkers(10, 2));
		assertEquals(
				"Max pop: 3; Cur pop: 0; Score: 0\nMax pop: 5; Cur pop: 0; Score: 0\n"
						+ "Max pop: 9; Cur pop: 9; Score: 6\nMax pop: 11; Cur pop: 0;"
						+ " Score: 0\nMax pop: 13; Cur pop: 0; Score: 0\n",
				p.getMonumentsInfo());

		p = new Player("hi2", 3);
		for (int i = 0; i < 6; i++) {
			p.buyMonumentWorkers(15, i);
		}
		assertEquals(
				"Max pop: 3; Cur pop: 3; Score: 1\nMax pop: 5; Cur pop: 5; Score: 2\n"
						+ "Max pop: 7; Cur pop: 7; Score: 4\nMax pop: 9; Cur pop: 9;"
						+ " Score: 6\nMax pop: 13; Cur pop: 13; Score: 10\nMax pop: 15; Cur pop: 15; Score: 12\n",
				p.getMonumentsInfo());

		p = new Player("hi2", 4);
		for (int i = 0; i < 7; i++) {
			p.buyMonumentWorkers(15, i);
		}
		assertEquals(
				"Max pop: 3; Cur pop: 3; Score: 1\nMax pop: 5; Cur pop: 5; Score: 2\n"
						+ "Max pop: 7; Cur pop: 7; Score: 4\nMax pop: 9; Cur pop: 9;"
						+ " Score: 6\nMax pop: 11; Cur pop: 11; Score: 8\nMax pop: 13;"
						+ " Cur pop: 13; Score: 10\nMax pop: 15; Cur pop: 15; Score: 12\n",
				p.getMonumentsInfo());
	}
	
	@Test
	public void testTotalScore(){
		p = new Player("",1);
		assertEquals(0,p.getTotalScore());
		p.buyDevelopment(DevelopmentList.ARCHITECTURE);
		assertEquals(8,p.getTotalScore());
		p.buyMonumentWorkers(3, 0);
		assertEquals(10,p.getTotalScore());
		p.buyDevelopment(DevelopmentList.EMPIRE);
		assertEquals(21,p.getTotalScore());
		p.addDisasters(3);
		assertEquals(18,p.getTotalScore());
		assertEquals(3,p.getDisasterCount());
	}

}
