package tests;
import static org.junit.Assert.*;

import models.Player;

import org.junit.Before;
import org.junit.Test;

/** Check creating a player's name and then checking his name*/
public class PlayerNamesTest {

	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p = new Player();
	}

//	@Test
//	public void test() {
//		System.out.println(p.getName());
//		System.out.println(p.setName());
//		System.out.prinln(p.getName());
//		fail("Not yet implemented");
//	}

}
