public class PlayerResources {
	public static final int FOOD = 5, WOOD = 0, STONE = 1, POTTERY = 2,
			CLOTH = 3, SPEARHEADS = 4;
	private int[] resourceArray = new int[6];

	public PlayerResources() {
		resourceArray[FOOD] = 3;
	}

	// eg, r.getAmount(PlayerResources.WOOD);
	public int getAmount(int resourceType) {
		return resourceArray[resourceType];
	}

	// no check to make sure legal by rules of game
	public void changeAmount(int typeGood, int increment) {
		resourceArray[typeGood] += increment;
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
