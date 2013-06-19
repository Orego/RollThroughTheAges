package models;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	private ArrayList<Player> players;
	
	public Game(int n) {
		
		players = new ArrayList<Player>();
		for(int i = 0; i < n; i++) {
			players.add(new Player());
		}
	}
	
	// takes arguments i=1,2,3,4
	public Player getPlayer(int i) {
		return players.get(i-1);
	}
	
	public int getNumPlayers() {
		return players.size();
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
