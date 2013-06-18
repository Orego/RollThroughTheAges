import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/** The class that displays a single die. */
public class GraphicDie extends JButton {

	/** The array of possible die face images. */
	private static final ImageIcon[] DIE_IMAGES = { 
		new ImageIcon("Images/food.jpg"),
		new ImageIcon("Images/workers.jpg"),
		new ImageIcon("Images/foodworkers.jpg"),
		new ImageIcon("Images/coin.jpg"),
		new ImageIcon("Images/goods.jpg"),
		new ImageIcon("Images/skull.jpg") };

	/** 
	 * Sets up the die.
	 * @param initialTopFace The initial top face.
	 * @param inUse Whether or not the die can be rolled.
	 */
	public GraphicDie(int initialTopFace, boolean inUse) {
		setIcon(DIE_IMAGES[initialTopFace]);
		System.out.println(DIE_IMAGES[initialTopFace]);
		this.setInUse(inUse);
		this.setPreferredSize(new Dimension(86,83));
	}
	
	/**
	 * Updates the face to display.
	 * @param newTopFace The new face.
	 */
	public void changeFace(int newTopFace){
		this.setIcon(DIE_IMAGES[newTopFace]);
	}
	
	/**
	 * Updates whether or not the die can be rolled
	 * @param inUse Whether or not the die can be rolled
	 */
	public void setInUse(boolean inUse){
		this.setEnabled(inUse);
	}
}
