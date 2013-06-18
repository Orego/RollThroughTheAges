import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class RollTrackerTest {
	
	ArrayList<Die> dice;
	RollTracker track;
	
	@Before
	public void setUp() throws Exception {
		dice = new ArrayList<Die>();
		for (int i = 0; i < 6; i++){
			dice.add(new Die());
		}
		track = new RollTracker();
	}

	@Test
	public void test() {
		track.rollDice(dice);
		for (int i = 0; i < dice.size(); i++){
			if (dice.get(i).toString() == "workers"){
				assertTrue(track.getWorkers() >= 3);
			}
			else if (dice.get(i).toString() == "good"){
				assertTrue(track.getGoods() >= 3);
			}
			else if (dice.get(i).toString() == "food / workers"){
				if (dice.get(i).isCountedAsFood()){
					assertTrue(track.getFood() >= 2);
				}
				else assertTrue(track.getWorkers() >= 2);
			}
			else if( dice.get(i).toString() == "skull"){
				assertTrue(track.getSkulls() >= 1 && track.getGoods() >= 2);
			}
			else if (dice.get(i).toString() == "coin"){
				assertTrue(track.getCoin() >= 7);
			}
			else if (dice.get(i).toString() =="food"){
				assertTrue(track.getFood() >= 3);
			}
		}
	}

}
