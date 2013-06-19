package tests;
import static org.junit.Assert.*;

import models.DevelopmentList;

import org.junit.Before;
import org.junit.Test;


public class DevelopmentListTest {

	private DevelopmentList d;
	
	@Before
	public void setUp() throws Exception {
		d = new DevelopmentList(); 	
	}
	@Test
	public void test() {
		assertFalse(d.isDevelopmentBought(DevelopmentList.RELIGION));
		d.buyDevelopment(DevelopmentList.COINAGE);
		assertTrue(d.isDevelopmentBought(DevelopmentList.COINAGE));
		assertFalse(d.getAvailableDevelopments().contains(new Integer(DevelopmentList.COINAGE)));
	}

}
