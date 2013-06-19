package model;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class GameTest {

	private Game g1, g2, g3, g4;

	@Before
	public void setUp() throws Exception {
		g1 = new Game(new String[] {"blah"});
		g2 = new Game(new String[] {"blah","blah 1"});
		g3 = new Game(new String[] {"blah","Blah","blah 1"});
		g4 = new Game(new String[] {"blah","2","3","4"});
	}
	
	@Test
	public void test() {
		assertEquals(1,g1.getNumPlayers());
		g1.getPlayer(1);
		for(int i=1;i<=2;i++){
			g2.getPlayer(i);
		}
		assertEquals(2,g2.getNumPlayers());
		for(int i=1;i<=3;i++){
			g3.getPlayer(i);
		}
		assertEquals(3,g3.getNumPlayers());
		for(int i=1;i<=4;i++){
			g4.getPlayer(i);
		}
		assertEquals(4,g4.getNumPlayers());
		
		assertEquals("blah",g1.getPlayerNames());
		assertEquals("blah, 2, 3, 4",g4.getPlayerNames());
	}

}
