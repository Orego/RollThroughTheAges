public class Die {

	public static final String[] FACE_NAMES = { "food", "workers", "food / workers", "coin", "good", "skull" };

	private int topFace;
	
	public void roll() {
		topFace = (int)(Math.random() * 6);
	}
	
	public int getTopFace() {
		return topFace;
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
