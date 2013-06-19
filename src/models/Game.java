package models;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Contains  all the information about the state of the game */
public class Game {
	
	/** holds the list of players */
	private ArrayList<Player> players;
	
	/** holds whose turn it is */
	private int currentplayer;
	
	/** Initializes the game */
	public Game(int n) {
		//create players
		players = new ArrayList<Player>();
		for(int i = 0; i < n; i++) {
			players.add(new Player());
		}
		
		
	}
	
	/** Gets the player at the given index */
	// takes arguments i=1,2,3,4
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
	
	public static void main(String[] args){
		System.out.println("choose number of players [1-4]: ");
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		while (s<1 || s>4) {
			System.out.println("out of bounds please choose a number from 1-4");
			s = in.nextInt();
		}
		Game g = new Game(s);
		System.out.println("this game has the following number of players " + g.getNumPlayers());
	}
}
