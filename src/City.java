
public class City {
	private int maxPop, curPop;
	
	public City(int maxPopulation){
		maxPop = maxPopulation;
	}
	
	public boolean isFull(){
		return curPop == maxPop;
	}
	
	//doesn't check that this is possible
	public boolean addWorkers(int numWorkersToAdd) {
		if (numWorkersToAdd > (maxPop - curPop)) {
			System.out.println("Warning: workers not added - tried to add more workers than remaining slots in city");
			return false;
		}
		curPop+=numWorkersToAdd;
		return true;		
	}
	
	public int currentPopulation(){
		return curPop;
	}
	
	public int maxPopulation(){
		return maxPop;
	}
}
