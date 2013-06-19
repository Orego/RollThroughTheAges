package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerResources;
/** This class displays resources graphically. */
public class ResourcesPanel extends JPanel {

	/** The images associated with the different resources. */
	private static final ImageIcon[] RESOURCE_IMAGES = {
			new ImageIcon("Images/wood.jpg"),
			new ImageIcon("Images/stone.jpg"),
			new ImageIcon("Images/pottery.jpg"),
			new ImageIcon("Images/cloth.jpg"),
			new ImageIcon("Images/spearhead.jpg"),
			new ImageIcon("Images/food2.jpg") };

	/** These arrays display the amount and worth of goods in each category. */
	private JLabel[] amountLabel, worthLabel;

	/** The total amount of goods. */
	private JLabel totalAmount;
	
	/** The worth of total goods. */
	private JLabel totalWorth;

	public ResourcesPanel() {
		amountLabel = new JLabel[6];
		worthLabel = new JLabel[6];
		layoutGUI();
	}

	/** Lays out the components of the panel. */
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

	/** Updates the amount and worth of resources. */
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
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(60 * 3, 40 * 8));
		ResourcesPanel rp = new ResourcesPanel();
		panel.add(rp, BorderLayout.CENTER);
		JButton changeAndUpdate = new JButton("+1 resource to each");
		changeAndUpdate.addActionListener(rp.new ButtonListener());
		panel.add(changeAndUpdate,BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class ButtonListener implements ActionListener {
		private int clickCounter;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			clickCounter++;
			int temp = clickCounter*clickCounter+clickCounter;
			temp/=2;
			updateResources(
					new int[] { clickCounter, clickCounter, clickCounter,
							clickCounter, clickCounter, 3 + clickCounter },
					new int[] { temp,temp*2,temp*3,temp*4,temp*5,clickCounter*4}, false);
		}

	}

}
