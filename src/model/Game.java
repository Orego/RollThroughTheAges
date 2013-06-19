package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** This class keeps track of game states and coordinates interactions among classes. */
public class Game {
	
	/** The list of players. */
	private ArrayList<Player> players;
	
	/** holds whose turn it is */
	private int currentplayer;
	
	/**
	 * 
	 * @param playerNames An array of player names
	 */
	public Game(String[] playerNames) {
		
		players = new ArrayList<Player>();
		for(int i = 0; i < playerNames.length; i++) {
			players.add(new Player(playerNames[i].trim()));
		}
		
		
	}
	
	/** Returns information about players 1,2,3, or 4. */
	public Player getPlayer(int i) {
		return players.get(i-1);
	}
	

	/** Get the total number of players in the game */
	public int getNumPlayers() {
		return players.size();
	}
	
	/** Return who's turn is it?*/
	public int getCurrentPlayer(){
		return currentplayer; 
	}
	
	/** Ends the current turn & starts next one */
	public void nextTurn(){
		currentplayer = (currentplayer + 1)%(players.size());
	}

	public DevelopmentList getPlayersDevelopmentList(int player){
		return players.get(player).getDevelopementList();
	}
	
	/** Returns a players dice list */
	public List<Die> getPlayersDice(int player){
		return players.get(player).getPlayersDice();
	}
	
	
	/**
	 * Do the first roll for a player
	 * @param player the index of the player
	 * @return the list of dice
	 */
	public List<Die> rollPlayersDice(int player){
		players.get(player).doFirstRoll();
		return players.get(player).getPlayersDice();
	}
	
	/** 
	 * Rerolls the players dice
	 * @param player the index of the player
	 * @param indices the list of indices of the dice
	 * @return the list of dice
	 */
	public List<Die> rerollPlayersDice(int player,List<Integer> indices){
		players.get(player).rerollDice(indices);
		return players.get(player).getPlayersDice();
	}
	
	/** Returns the number of rerolls a player has left */
	public int getPlayersNumRerolls(int player){
		return players.get(player).getRerollsLeft();
	}
	
	/** Returns the player names in a string separated by commas. */
	public String getPlayerNames(){
		String str = "";
		for (int i = 0; i<players.size()-1; i++){
			str+=players.get(i).getName()+", ";
		}
		str += players.get(players.size()-1).getName();
		return str;
	}
	
	public static void main(String[] args){
		System.out.println("choose number of players [1-4]: ");
		Scanner scan = new Scanner(System.in);
		int numPlayers = scan.nextInt();
		while (numPlayers<1 || numPlayers>4) {
			System.out.println("out of bounds please choose a number from 1-4");
			numPlayers = scan.nextInt();
		}
		scan = new Scanner(System.in);
		System.out.println("Please enter each name on a new line:");
		String[] playerNames = new String[numPlayers];
		for (int i=0; i<numPlayers; i++){
			playerNames[i] = scan.nextLine();
		}
		
		Game g = new Game(playerNames);
		System.out.println("this game has the following number of player(s): " + g.getNumPlayers());
		System.out.println("Their names are "+g.getPlayerNames());
	}
}
