public class Die {

	private int topFace;
	
	public void roll() {
		topFace = (int)(Math.random() * 6);
	}
	
	public int getTopFace() {
		return topFace;
	}

}
