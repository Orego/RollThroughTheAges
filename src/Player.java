public class Player {
	private City[] cities = new City[7];

	public Player() {
		initializeCities();
	}
	
	/** Set each player starts with 7 total cities with 3 full and 4 
	 *  with the correct number of max workers
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
	
	/** Returns the number of full cities*/
	public int getNumCities() {
		int count = 0;
		for (City c : cities) {
			if (c.isFull()) {
				count++;
			}
		}
		return count;
	}
	
	/** Return the city with this index*/
	public City getCity(int i) {
		return cities[i];
	}
	
	public void buyCityWorkers(int workers, int city) {
		cities[city].addWorkers(workers);
	}
}
