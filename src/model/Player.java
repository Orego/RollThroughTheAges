package model;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/** This class stores information about a single player. */
public class Player {
	
	/** The cities associated with this player. */
	private City[] cities = new City[7];
	
	/** The resources associated with this player. */
	private PlayerResources resources = new PlayerResources();

	/** The player's name. */
	private String name;
	
	/** Holds the list of dice that the player is allowed to use */
	private ArrayList<Die> dice;
	
	/** Holds the rolltracker for the dice */
	private RollTracker roller;
	
	/** Holds the list of developments the plaayer holds */
	private DevelopmentList developments;

	/**
	 * @param name The player's name
	 */
	public Player(String name) {
		this.name = name;
		initializeCities();
		dice = new ArrayList<Die>();
		for (int i = 0; i < 3; i++){
			dice.add(new Die());
		}
		roller = new RollTracker();
		developments = new DevelopmentList();
	}
	
	 /** Returns the player's name. */
	public String getName(){
		return name;
	}
	
	/** Returns the player's list of developments */
	public DevelopmentList getDevelopementList(){
		return developments;
	}

	/**
	 * Set each player starts with 7 total cities with 3 full and 4 with the
	 * correct number of max workers
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
	
	/** Returns the number of full cities */
	public int getNumCities() {
		int count = 0;
		for (City c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}

	/** Return the city with this index */
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
	public int buyCityWorkers(int workers, int city) {
		return cities[city].addWorkers(workers);
	}

	/** Add the specified number of goods to the player's resources. */
	public void addGoods(int numGoods) {
		int skipCounter = 0, goodTypeCounter = 0, returnValue = 0;
		while (numGoods > 0 && skipCounter < 5) {
			returnValue = resources.changeAmount(goodTypeCounter % 5, 1);
			if (returnValue == 0) {
				numGoods--;
				skipCounter = 0;
			} else
				skipCounter++;
			goodTypeCounter++;
		}
	}
	
	/**
	 * @param amtFood Amount food to add.
	 * @return The amount of food over the max or under the min.
	 */
	public int addFood(int amtFood){
		return resources.changeAmount(PlayerResources.FOOD, amtFood);
	}

	/** Get a visual representation of the player's resources. */
	public String getResourcesInfo() {
		return "Resources:\nSpearheads: "
				+ resources.getAmount(PlayerResources.SPEARHEADS) + "\nCloth: "
				+ resources.getAmount(PlayerResources.CLOTH) + "\nPottery: "
				+ resources.getAmount(PlayerResources.POTTERY) + "\nStone: "
				+ resources.getAmount(PlayerResources.STONE) + "\nWood: "
				+ resources.getAmount(PlayerResources.WOOD) + "\nFood: "
				+ resources.getAmount(PlayerResources.FOOD);
	}
	
	/** Returns the amount of food the player has */
	public int getFood(){
		return resources.getAmount(PlayerResources.FOOD);
	}

	public static void main(String[] args) {
		Player p = new Player("");
		System.out.println(p.getResourcesInfo());
		System.out.println();
		p.addGoods(3);
		System.out.println("Added 3 goods.");
		System.out.println(p.getResourcesInfo());
		System.out.println();
		p.addGoods(7);
		System.out.println("Added 7 goods.");
		System.out.println(p.getResourcesInfo());
		System.out.println();
		p.addGoods(13);
		System.out.println("Added 13 goods.");
		System.out.println(p.getResourcesInfo());
		System.out.println();
		p.addGoods(10);
		System.out.println("Added 10 goods.");
		System.out.println(p.getResourcesInfo());
		
		p = new Player("");
		System.out.println("\nInitial food: "+p.getFood());
		p.addFood(7);
		System.out.println("Added 7 food: "+p.getFood());
		p.addFood(10);
		System.out.println("Added 10 food (max total is 15): "+p.getFood());
		
		p = new Player("");
		System.out.println("\nThere should be three cities initially: "+p.getNumCities());
		System.out.println("Is City #1 full? " + p.getCity(0).isFull());
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		System.out.println("Num workers left after we add 4 workers to city #4 (max population = 3)? " + p.getCity(3).addWorkers(4));
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		
		Scanner scan = new Scanner (System.in);
		System.out.print("\nChoose a player name: ");
		p = new Player(scan.nextLine());
		System.out.println("Hello player "+p.getName());
	}
}
