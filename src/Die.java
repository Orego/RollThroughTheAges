public class Die {

	public static final String[] FACE_NAMES = { "food", "workers", "food / workers", "coin", "good", "skull" };

	public static final int FOOD = 0;
	public static final int WORKERS = 1;
	public static final int FOODWORKERS = 2;
	public static final int COIN = 3;
	public static final int GOOD = 4;
	public static final int SKULL = 5;
	
	private int topFace;
	private boolean countAsFood = true;
	
	public void roll() {
		topFace = (int)(Math.random() * 6);
	}
	
	public int getTopFace() {
		return topFace;
	}
	
	public boolean isCountedAsFood(){
		return countAsFood;
	}
	
	public void countAsWorkers(){
		countAsFood = false;
	}
	
	public void countAsFood(){
		countAsFood = true;
	}

	@Override
	public String toString() {
		return FACE_NAMES[topFace];
	}
	
	public static void main(String[] args) {
		Die d = new Die();
		for (int i = 0; i < 10; i++) {
			d.roll();
			System.out.println(d);
		}
	}

}
