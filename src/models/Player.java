package models;
import java.util.ArrayList;
import java.util.List;

/** Holds information about a player */
public class Player {
	
	/** Holds the list of cities. */
	private City[] cities = new City[7];
//	private ArrayList<Development> developments;
	
	/** Holds the list of dice that the player is allowed to use */
	private ArrayList<Die> dice;
	
	private RollTracker roller;

	/** Initializes the players */
	public Player() {
		initializeCities();
		dice = new ArrayList<Die>();
		for (int i = 0; i < 3; i++){
			dice.add(new Die());
		}
		roller = new RollTracker();
	}
	
	/** Set each player starts with 7 total cities with 3 full and 4 
	 *  with the correct number of max workers
	 */
	private void initializeCities() {
		cities[0] = new City(0);
		cities[1] = new City(0);
		cities[2] = new City(0);
		cities[3] = new City(3);
		cities[4] = new City(4);
		cities[5] = new City(5);
		cities[6] = new City(6);
	}
	
	/** Returns the list of dice that the player can roll */
	public List<Die> getPlayersDice(){
		return dice;
	}
	
	/** Does the first roll of the players turn */
	public void doFirstRoll(){
		roller.rollDice(dice);
	}
	
	/**
	 * Reroll the dice
	 * 
	 * @param diceToRoll the indices of the dice
	 * @return true if reroll was possible.
	 */
	public boolean rerollDice(List<Integer> diceToRoll){
		return roller.reroll(diceToRoll, dice);
	}
	
	/** Get the rerolls left */
	public int getRerollsLeft(){
		return roller.getRerollsLeft();
	}
	
	/** Adds a die to the players dice list */
	public void addDie() throws AddedTooManyDiceException {
		if (dice.size() >= 7) throw new AddedTooManyDiceException();
		else dice.add(new Die());
	}
	
	/** Thrown when program tries to add more than 7 dice */
	private class AddedTooManyDiceException extends RuntimeException{
		
		private static final long serialVersionUID = 1L;
	}
	
	/** Returns the number of full cities*/
	public int getNumCities() {
		int count = 0;
		for (City c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}
	
	/** Return the city with this index*/
	public City getCity(int i) {
		return cities[i];
	}
	
	/** 
	 * Adds workers to a particular city
	 * NOTE: needs work....
	 * 
	 * @param workers the amount of workers added
	 * @param city the city number
	 */
	public void buyCityWorkers(int workers, int city) {
		cities[city].addWorkers(workers);
	}
}
