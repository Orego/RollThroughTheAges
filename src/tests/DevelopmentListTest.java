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
		assertTrue(d.isDevelopmentBought(DevelopmentList.RELIGION)==0);
		d.buyDevelopment(DevelopmentList.COINAGE);
		assertTrue(d.isDevelopmentBought(DevelopmentList.COINAGE)==1);
		System.out.println(
				DevelopmentList.DEVELOPMENT_NAMES[DevelopmentList.COINAGE] +
				" is number " +
				DevelopmentList.COINAGE +
				" and cost is " + 
				DevelopmentList.DEVELOPMENT_COSTS[DevelopmentList.COINAGE] +
				" and points are " + 
				DevelopmentList.DEVELOPMENT_POINTS[DevelopmentList.COINAGE]
		);
		System.out.println(
			d.getAvailableDevelopments() + " coinage is 5, and should not be in this list"
		);
	}

}
