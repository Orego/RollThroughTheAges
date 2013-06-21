package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class CitiesPanel extends JPanel implements TurnObserver {

	/** The images associated with the different cities. */
	private static final ImageIcon[] CITY_IMAGES = {
			new ImageIcon("Images/city4.jpg"),
			new ImageIcon("Images/city5.jpg"),
			new ImageIcon("Images/city6.jpg"),
			new ImageIcon("Images/city7.jpg") };

	/** The images associated with the different full cities. */
	private static final ImageIcon[] CITY_IMAGES_SELECTED = {
			new ImageIcon("Images/city4selected.jpg"),
			new ImageIcon("Images/city5selected.jpg"),
			new ImageIcon("Images/city6selected.jpg"),
			new ImageIcon("Images/city7selected.jpg") };

	private JLabel[] cities;
	private JCheckBox[] checkboxes;
	private static JLabel name;
	private Game gamestate;

	private MainWindow main;

	/**
	 * This initializes all of the graphic cities with their checkboxes to
	 * determine the number of workers
	 */
	public CitiesPanel(Game game, MainWindow main) {
		gamestate = game;
		this.main = main;
		cities = new JLabel[4];
		checkboxes = new JCheckBox[18];
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setBackground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;

		ImageIcon icon = new ImageIcon("Images/city1.jpg");
		JLabel label = new JLabel(icon);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(label);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		c.gridx = 1;
		c.gridy = 0;
		this.add(panel, c);
		panel.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		panel.repaint();

		label = new JLabel(new ImageIcon("Images/city2.jpg"));
		c.gridx = 3;
		c.gridy = 0;
		this.add(label, c);

		label = new JLabel(new ImageIcon("Images/city3.jpg"));
		c.gridx = 5;
		c.gridy = 0;
		this.add(label, c);

		JPanel[] panels = new JPanel[4];

		for (int i = 0; i < 4; i++) {
			panels[i] = new JPanel();
			panels[i].setLayout(null);
			cities[i] = new JLabel(CITY_IMAGES[i]);
			panels[i].add(cities[i]);
			cities[i].setBounds(0, 0, CITY_IMAGES[i].getIconWidth(),
					CITY_IMAGES[i].getIconHeight());
			c.gridx = 2 * i;
			c.gridy = 1;
			this.add(panels[i], c);
			panels[i].setPreferredSize(new Dimension(CITY_IMAGES[i]
					.getIconWidth(), CITY_IMAGES[i].getIconHeight()));
			panels[i].repaint();
		}

		// Adding Checkboxes to the Fourth city
		checkboxes[0] = new JCheckBox();
		panels[0].add(checkboxes[0], 0);
		checkboxes[0].setBounds(20, 43, 25, 25);
		panels[0].repaint();

		checkboxes[1] = new JCheckBox();
		panels[0].add(checkboxes[1], 0);
		checkboxes[1].setBounds(8, 65, 25, 25);
		panels[0].repaint();

		checkboxes[2] = new JCheckBox();
		panels[0].add(checkboxes[2], 0);
		checkboxes[2].setBounds(30, 65, 25, 25);
		panels[0].repaint();

		// Adding Checkboxes to the Fifth city
		checkboxes[3] = new JCheckBox();
		panels[1].add(checkboxes[3], 0);
		checkboxes[3].setBounds(8, 50, 25, 25);
		panels[1].repaint();

		checkboxes[4] = new JCheckBox();
		panels[1].add(checkboxes[4], 0);
		checkboxes[4].setBounds(8, 70, 25, 25);
		panels[1].repaint();

		checkboxes[5] = new JCheckBox();
		panels[1].add(checkboxes[5], 0);
		checkboxes[5].setBounds(30, 50, 25, 25);
		panels[1].repaint();

		checkboxes[6] = new JCheckBox();
		panels[1].add(checkboxes[6], 0);
		checkboxes[6].setBounds(30, 70, 25, 25);
		panels[1].repaint();

		// Adding Checkboxes to the Sixth city

		checkboxes[7] = new JCheckBox();
		panels[2].add(checkboxes[7], 0);
		checkboxes[7].setBounds(19, 40, 25, 25);
		panels[2].repaint();

		checkboxes[8] = new JCheckBox();
		panels[2].add(checkboxes[8], 0);
		checkboxes[8].setBounds(8, 60, 25, 25);
		panels[2].repaint();

		checkboxes[9] = new JCheckBox();
		panels[2].add(checkboxes[9], 0);
		checkboxes[9].setBounds(8, 80, 25, 25);
		panels[2].repaint();

		checkboxes[10] = new JCheckBox();
		panels[2].add(checkboxes[10], 0);
		checkboxes[10].setBounds(30, 60, 25, 25);
		panels[2].repaint();

		checkboxes[11] = new JCheckBox();
		panels[2].add(checkboxes[11], 0);
		checkboxes[11].setBounds(30, 80, 25, 25);
		panels[2].repaint();

		// The checkboxes for city number Seven
		checkboxes[12] = new JCheckBox();
		panels[3].add(checkboxes[12], 0);
		checkboxes[12].setBounds(8, 50, 25, 25);
		panels[3].repaint();

		checkboxes[13] = new JCheckBox();
		panels[3].add(checkboxes[13], 0);
		checkboxes[13].setBounds(30, 50, 25, 25);
		panels[3].repaint();

		checkboxes[14] = new JCheckBox();
		panels[3].add(checkboxes[14], 0);
		checkboxes[14].setBounds(8, 70, 25, 25);
		panels[3].repaint();

		checkboxes[15] = new JCheckBox();
		panels[3].add(checkboxes[15], 0);
		checkboxes[15].setBounds(8, 90, 25, 25);
		panels[3].repaint();

		checkboxes[16] = new JCheckBox();
		panels[3].add(checkboxes[16], 0);
		checkboxes[16].setBounds(30, 70, 25, 25);
		panels[3].repaint();

		checkboxes[17] = new JCheckBox();
		panels[3].add(checkboxes[17], 0);
		checkboxes[17].setBounds(30, 90, 25, 25);
		panels[3].repaint();

		for (int i = 0; i < checkboxes.length; i++) {
			checkboxes[i].addActionListener(new CheckListener());
		}
	}

	/**
	 * It gets the selected workers from a city
	 * 
	 * @param city
	 *            (index of the city)
	 * @return
	 */
	public int getSelectedWorkersForCity(int city) {
		switch (city) {
		case 3:
			int sum = 0;
			for (int i = 0; i <= 2; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled()) {
					sum++;
				}
			}
			return sum;
		case 4:
			sum = 0;
			for (int i = 3; i <= 6; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled()) {
					sum++;
				}
			}
			return sum;

		case 5:
			sum = 0;
			for (int i = 7; i <= 11; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled()) {
					sum++;
				}
			}
			return sum;
		case 6:
			sum = 0;
			for (int i = 12; i <= 17; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled()) {
					sum++;
				}
			}
			return sum;
		default:
			return 0;
		}
	}

	public int getTotalSelectedWorkers() {
		int total = 0;
		for (int i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].isSelected() && checkboxes[i].isEnabled()) {
				total++;
			}
		}
		return total;
	}

	public void setWorkersLeftZero(boolean zero) {
		for (int i = 0; i < checkboxes.length; i++)
			if (!checkboxes[i].isSelected())
				checkboxes[i].setEnabled(!zero);
	}

	/**
	 * Updates the check boxes for the current player
	 */
	private void updateCityChecksSelected() {
		for (int i = 0; i < 18; i++) {
			checkboxes[i].setSelected(false);
		}
		for (int i = 3; i < 7; i++) {
			int population = gamestate.getPlayer(gamestate.getCurrentPlayer())
					.getCity(i).currentPopulation();

			for (int j = 0; j < population; j++) {
				switch (i) {
				case 3:
					checkboxes[j].setSelected(true);
					checkboxes[j].setEnabled(false);
					break;
				case 4:
					checkboxes[j + 3].setSelected(true);
					checkboxes[j].setEnabled(false);
					break;
				case 5:
					checkboxes[j + 7].setSelected(true);
					checkboxes[j].setEnabled(false);
					break;
				case 6:
					checkboxes[j + 12].setSelected(true);
					checkboxes[j].setEnabled(false);
					break;
				}
			}

		}
	}

	/** Call AFTER updateCityChecksSelected. */
	private void updateCityChecksEnabled() {
		for (int i = 0; i < 18; i++) {
			if (!checkboxes[i].isSelected())
				checkboxes[i].setEnabled(true);
			else
				checkboxes[i].setEnabled(false);
		}
	}

	public static void main(String[] args) {
		String[] names = { "Matt", "Candice" };
		Game g = new Game(names);

		JFrame frame = new JFrame();
		CitiesPanel cPanel = new CitiesPanel(g, new MainWindow());
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(cPanel, BorderLayout.CENTER);
		frame.add(outerPanel);
		JPanel buttonPanel = new JPanel();
		JButton buyButton = new JButton("Buy cities");
		buttonPanel.add(buyButton);
		buyButton.addActionListener(cPanel.new BuyListener());
		name = new JLabel(g.getPlayer(g.getCurrentPlayer()).getName()
				+ "'s turn");
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(name);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		cPanel.setUpPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public void setUpPanel() {
		this.setBounds(0, 0, 200, 100);
	}

	public class CheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			main.updateWorkersAvailable();
			// System.out.println("First City " + getSelectedWorkersForCity(3));
			// System.out.println("Second City " +
			// getSelectedWorkersForCity(4));
			// System.out.println("Third City " + getSelectedWorkersForCity(5));
			// System.out.println("Fourth City " + getSelectedWorkersForCity(6)
			// + "\n");
		}
	}

	public class BuyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buyWorkers();
			gamestate.nextTurn();
			name.setText(gamestate.getPlayer(gamestate.getCurrentPlayer())
					.getName() + "'s turn");

			updateCityChecksSelected();
			updateCityChecksEnabled();
		}
	}

	public void buyWorkers() {
		for (int i = 3; i < 7; i++) {
			gamestate.getPlayer(gamestate.getCurrentPlayer()).buyCityWorkers(
					getSelectedWorkersForCity(i), i);
		}
	}
	
	@Override
	public void doNewTurnThings() {
		updateCityChecksSelected();
		setChecksEnabled(false);
	}

	@Override
	public void turnPartIsThis(boolean thisTurnPart) {
		if (!thisTurnPart
				|| gamestate.getPlayer(gamestate.getCurrentPlayer())
						.getWorkersAvailable() == 0) {
			setChecksEnabled(false);

		} else {
			updateCityChecksEnabled();

		}
	}

	private void setChecksEnabled(boolean enabled) {
		for (int i = 0; i < checkboxes.length; i++)
			checkboxes[i].setEnabled(enabled);
	}
}
