package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Game g1, g2, g3, g4;

	@Before
	public void setUp() throws Exception {
		g1 = new Game(new String[] { "blah" });
		g2 = new Game(new String[] { "blah", "blah 1" });
		g3 = new Game(new String[] { "blah", "Blah", "blah 1" });
		g4 = new Game(new String[] { "blah", "2", "3", "4" });
	}

	@Test
	public void test() {
		// make sure no exceptions get thrown
		assertEquals(1, g1.getNumPlayers());
		g1.getPlayer(0);
		for (int i = 0; i < 2; i++) {
			g2.getPlayer(i);
		}
		assertEquals(2, g2.getNumPlayers());
		for (int i = 0; i < 3; i++) {
			g3.getPlayer(i);
		}
		assertEquals(3, g3.getNumPlayers());
		for (int i = 0; i < 4; i++) {
			g4.getPlayer(i);
		}
		assertEquals(4, g4.getNumPlayers());

		assertEquals("blah", g1.getPlayerNames());
		assertEquals("blah, 2, 3, 4", g4.getPlayerNames());
	}

	@Test
	public void testEndOfGame() {
		Game g = new Game(new String[] { "p1", "p2", "p3", "p4" });
		// p1 buys 5 developments
		Player p1 = g.getPlayer(0);
		for (int i = 0; i < 5; i++) {
			p1.buyDevelopment(i);
		}
		for (int i = 1; i < g.getNumPlayers(); i++) {
			assertTrue(g.nextTurn());
		}
		assertFalse(g.nextTurn());
		assertEquals(13, p1.getTotalScore());
		for (int i = 1; i < g.getNumPlayers(); i++) {
			assertEquals(0, g.getPlayer(i).getTotalScore());
		}

		g = new Game(new String[] { "p1", "p2", "p3", "p4" });
		p1 = g.getPlayer(1);
		g.getPlayer(0).buyMonumentWorkers(5, 0);
		g.nextTurn();
		for (int i = 0; i < p1.getMonumentsPlayerHas().length; i++) {
			p1.buyMonumentWorkers(15, i);
		}
		for (int i = 2; i < g.getNumPlayers(); i++) {
			assertTrue(g.nextTurn());
		}
		assertFalse(g.nextTurn());
		assertEquals(42, p1.getTotalScore());
		assertEquals(1, g.getPlayer(0).getTotalScore());
		assertEquals(0, g.getPlayer(2).getTotalScore());
		assertEquals(0, g.getPlayer(3).getTotalScore());

	}

}
