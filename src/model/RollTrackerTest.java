package model;

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
		for (int i = 0; i < 6; i++) {
			dice.add(new Die());
		}
		track = new RollTracker();
	}

	@Test
	public void testRoll() {
		track.rollDice(dice);
		for (int i = 0; i < dice.size(); i++) {
			if (dice.get(i).toString() == "workers") {
				assertTrue(track.getWorkers() >= 3);
			} else if (dice.get(i).toString() == "good") {
				assertTrue(track.getGoods() >= 1);
			} else if (dice.get(i).toString() == "food / workers") {
				if (dice.get(i).isCountedAsFood()) {
					assertTrue(track.getFood() >= 2);
				} else
					assertTrue(track.getWorkers() >= 2);
			} else if (dice.get(i).toString() == "skull") {
				assertTrue(track.getSkulls() >= 1 && track.getGoods() >= 2);
			} else if (dice.get(i).toString() == "coin") {
				assertTrue(track.getCoin() >= 7);
			} else if (dice.get(i).toString() == "food") {
				assertTrue(track.getFood() >= 3);
			}
		}
	}

	@Test
	public void testReRoll() {
		track.rollDice(dice);
		int food = track.getFood(), coin = track.getCoin(), workers = track
				.getWorkers(), skulls = track.getSkulls(), goods = track
				.getGoods();
		assertFalse(track.reroll(new ArrayList<Integer>(), dice));
		assertEquals(food, track.getFood());
		assertEquals(coin, track.getCoin());
		assertEquals(workers, track.getWorkers());
		assertEquals(skulls, track.getSkulls());
		assertEquals(goods, track.getGoods());

		ArrayList<Integer> ints = new ArrayList<Integer>();
		int ranNum = (int) (Math.random() * 6);
		ints.add(ranNum);
		// will give infinite loop if all dice are skulls
		while (track.rerollContainsSkulls(ints, dice)) {
			ints.clear();
			ranNum = (int) (Math.random() * 6);
			ints.add(ranNum);
		}
		assertTrue(track.reroll(ints, dice));

		// will give infinite loop if all dice are skulls
		while (track.rerollContainsSkulls(ints, dice)) {
			ints.clear();
			ranNum = (int) (Math.random() * 6);
			ints.add(ranNum);
		}
		assertTrue(track.reroll(ints, dice));

		assertFalse(track.reroll(ints, dice));

		//this test makes sure reroll changes the dice
		boolean flag = false;
		for (int i=0; i<10; i++) {
			track.rollDice(dice);
			
			food = track.getFood();
			coin = track.getCoin();
			workers = track.getWorkers();
			skulls = track.getSkulls();
			goods = track.getGoods();
			
			do {
				ints.clear();
				ranNum = (int) (Math.random() * 6);
				ints.add(ranNum);
			} while (track.rerollContainsSkulls(ints, dice));
			
			assertTrue(track.reroll(ints, dice));
			
			if (!(food == track.getFood() && coin == track.getCoin()
					&& workers == track.getWorkers() && skulls == track.getSkulls()
					&& goods == track.getGoods()))
				flag = true;
		}
		assertTrue(flag);
	}
}
