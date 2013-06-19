package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.PlayerResources;

public class ResourcesPanel extends JPanel {

	private static final ImageIcon[] RESOURCE_IMAGES = {
			new ImageIcon("Images/wood.jpg"),
			new ImageIcon("Images/stone.jpg"),
			new ImageIcon("Images/pottery.jpg"),
			new ImageIcon("Images/cloth.jpg"),
			new ImageIcon("Images/spearhead.jpg"),
			new ImageIcon("Images/food2.jpg") };

	private JLabel[] amountLabel, worthLabel;

	private JLabel totalAmount, totalWorth;

	public ResourcesPanel() {
		amountLabel = new JLabel[6];
		worthLabel = new JLabel[6];
		layoutGUI();
	}

	private void layoutGUI() {
		this.setLayout(new GridLayout(0, 3, 3, 3));// rows, columns
		setPreferredSize(new Dimension(60 * 3, 40 * 7));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));// top,left,bottom,right

		this.add(new JLabel("Goods:"));
		this.add(new JLabel("Amount:"));
		this.add(new JLabel("Worth:"));
		for (int i = 4; i >= 0; i--) {
			this.add(new JLabel(RESOURCE_IMAGES[i]));
			amountLabel[i] = new JLabel("0");
			this.add(amountLabel[i]);
			worthLabel[i] = new JLabel("0");
			this.add(worthLabel[i]);
		}
		this.add(new JLabel("Total:"));
		totalAmount = new JLabel("0");
		totalWorth = new JLabel("0");
		this.add(totalAmount);
		this.add(totalWorth);
		this.add(new JLabel(RESOURCE_IMAGES[5]));
		amountLabel[PlayerResources.FOOD] = new JLabel("3");
		this.add(amountLabel[PlayerResources.FOOD]);
		worthLabel[PlayerResources.FOOD] = new JLabel();
		this.add(worthLabel[PlayerResources.FOOD]);

	}

	// private class JLabel2 extends JLabel{
	// private JLabel2(){
	// super();
	// setup();
	// }
	//
	// private JLabel2(String text){
	// super(text);
	// setup();
	// }
	//
	//
	// }

	public void updateResources(int[] amount, int[] worth, boolean granaries) {
		int totalAmount = 0, totalWorth = 0;
		for (int i = 0; i < 6; i++) {
			if (i != PlayerResources.FOOD) {
				totalWorth += worth[i];
				totalAmount += amount[i];
			}
			amountLabel[i].setText("" + amount[i]);
			if (i != PlayerResources.FOOD || granaries)
				worthLabel[i].setText("" + worth[i]);
		}
		this.totalAmount.setText("" + totalAmount);
		this.totalWorth.setText("" + totalWorth);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ResourcesPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
