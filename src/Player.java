public class Player {
	private City[] cities = new City[7];

	public Player() {
		initializeCities();
	}

	private void initializeCities() {
		cities[0] = new City(0);
		cities[1] = new City(0);
		cities[2] = new City(0);
		cities[3] = new City(3);
		cities[4] = new City(4);
		cities[5] = new City(5);
		cities[6] = new City(6);

	}

	public int getNumCities() {
		int count = 0;
		for (City c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}

//	public void buyCityWorkers(int numWorkers) {
//		// TODO Auto-generated method stub
//
//	}
}
