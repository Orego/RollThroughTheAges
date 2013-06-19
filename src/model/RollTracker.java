package model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

/** Rolls dice and counts all goods, skulls, workers, food, and coins. */
public class RollTracker {

	/** The amount of food most recently rolled. */
	private int food;

	/** The amount of goods most recently rolled. */
	private int goods;

	/** The amount of skulls most recently rolled. */
	private int skulls;

	/** The amount of workers most recently rolled. */
	private int workers;

	/** The amount of coin most recently rolled. */
	private int coin;

	/** The number of rerolls since last roll. */
	private int rerollCount;

	/**
	 * Rolls the given list of dice.
	 * 
	 * @param dice
	 *            The list of dice.
	 */
	public void rollDice(List<Die> dice) {
		food = 0;
		goods = 0;
		skulls = 0;
		workers = 0;
		coin = 0;
		rerollCount = 0;
		for (int i = 0; i < dice.size(); i++) {
			dice.get(i).roll();
			updateValueFromFace(dice.get(i).getTopFace(), dice.get(i)
					.isCountedAsFood(), true);
		}
	}

	/**
	 * Adds or subtracts value of face.
	 * 
	 * @param topFace
	 *            Which face is showing.
	 * @param countAsFood
	 *            Whether the die counts as food if the food/worker face is
	 *            showing.
	 * @param add
	 *            True if add, false if subtract.
	 */
	private void updateValueFromFace(int topFace, boolean countAsFood,
			boolean add) {
		int unit = -1;
		if (add)
			unit = 1;
		switch (topFace) {
		case Die.FOOD:
			food += unit * 3;
			break;
		case Die.COIN:
			coin += unit * 7;
			break;
		case Die.FOODWORKERS:
			if (countAsFood)
				food += unit * 2;
			else
				workers += unit * 2;
			break;
		case Die.GOOD:
			goods += unit * 1;
			break;
		case Die.SKULL:
			skulls += unit * 1;
			goods += unit * 2;
			break;
		case Die.WORKERS:
			workers += unit * 3;
			break;
		}
	}
	
	/** The number of rerolls left. */
	public int getRerollsLeft(){
		return 2-rerollCount;
	}

	/**
	 * Rerolls the dice at the indices indicated by the list of indices. Note:
	 * leadership is not taken into account, so last roll would be ignored. 
	 * 
	 * @param indices
	 * @param dice
	 * @return Returns true if reroll was possible
	 */
	public boolean reroll(List<Integer> indices, List<Die> dice) throws FlippedSkullException {
		if (rerollCount > 1 || indices == null || indices.isEmpty())// rerollCount will be 0 1st
													// time and 1 2nd
			return false;
		
		if (rerollContainsSkulls(indices,dice))
			throw new FlippedSkullException();

		rerollCount++;

		Die die;
		for (int i = 0; i < indices.size(); i++) {
			die = dice.get(indices.get(i));
			// subtract original value
			updateValueFromFace(die.getTopFace(), die.isCountedAsFood(), false);
			// reroll that particular die
			die.roll();
			updateValueFromFace(die.getTopFace(), die.isCountedAsFood(), true);
		}
		return true;
	}
	
	private class FlippedSkullException extends RuntimeException {}
	
	/**
	 * Checks whether a reroll would flip skulls
	 * @param indices
	 * @param dice
	 * @return
	 */
	public boolean rerollContainsSkulls(List<Integer> indices, List<Die> dice){
		for (int i = 0; i < indices.size(); i++)
			if (dice.get(indices.get(i)).getTopFace() == Die.SKULL)
				return true;
		return false;
	}

	/**
	 * @return The amount of food most recently rolled.
	 */
	public int getFood() {
		return food;
	}

	/**
	 * @return The amount of goods most recently rolled.
	 */
	public int getGoods() {
		return goods;
	}

	/**
	 * @return The amount of skulls most recently rolled.
	 */
	public int getSkulls() {
		return skulls;
	}

	/**
	 * @return The amount of workers most recently rolled.
	 */
	public int getWorkers() {
		return workers;
	}

	/**
	 * @return The amount of coin most recently rolled.
	 */
	public int getCoin() {
		return coin;
	}

	public static void main(String[] args) {
		RollTracker roll = new RollTracker();
		ArrayList<Die> dice = new ArrayList<Die>();
		for (int i = 0; i < 6; i++) {
			dice.add(new Die());
		}
		roll.rollDice(dice);
		
		System.out.println("Here are the dice rolled:");
		for (int i = 0; i < dice.size(); i++) {
			System.out.println(dice.get(i));
		}
		System.out.println(roll);

		ArrayList<Integer> ints = new ArrayList<Integer>();
		Integer ranNum = (int) (Math.random() * 6);
		ints.add(ranNum);
		for (int i = 0; i < 3; i++) {
			// will give infinite loop if all dice are skulls
			while (roll.rerollContainsSkulls(ints, dice)) {
				ints.clear();
				ranNum = (int) (Math.random() * 6);
				ints.add(ranNum);
			}
			System.out.println("Rerolling die "+ranNum);
			System.out.println("Reroll occurred: " + roll.reroll(ints, dice));
			System.out.println("Here are the current dice:");
			for (int j = 0; j < dice.size(); j++) {
				System.out.println(dice.get(j));
			}
			System.out.println(roll);
		}
	}

	public String toString() {
		return "TOTALS:\nGoods = " + getGoods() + "\nWorkers = " + getWorkers()
				+ "\nCoin = " + getCoin() + "\nSkulls = " + getSkulls()
				+ "\nFood = " + getFood();
	}
}
