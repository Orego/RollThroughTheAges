package view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import model.Die;

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

	/** The selected border. */
	private static final Border selectedBorder = BorderFactory
			.createCompoundBorder(
					BorderFactory.createLineBorder(Color.BLACK, 1),
					BorderFactory.createLineBorder(Color.RED, 2));

	/** The int corresponding to the visible face. */
	private int topFace;
	
	private boolean foodNotWorkers;

	/**
	 * Sets up the die.
	 * 
	 * @param initialTopFace
	 *            The initial top face.
	 * @param inUse
	 *            Whether or not the die can be rolled.
	 */
	public GraphicDie(int initialTopFace, boolean inUse) {
		topFace = initialTopFace;
		setIcon(DIE_IMAGES[initialTopFace]);
		this.setInUse(inUse);
		this.setPreferredSize(new Dimension(86, 83));
		this.addActionListener(new SelectedListener());
		this.setBorder(null);
		foodNotWorkers = true;
	}

	/**
	 * Updates the face to display.
	 * 
	 * @param newTopFace
	 *            The new face.
	 */
	public void changeFace(int newTopFace) {
		topFace = newTopFace;
		this.setIcon(DIE_IMAGES[newTopFace]);
		this.setBorder(null);
	}

	/**
	 * Updates whether or not the die can be rolled
	 * 
	 * @param inUse
	 *            Whether or not the die can be rolled
	 */
	public void setInUse(boolean inUse) {
		this.setEnabled(inUse);
		if (!inUse)
			this.setBorder(null);
	}

	/**
	 * Toggles the border when the button is pressed.
	 */
	private class SelectedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (topFace != Die.SKULL)
				toggleBorder();
		}
	}

	/**
	 * Toggles the border between nothing and selected.
	 */
	private void toggleBorder() {
		if (this.getBorder() == null)
			this.setBorder(selectedBorder);
		else
			this.setBorder(null);
	}

	/**
	 * True if graphical die is outlined/selected.
	 * 
	 * @return
	 */
	public boolean dieIsSelected() {
		return this.getBorder() != null;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Stroke s = g2.getStroke();
		g2.setColor(new Color(0,255,0,150));
		g2.setStroke(new BasicStroke(3));
		if (topFace == Die.FOODWORKERS){
			if (foodNotWorkers){//highlight the left
				g2.drawRect(1, 2, 42, 81-5);
			}else{
				g2.drawRect(42+2, 2, 42-5, 81-5);
			}
		}
		g2.setStroke(s);
	}
	
	public boolean interpretAsFood(){
		return foodNotWorkers;
	}
}
