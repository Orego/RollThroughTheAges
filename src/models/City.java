package models;

public class City {
	private int maxPop, curPop;

	public City(int maxPopulation) {
		maxPop = maxPopulation;
	}

	public boolean isFull() {
		return curPop == maxPop;
	}

	/**
	 * Adds workers or returns the amount of workers over the max population of
	 * the city. Precondition: numWorkersToAdd is positive.
	 */
	public int addWorkers(int numWorkersToAdd) {
		if (numWorkersToAdd > (maxPop - curPop)) {
			numWorkersToAdd -= maxPop - curPop;
			curPop = maxPop;
			return numWorkersToAdd;
		}
		curPop += numWorkersToAdd;
		return 0;
	}

	public int currentPopulation() {
		return curPop;
	}

	public int maxPopulation() {
		return maxPop;
	}
}
