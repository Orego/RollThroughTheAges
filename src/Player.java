import java.util.ArrayList;

public class Player {
	private City[] cities = new City[7];
	private PlayerResources resources = new PlayerResources();

	// private ArrayList<Development> developments;

	public Player() {
		initializeCities();
	}

	/**
	 * Set each player starts with 7 total cities with 3 full and 4 with the
	 * correct number of max workers
	 */
	private void initializeCities() {
		cities[0] = new City(0);
		cities[1] = new City(0);
		cities[2] = new City(0);
		cities[3] = new City(3);
		cities[4] = new City(4);
		cities[5] = new City(5);
		cities[6] = new City(6);
	}

	/** Returns the number of full cities */
	public int getNumCities() {
		int count = 0;
		for (City c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}

	/** Return the city with this index */
	public City getCity(int i) {
		return cities[i];
	}

	public void buyCityWorkers(int workers, int city) {
		cities[city].addWorkers(workers);
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
	
	public static void main(String[] args) {
		Player p = new Player();
		System.out.println(p.getResourcesInfo());
		p.addGoods(3);
		System.out.println("Added 3 goods.");
		System.out.println(p.getResourcesInfo());
		p.addGoods(7);
		System.out.println("Added 7 goods.");
		System.out.println(p.getResourcesInfo());
		p.addGoods(13);
		System.out.println("Added 13 goods.");
		System.out.println(p.getResourcesInfo());
		p.addGoods(10);
		System.out.println("Added 10 goods.");
		System.out.println(p.getResourcesInfo());
	}
}
