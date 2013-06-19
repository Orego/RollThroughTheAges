package view;
import java.awt.Color;
import java.awt.Dimension;
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
}
