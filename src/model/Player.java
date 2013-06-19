package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/** This class stores information about a single player. */
public class Player {

	/** The cities associated with this player. */
	private Structure[] cities;

	/** The monuments associated with the player. */
	private Structure[] monuments;

	/** The resources associated with this player. */
	private PlayerResources resources = new PlayerResources();

	/** The player's name. */
	private String name;

	/** Holds the list of dice that the player is allowed to use */
	private ArrayList<Die> dice;

	/** Holds the rolltracker for the dice */
	private RollTracker roller;

	/** The player's total score. */
	private int totalScore;

	/** The player's disaster count. */
	private int disasterCount;

	/** Holds the list of developments the player holds. */
	private DevelopmentList developments;

	/**
	 * @param name
	 *            The player's name
	 */
	public Player(String name, int numPlayers) {
		this.name = name;
		initializeCities();
		initializeMonuments(numPlayers);
		dice = new ArrayList<Die>();
		for (int i = 0; i < 3; i++) {
			dice.add(new Die());
		}
		roller = new RollTracker();
		developments = new DevelopmentList();
	}

	/** Returns the player's total score. */
	public int getTotalScore() {
		return developments.getDevelopmentsTotalScore() + getMonumentsScores()
				+ getBonuses() - disasterCount;
	}

	/** Returns the score from bonuses (gotten through architecture/empire developments). */
	private int getBonuses() {
		int bonus = 0;
		if (developments.isDevelopmentBought(DevelopmentList.ARCHITECTURE)) {
			for (int i = 0; i < monuments.length; i++) {
				if (monuments[i].isFull()) {
					bonus++;
				}
			}
		}
		if (developments.isDevelopmentBought(DevelopmentList.EMPIRE)) {
			for (int i = 0; i < cities.length; i++) {
				if (cities[i].isFull()) {
					bonus++;
				}
			}
		}
		return bonus;
	}
	
	public void buyDevelopment(int development){
		developments.buyDevelopment(development);
	}

	/** Returns the score from monuments built. */
	private int getMonumentsScores() {
		int i2 = 0;
		for (int i = 0; i < monuments.length; i++) {
			i2 += monuments[i].getScore();
		}
		return i2;
	}
	
	/** Updates disaster count. */
	public void addDisasters(int numDisastersToAdd){
		disasterCount+=numDisastersToAdd;
	}

	/**
	 * Sets up the right monuments depending on number of players. For 1-player
	 * game, ignores all occasionally blocked monuments.
	 */
	private void initializeMonuments(int numPlayers) {
		monuments = new Structure[3 + numPlayers];

		monuments[0] = new Structure(3, 1);
		int backwards = 0;
		for (int i = 1; i < 7; i++) {
			if ((numPlayers < 3 && (i == 2 || i == 6))
					|| (numPlayers % 2 != 0 && i == 4)) {
				backwards++;
				continue;
			}
			monuments[i - backwards] = new Structure(3 + 2 * i, 2 * i);
		}
	}

	/** Returns the player's name. */
	public String getName() {
		return name;
	}

	/** Returns the player's list of developments */
	public DevelopmentList getDevelopementList() {
		return developments;
	}

	/**
	 * Set each player starts with 7 total cities with 3 full and 4 with the
	 * correct number of max workers
	 */
	private void initializeCities() {
		cities = new Structure[7];
		cities[0] = new Structure(0, 0);
		cities[1] = new Structure(0, 0);
		cities[2] = new Structure(0, 0);
		cities[3] = new Structure(3, 0);
		cities[4] = new Structure(4, 0);
		cities[5] = new Structure(5, 0);
		cities[6] = new Structure(6, 0);
	}

	/** Returns the list of dice that the player can roll */
	public List<Die> getPlayersDice() {
		return dice;
	}

	/** Does the first roll of the players turn */
	public void doFirstRoll() {
		roller.rollDice(dice);
	}

	/**
	 * Reroll the dice
	 * 
	 * @param diceToRoll
	 *            the indices of the dice
	 * @return true if reroll was possible.
	 */
	public boolean rerollDice(List<Integer> diceToRoll) {
		return roller.reroll(diceToRoll, dice);
	}

	/** Get the rerolls left */
	public int getRerollsLeft() {
		return roller.getRerollsLeft();
	}

	/** Adds a die to the players dice list */
	public void addDie() throws AddedTooManyDiceException {
		if (dice.size() >= 7)
			throw new AddedTooManyDiceException();
		else
			dice.add(new Die());
	}

	/** Thrown when program tries to add more than 7 dice */
	private class AddedTooManyDiceException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	/** Returns the number of full cities */
	public int getNumCities() {
		int count = 0;
		for (Structure c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}

	/** Return the city with this index */
	public Structure getCity(int i) {
		return cities[i];
	}

	/**
	 * Adds workers to a particular city NOTE: needs work....
	 * 
	 * @param workers
	 *            the amount of workers added
	 * @param city
	 *            the city number
	 */
	public int buyCityWorkers(int workers, int city) {
		return cities[city].addWorkers(workers);
	}

	public int buyMonumentWorkers(int workers, int monument) {
		return monuments[monument].addWorkers(workers);
	}

	public String getMonumentsInfo() {
		String str = "";
		for (Structure s : monuments) {
			str += s + "\n";
		}
		return str;
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
	 * @param amtFood
	 *            Amount food to add.
	 * @return The amount of food over the max or under the min.
	 */
	public int addFood(int amtFood) {
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
	public int getFood() {
		return resources.getAmount(PlayerResources.FOOD);
	}

	public static void main(String[] args) {
		Player p = new Player("", 2);
		System.out
				.println("There are 2 players, so you have 5 monuments to build:");
		System.out.println(p.getMonumentsInfo());
		System.out.println("Build the second monument:");
		p.buyMonumentWorkers(5, 1);
		System.out.println(p.getMonumentsInfo() + "\n");

		p = new Player("", 1);
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

		p = new Player("", 1);
		System.out.println("\nInitial food: " + p.getFood());
		p.addFood(7);
		System.out.println("Added 7 food: " + p.getFood());
		p.addFood(10);
		System.out.println("Added 10 food (max total is 15): " + p.getFood());

		p = new Player("", 1);
		System.out.println("\nThere should be three cities initially: "
				+ p.getNumCities());
		System.out.println("Is City #1 full? " + p.getCity(0).isFull());
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		System.out
				.println("Num workers left after we add 4 workers to city #4 (max population = 3)? "
						+ p.getCity(3).addWorkers(4));
		System.out.println("Is City #4 full? " + p.getCity(3).isFull());
		
		p = new Player("",1);
		System.out.println("\nTotal score for new player: "+p.getTotalScore());
		p.buyDevelopment(DevelopmentList.EMPIRE);
		System.out.println("Buy empire (8 + 1 bonus/city): "+p.getTotalScore());
		p.addDisasters(2);
		System.out.println("Lost two disaster points: "+p.getTotalScore());

		Scanner scan = new Scanner(System.in);
		System.out.print("\nChoose a player name: ");
		p = new Player(scan.nextLine(), 1);
		System.out.println("Hello player " + p.getName());

	}
}
