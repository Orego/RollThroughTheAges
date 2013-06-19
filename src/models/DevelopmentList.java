package models;
import java.util.ArrayList;



public class DevelopmentList {
	public static final int LEADERSHIP = 0, //number representing leadership development
			IRRIGATION=1, //number representing irrigation development
			AGRICULTURE=2, //and similarly for all other developments
			QUARRYING=3, MEDICINE=4, COINAGE=5, 
			CARAVANS=6, RELIGION=7, GRANARIES=8, 
			MASONRY=9, ENGINEERING=10, ARCHITECTURE=11, EMPIRE=12;
	
	private int[] developmentArray;
	
	public static final String[] DEVELOPMENT_NAMES = {
		"leadership", "irrigation", "agriculture", "quarrying",
		"medicine", "coinage", "caravans", "religion", "granaries",
		"masonry", "engineering", "architecture", "empire"
		// lets us look up the names of the developments based on the index number
	};
	
	public static final int[] DEVELOPMENT_COSTS = {
		10, 10, 15, 15, 15, 20, 20, 20, 30, 30, 40, 50, 60
	};
	
	public static final int[] DEVELOPMENT_POINTS = {
		2,2,3,3,3,4,4,6,6,6,6,8,8
	};
	
	public DevelopmentList() {
		developmentArray = new int[13];
	}

	// indicator that return 1 if player has a development, 0 otw
	// usage: p.GetDevelopmentList().doesPlayerHaveDevelopment(LEADERSHIP);
	public int isDevelopmentBought(int developmentIndex) {
		return developmentArray[developmentIndex];
	}

	// buys a development. Remark: player can never lose their developments
	// usage: p.buyDevelopment(EMPIRE);
	public void buyDevelopment(int developmentIndex) {
		developmentArray[developmentIndex] = 1;
	}
	
	// returns list containing integers corresponding to all developments not yet purchased
	public ArrayList<Integer> getAvailableDevelopments() {
		ArrayList<Integer> w = new ArrayList<Integer>();
		for(int i=0; i < developmentArray.length; i++) {
			if (developmentArray[i] == 0) {
				w.add(i);
			}
		}
		return w;
	};
}
