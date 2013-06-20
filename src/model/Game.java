package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class keeps track of game states and coordinates interactions among
 * classes.
 */
public class Game {

	/** The list of players. */
	private ArrayList<Player> players;

	/** holds whose turn it is */
	private int currentplayer;

	/** holds current position */
	private int current_turn_part;

	/** The names of the different turn parts. */
	public static final String[] TURN_PARTS = {
			"Roll dice and collect goods and food",
			"Feed cities resolve disasters",
			"Build cities and / or monumentes", "Option to buy 1 development",
			"Discard goods in excess of 6" };

	/**
	 * 
	 * @param playerNames
	 *            An array of player names
	 */
	public Game(String[] playerNames) {

		players = new ArrayList<Player>();
		for (int i = 0; i < playerNames.length; i++) {
			players.add(new Player(playerNames[i].trim(), playerNames.length));
		}

	}

	/** This calculates whether the game is over. */
	private boolean isEndofGame() {
		int[] monumentCheck = new int[getPlayer(0).getMonumentsPlayerHas().length];
		boolean[] listOfMonuments;
		for (int i = 0; i < players.size(); i++) {
			if (getPlayer(i).getDevelopementList().getAvailableDevelopments()
					.size() <= 8) {
				return true;
			}
			listOfMonuments = getPlayer(i).getMonumentsPlayerHas();
			for (int j = 0; j < listOfMonuments.length; j++) {
				if (listOfMonuments[j])
					monumentCheck[j] = 1;
			}
		}
		int sum = 0;
		for (int i = 0; i < monumentCheck.length; i++) {
			sum += monumentCheck[i];
		}
		if (sum == monumentCheck.length) {
			return true;
		}
		return false;
	}

	/** Returns information about players--zero-based. */
	public Player getPlayer(int i) {
		return players.get(i);
	}

	/** Get the total number of players in the game */
	public int getNumPlayers() {
		return players.size();
	}

	/** Return who's turn is it? */
	public int getCurrentPlayer() {
		return currentplayer;
	}

	/** Return's the part of the turn the player is on */
	public int getCurrentTurnPart() {
		return current_turn_part;
	}

	/**
	 * Ends the current turn & starts next one Returns false if the game is
	 * ended.
	 * 
	 * TODO: make this private once GUI is integrated
	 * */
	public boolean nextTurn() {
		currentplayer = (currentplayer + 1) % (players.size());
		if ((currentplayer == 0) && isEndofGame()) {
			return false;
		}
		return true;
	}

	/** This method advances to the next turn part. It returns false if the game is over. */
	public boolean nextTurnPart() {
		if (current_turn_part == 4)
			if (!nextTurn())
				return false;
		current_turn_part = (current_turn_part + 1) % 5;
		return true;
	}

	/** This method returns the specified player's development list. */
	public DevelopmentList getPlayersDevelopmentList(int player) {
		return players.get(player).getDevelopementList();
	}

	/** Returns a players dice list */
	public List<Die> getPlayersDice(int player) {
		return players.get(player).getPlayersDice();
	}

	/**
	 * Do the first roll for a player
	 * 
	 * @param player
	 *            the index of the player
	 * @return the list of dice
	 */
	public List<Die> rollPlayersDice(int player) {
		players.get(player).doFirstRoll();
		return players.get(player).getPlayersDice();
	}

	/**
	 * Rerolls the players dice
	 * 
	 * @param player
	 *            the index of the player
	 * @param indices
	 *            the list of indices of the dice
	 * @return the list of dice
	 */
	public List<Die> rerollPlayersDice(int player, List<Integer> indices) {
		players.get(player).rerollDice(indices);
		return players.get(player).getPlayersDice();
	}

	/** Returns the number of rerolls a player has left */
	public int getPlayersNumRerolls(int player) {
		return players.get(player).getRerollsLeft();
	}

	/** Returns the player names in a string separated by commas. */
	public String getPlayerNames() {
		String str = "";
		for (int i = 0; i < players.size() - 1; i++) {
			str += players.get(i).getName() + ", ";
		}
		str += players.get(players.size() - 1).getName();
		return str;
	}

	public static void main(String[] args) {
		System.out.println("choose number of players [1-4]: ");
		Scanner scan = new Scanner(System.in);
		int numPlayers = scan.nextInt();
		while (numPlayers < 1 || numPlayers > 4) {
			System.out.println("out of bounds please choose a number from 1-4");
			numPlayers = scan.nextInt();
		}
		scan = new Scanner(System.in);
		System.out.println("Please enter each name on a new line:");
		String[] playerNames = new String[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			playerNames[i] = scan.nextLine();
		}

		Game g = new Game(playerNames);
		System.out.println("this game has the following number of player(s): "
				+ g.getNumPlayers());
		System.out.println("Their names are " + g.getPlayerNames());

		System.out.println("\nPlayer " + g.getPlayer(0).getName()
				+ " buys five developments (the cheater).");
		DevelopmentList pd = g.getPlayersDevelopmentList(0);
		for (int i = 0; i < 5; i++) {
			pd.buyDevelopment(i);
		}

		for (int i = 0; i < g.getNumPlayers(); i++) {
			System.out.println("Player "
					+ g.getPlayer((i + 1) % g.getNumPlayers()).getName()
					+ " can go now: " + g.nextTurn());
		}
		System.out.println("Game over.");
		for (int i = 0; i < g.getNumPlayers(); i++) {
			System.out.println("Player " + g.getPlayer(i).getName()
					+ " has score " + g.getPlayer(i).getTotalScore());
		}
	}
}
