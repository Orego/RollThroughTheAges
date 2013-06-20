package model;

public class Structure {
	private int maxPop, curPop, maxScore;
	private boolean otherPlayerHasFinished;

	public Structure(int maxPopulation, int maxScore) {
		maxPop = maxPopulation;
		otherPlayerHasFinished = false;
		this.maxScore = maxScore;
	}

	public boolean isFull() {
		return curPop == maxPop;
	}
	
	public void setOtherPlayerHasFinished(boolean finished){
		otherPlayerHasFinished = finished;
	}
	
	public boolean getOtherPlayerHasFinished(){
		return otherPlayerHasFinished;
	}
	
	/** Returns points for completion--zero if not finished yet (or for 1st monument, 2nd person). */
	public int getScore(){
		if (curPop<maxPop)
			return 0;
		if (otherPlayerHasFinished)
			return maxScore/2;
		return maxScore;
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
	
	public String toString(){
		return "Max pop: "+maxPop+"; Cur pop: "+curPop+"; Score: "+getScore();
	}
}
