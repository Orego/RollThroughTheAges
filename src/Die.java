/** A die for the Roll Through the Ages game. */
public class Die {

	/** String names for the faces of the die. */
	public static final String[] FACE_NAMES = { "food", "workers", "food / workers", "coin", "good", "skull" };

	/** The face showing 3 food. */
	public static final int FOOD = 0;
	
	/** The face showing 3 workers. */
	public static final int WORKERS = 1;
	
	/** The face showing 2 food or 2 workers. */
	public static final int FOODWORKERS = 2;
	
	/** The face showing a coin (normally worth 7 coins). */
	public static final int COIN = 3;
	
	/** The face showing a good. */
	public static final int GOOD = 4;
	
	/** The face showing a skull and two goods. */
	public static final int SKULL = 5;
	
	/** The current top face of this die. */
	private int topFace;
	
	/** True if this die would be counted as food when topFace is FOODWORKERS. */
	private boolean countAsFood;
	
	public Die() {
		countAsFood = true;
	}

	/** Rolls this die. */
	public void roll() {
		topFace = (int)(Math.random() * 6);
	}
	
	/** Returns the top face of this die. */
	public int getTopFace() {
		return topFace;
	}
	
	/** Returns true if food/worker face is counted as food. */
	public boolean isCountedAsFood(){
		return countAsFood;
	}

	/** Sets whether food/worker face is counted as food. */
	public void countAsFood(boolean value){
		countAsFood = value;
	}

	@Override
	public String toString() {
		return FACE_NAMES[topFace];
	}
	
	public static void main(String[] args) {
		Die d = new Die();
		for (int i = 0; i < 10; i++) {
			d.roll();
			System.out.println(d);
		}
	}

}
