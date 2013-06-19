package model;

/**
 * This class keeps track of a player's resources
 */
public class PlayerResources {

	/** The resource food. */
	public static final int FOOD = 5;

	/** The resource wood. */
	public static final int WOOD = 0;

	/** The resource stone. */
	public static final int STONE = 1;

	/** The resource pottery. */
	public static final int POTTERY = 2;

	/** The resource cloth. */
	public static final int CLOTH = 3;

	/** The resource spearheads. */
	public static final int SPEARHEADS = 4;

	/** The amount of each resource a player has. */
	private int[] resourceArray;

	/** String names for the resource types. */
	public static final String[] RESOURCE_NAMES = { "wood", "stone", "pottery",
			"cloth", "spearheads", "food" };

	/** Sets up the initial resources. */
	public PlayerResources() {
		resourceArray = new int[6];
		resourceArray[FOOD] = 3;
	}

	/**
	 * @param resourceType
	 *            The type of resource, e.g. pottery or food
	 * @return How much of it the player has
	 */
	public int getAmount(int resourceType) {
		return resourceArray[resourceType];
	}

	/**
	 * Changes amount of good; returns by how much the change is over or
	 * negative.
	 */
	public int changeAmount(int typeGood, int increment) {
		int newAmt = resourceArray[typeGood] + increment;
		if (newAmt < 0) {
			resourceArray[typeGood] = 0;
			return newAmt;
		}
		if (typeGood == FOOD) {
			if (newAmt > 15) {
				resourceArray[FOOD] = 15;
				return newAmt - 15;
			}
		} else if (newAmt > 8 - typeGood) {
			resourceArray[typeGood] = 8 - typeGood;
			return newAmt - (8 - typeGood);
		}

		resourceArray[typeGood] += increment;
		return 0;
	}
	
	/** Returns the worth of the type of good.  If food, assumes granaries development applies. */
	public int worthOf(int typeGood){
		if (typeGood == FOOD)
			return resourceArray[typeGood]*4;
		return (typeGood+1)*(resourceArray[typeGood]*resourceArray[typeGood]+resourceArray[typeGood])/2;
	}

	public static void main(String[] args) {
		PlayerResources r = new PlayerResources();
		for (int i = 0; i <= 5; i++) {
			System.out.println(RESOURCE_NAMES[i] + " " + r.getAmount(i));
		}
	}

	// by type
	// public int getValue(int resourceType){
	// return -1;
	// }

	// doesn't include food
	// public int getTotalGoods()
	// {
	// return -1;
	// }

	// public int getTotalValue(){
	// return -1;
	// }
}
