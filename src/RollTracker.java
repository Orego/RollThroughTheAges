import java.util.ArrayList;
import java.util.List;

/** Rolls dice and counts all goods, skulls, workers, food, and coins. */
public class RollTracker {
	
	/** The amount of food most recently rolled. */
	private int food;
	
	/** The amount of goods most recently rolled. */
	private int goods;
	
	/** The amount of skulls most recently rolled. */
	private int skulls;
	
	/** The amount of workers most recently rolled. */
	private int workers;
	
	/** The amount of coin most recently rolled. */
	private int coin;
	
	/**
	 * Rolls the given list of dice.
	 * @param dice The list of dice.
	 */
	public void rollDice(List<Die> dice){
		food = 0;
		goods = 0;
		skulls = 0;
		workers = 0;
		coin = 0;
		for (int i = 0; i<dice.size(); i++){
			dice.get(i).roll();
			switch (dice.get(i).getTopFace()){
			case Die.FOOD:
				food += 3;
				break;
			case Die.COIN:
				coin += 7;
				break;
			case Die.FOODWORKERS:
				if (dice.get(i).isCountedAsFood()) food += 2;
				else workers += 2;
				break;
			case Die.GOOD:
				goods += 1;
				break;
			case Die.SKULL:
				skulls += 1;
				goods += 2;
				break;
			case Die.WORKERS:
				workers += 3;
				break;
			}
		}
	}
	
	/**
	 * @return The amount of food most recently rolled.
	 */
	public int getFood(){
		return food;
	}
	
	/**
	 * @return The amount of goods most recently rolled.
	 */
	public int getGoods(){
		return goods;
	}
	
	/**
	 * @return The amount of skulls most recently rolled.
	 */
	public int getSkulls(){
		return skulls;
	}
	
	/**
	 * @return The amount of workers most recently rolled.
	 */
	public int getWorkers(){
		return workers;
	}
	
	/**
	 * @return The amount of coin most recently rolled.
	 */
	public int getCoin(){
		return coin;
	}

	public static void main(String[] args){
		RollTracker roll = new RollTracker();
		ArrayList<Die> dice = new ArrayList<Die>();
		for (int i = 0; i < 6; i++){
			dice.add(new Die());
		}
		roll.rollDice(dice);
		System.out.println("Here are the dice rolled:");
		for (int i = 0; i < dice.size(); i++){
			System.out.println(dice.get(i));
		}
		System.out.println("TOTALS:");
		System.out.println("Goods = " + roll.getGoods());
		System.out.println("Workers = "+ roll.getWorkers());
		System.out.println("Coin = " + roll.getCoin());
		System.out.println("Skulls = " + roll.getSkulls());
		System.out.println("Food = " + roll.getFood());
	}

}
