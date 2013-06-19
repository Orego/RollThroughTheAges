package models;
import java.util.ArrayList;

public class DevelopmentList {
	
	/** Indices associated with development. */
	public static final int LEADERSHIP = 0,
			IRRIGATION = 1, 
			AGRICULTURE = 2,
			QUARRYING = 3, MEDICINE = 4,
			COINAGE = 5,
			CARAVANS = 6,
			RELIGION = 7, GRANARIES = 8,
			MASONRY = 9,
			ENGINEERING = 10,
			ARCHITECTURE = 11, EMPIRE = 12;

	/** Keeps track of whether a player has a development yet or not. */
	private boolean[] developmentArray;

	/** The names associated with the developments. */
	public static final String[] DEVELOPMENT_NAMES = { "leadership",
			"irrigation", "agriculture", "quarrying", "medicine", "coinage",
			"caravans", "religion", "granaries", "masonry", "engineering",
			"architecture", "empire"
	};

	/** The costs associated with the developments. */
	public static final int[] DEVELOPMENT_COSTS = { 10, 10, 15, 15, 15, 20, 20,
			20, 30, 30, 40, 50, 60 };

	/** The points associated with the developments. */
	public static final int[] DEVELOPMENT_POINTS = { 2, 2, 3, 3, 3, 4, 4, 6, 6,
			6, 6, 8, 8 };

	public DevelopmentList() {
		developmentArray = new boolean[13];
	}

	/**
	 * indicator that returns true if player has a development, false otw usage:
	 * p.GetDevelopmentList().doesPlayerHaveDevelopment(LEADERSHIP);
	 */
	public boolean isDevelopmentBought(int developmentIndex) {
		return developmentArray[developmentIndex];
	}

	/**
	 * buys a development. Remark: player can never lose their developments
	 * usage: p.buyDevelopment(EMPIRE);
	 */
	public void buyDevelopment(int developmentIndex) {
		developmentArray[developmentIndex] = true;
	}

	/**
	 * returns list containing integers corresponding to all developments not
	 * yet purchased.
	 */
	public ArrayList<Integer> getAvailableDevelopments() {
		ArrayList<Integer> w = new ArrayList<Integer>();
		for (int i = 0; i < developmentArray.length; i++) {
			if (!developmentArray[i]) {
				w.add(i);
			}
		}
		return w;
	}

	/** Get all the info associated with this particular development. */
	public String getDevelopmentInfo(int development) {
		String str = DEVELOPMENT_NAMES[development] + " has ";
		if (!developmentArray[development])
			str += "not ";
		str += "been bought.\nIt's number is " + development + ", cost is "
				+ DEVELOPMENT_COSTS[development] + " and points are "
				+ DEVELOPMENT_POINTS[development] + ".";
		return str;
	}

	public static void main(String[] args) {
		DevelopmentList d = new DevelopmentList();
		System.out.println("Is religion bought? "
				+ d.isDevelopmentBought(DevelopmentList.RELIGION));
		d.buyDevelopment(DevelopmentList.COINAGE);
		System.out.println("Bought coinage.");
		System.out.println(d.getDevelopmentInfo(COINAGE));
		System.out.println("Available developments: "
				+ d.getAvailableDevelopments()
				+ "\nCoinage is 5, and should not be in this list");
	}
}
