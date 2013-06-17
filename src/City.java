
public class City {
	private int maxPop, curPop;
	
	public City(int maxPopulation){
		maxPop = maxPopulation;
	}
	
	public boolean isFull(){
		return curPop == maxPop;
	}
	
	//doesn't check that this is possible
	public void addWorkers(int numWorkersToAdd) {
		curPop+=numWorkersToAdd;
	}
	
	public int currentPopulation(){
		return curPop;
	}
	
	public int maxPopulation(){
		return maxPop;
	}
}
