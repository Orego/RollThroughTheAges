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

public class MonumentPanel extends JPanel {

	/** The images associated with the different cities. */
	private static final ImageIcon[] MONUMENT_IMAGES = {
			new ImageIcon("Images/stepPyramid.jpg"),
			new ImageIcon("Images/stoneCircle.jpg"),
			new ImageIcon("Images/temple.jpg"),
			new ImageIcon("Images/obelisk.jpg"),
			new ImageIcon("Images/hanginggardens.jpg"),
			new ImageIcon("Images/wall.jpg"),
			new ImageIcon("Images/greatPyramid.jpg") };

	/** Holds information about the state of the game */
	private Game gamestate;

	/** Holds all of the checkboxes for the monuments */
	private JCheckBox[] checkboxes;

	private static JLabel name;

	public MonumentPanel(Game game) {
		gamestate = game;
		checkboxes = new JCheckBox[63];
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints c = new GridBagConstraints();
		// c.fill = GridBagConstraints.HORIZONTAL;

		JPanel[] panels = new JPanel[7];
		
		
		panels[0] = new JPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[0], c);

		panels[1] = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[1], c);

		panels[2] = new JPanel();
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[2], c);

		panels[3] = new JPanel();
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		this.add(panels[3], c);

		panels[4] = new JPanel();
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[4], c);

		panels[5] = new JPanel();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		this.add(panels[5], c);

		panels[6] = new JPanel();
		c.gridx = 5;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[6], c);

		for (int i = 0; i < 7; i++) {
			JLabel label = new JLabel(MONUMENT_IMAGES[i]);
			panels[i].setLayout(null);
			panels[i].add(label);
			label.setBounds(0, 0, MONUMENT_IMAGES[i].getIconWidth(),
					MONUMENT_IMAGES[i].getIconHeight());
			panels[i].setPreferredSize(new Dimension(MONUMENT_IMAGES[i]
					.getIconWidth(), MONUMENT_IMAGES[i].getIconHeight()));
			panels[i].repaint();
		}

		// checkboxes for the temple
		checkboxes[0] = new JCheckBox();
		panels[0].add(checkboxes[0], 0);
		checkboxes[0].setBounds(18, 10, 25, 25);
		panels[0].repaint();

		checkboxes[1] = new JCheckBox();
		panels[0].add(checkboxes[1], 0);
		checkboxes[1].setBounds(7, 26, 25, 25);
		panels[0].repaint();

		checkboxes[2] = new JCheckBox();
		panels[0].add(checkboxes[2], 0);
		checkboxes[2].setBounds(26, 26, 25, 25);
		panels[0].repaint();

		// checkboxes for stone circle
		checkboxes[3] = new JCheckBox();
		panels[1].add(checkboxes[3], 0);
		checkboxes[3].setBounds(9, 15, 25, 25);
		panels[1].repaint();

		checkboxes[4] = new JCheckBox();
		panels[1].add(checkboxes[4], 0);
		checkboxes[4].setBounds(25, 1, 25, 25);
		panels[1].repaint();

		checkboxes[5] = new JCheckBox();
		panels[1].add(checkboxes[5], 0);
		checkboxes[5].setBounds(37, 15, 25, 25);
		panels[1].repaint();

		checkboxes[6] = new JCheckBox();
		panels[1].add(checkboxes[6], 0);
		checkboxes[6].setBounds(52, 1, 25, 25);
		panels[1].repaint();

		checkboxes[7] = new JCheckBox();
		panels[1].add(checkboxes[7], 0);
		checkboxes[7].setBounds(65, 15, 25, 25);
		panels[1].repaint();

		// Checkboxes for temple
		checkboxes[8] = new JCheckBox();
		panels[2].add(checkboxes[8], 0);
		checkboxes[8].setBounds(28, 5, 25, 25);
		panels[2].repaint();

		checkboxes[9] = new JCheckBox();
		panels[2].add(checkboxes[9], 0);
		checkboxes[9].setBounds(9, 20, 25, 25);
		panels[2].repaint();

		checkboxes[10] = new JCheckBox();
		panels[2].add(checkboxes[10], 0);
		checkboxes[10].setBounds(28, 20, 25, 25);
		panels[2].repaint();

		checkboxes[11] = new JCheckBox();
		panels[2].add(checkboxes[11], 0);
		checkboxes[11].setBounds(47, 20, 25, 25);
		panels[2].repaint();

		checkboxes[12] = new JCheckBox();
		panels[2].add(checkboxes[12], 0);
		checkboxes[12].setBounds(9, 35, 25, 25);
		panels[2].repaint();

		checkboxes[13] = new JCheckBox();
		panels[2].add(checkboxes[13], 0);
		checkboxes[13].setBounds(28, 35, 25, 25);
		panels[2].repaint();

		checkboxes[14] = new JCheckBox();
		panels[2].add(checkboxes[14], 0);
		checkboxes[14].setBounds(47, 35, 25, 25);
		panels[2].repaint();

		// Checkboxes for obelisk
		checkboxes[15] = new JCheckBox();
		panels[3].add(checkboxes[15], 0);
		checkboxes[15].setBounds(0, 1, 25, 25);
		panels[3].repaint();

		checkboxes[16] = new JCheckBox();
		panels[3].add(checkboxes[16], 0);
		checkboxes[16].setBounds(0, 18, 25, 25);
		panels[3].repaint();

		checkboxes[17] = new JCheckBox();
		panels[3].add(checkboxes[17], 0);
		checkboxes[17].setBounds(0, 35, 25, 25);
		panels[3].repaint();

		checkboxes[18] = new JCheckBox();
		panels[3].add(checkboxes[18], 0);
		checkboxes[18].setBounds(0, 52, 25, 25);
		panels[3].repaint();

		checkboxes[19] = new JCheckBox();
		panels[3].add(checkboxes[19], 0);
		checkboxes[19].setBounds(0, 69, 25, 25);
		panels[3].repaint();

		checkboxes[20] = new JCheckBox();
		panels[3].add(checkboxes[20], 0);
		checkboxes[20].setBounds(0, 86, 25, 25);
		panels[3].repaint();

		checkboxes[21] = new JCheckBox();
		panels[3].add(checkboxes[21], 0);
		checkboxes[21].setBounds(0, 103, 25, 25);
		panels[3].repaint();

		checkboxes[22] = new JCheckBox();
		panels[3].add(checkboxes[22], 0);
		checkboxes[22].setBounds(0, 120, 25, 25);
		panels[3].repaint();

		checkboxes[23] = new JCheckBox();
		panels[3].add(checkboxes[23], 0);
		checkboxes[23].setBounds(0, 136, 25, 25);
		panels[3].repaint();

		// checkboxes for hanging gardens
		checkboxes[24] = new JCheckBox();
		panels[4].add(checkboxes[24], 0);
		checkboxes[24].setBounds(28, 7, 25, 25);
		panels[4].repaint();

		checkboxes[25] = new JCheckBox();
		panels[4].add(checkboxes[25], 0);
		checkboxes[25].setBounds(47, 7, 25, 25);
		panels[4].repaint();

		checkboxes[26] = new JCheckBox();
		panels[4].add(checkboxes[26], 0);
		checkboxes[26].setBounds(10, 24, 25, 25);
		panels[4].repaint();

		checkboxes[27] = new JCheckBox();
		panels[4].add(checkboxes[27], 0);
		checkboxes[27].setBounds(28, 24, 25, 25);
		panels[4].repaint();

		checkboxes[28] = new JCheckBox();
		panels[4].add(checkboxes[28], 0);
		checkboxes[28].setBounds(47, 24, 25, 25);
		panels[4].repaint();

		checkboxes[29] = new JCheckBox();
		panels[4].add(checkboxes[29], 0);
		checkboxes[29].setBounds(65, 24, 25, 25);
		panels[4].repaint();

		checkboxes[30] = new JCheckBox();
		panels[4].add(checkboxes[30], 0);
		checkboxes[30].setBounds(2, 40, 25, 25);
		panels[4].repaint();

		checkboxes[31] = new JCheckBox();
		panels[4].add(checkboxes[31], 0);
		checkboxes[31].setBounds(20, 40, 25, 25);
		panels[4].repaint();

		checkboxes[32] = new JCheckBox();
		panels[4].add(checkboxes[32], 0);
		checkboxes[32].setBounds(38, 40, 25, 25);
		panels[4].repaint();

		checkboxes[33] = new JCheckBox();
		panels[4].add(checkboxes[33], 0);
		checkboxes[33].setBounds(56, 40, 25, 25);
		panels[4].repaint();

		checkboxes[34] = new JCheckBox();
		panels[4].add(checkboxes[34], 0);
		checkboxes[34].setBounds(74, 40, 25, 25);
		panels[4].repaint();

		// checkboxes for wall
		checkboxes[35] = new JCheckBox();
		panels[5].add(checkboxes[35], 0);
		checkboxes[35].setBounds(2, 15, 25, 25);
		panels[5].repaint();

		checkboxes[36] = new JCheckBox();
		panels[5].add(checkboxes[36], 0);
		checkboxes[36].setBounds(22, 15, 25, 25);
		panels[5].repaint();

		checkboxes[37] = new JCheckBox();
		panels[5].add(checkboxes[37], 0);
		checkboxes[37].setBounds(42, 15, 25, 25);
		panels[5].repaint();

		checkboxes[38] = new JCheckBox();
		panels[5].add(checkboxes[38], 0);
		checkboxes[38].setBounds(62, 15, 25, 25);
		panels[5].repaint();

		checkboxes[39] = new JCheckBox();
		panels[5].add(checkboxes[39], 0);
		checkboxes[39].setBounds(82, 15, 25, 25);
		panels[5].repaint();

		checkboxes[40] = new JCheckBox();
		panels[5].add(checkboxes[40], 0);
		checkboxes[40].setBounds(102, 15, 25, 25);
		panels[5].repaint();

		checkboxes[41] = new JCheckBox();
		panels[5].add(checkboxes[41], 0);
		checkboxes[41].setBounds(122, 15, 25, 25);
		panels[5].repaint();

		checkboxes[42] = new JCheckBox();
		panels[5].add(checkboxes[42], 0);
		checkboxes[42].setBounds(142, 15, 25, 25);
		panels[5].repaint();

		checkboxes[43] = new JCheckBox();
		panels[5].add(checkboxes[43], 0);
		checkboxes[43].setBounds(162, 15, 25, 25);
		panels[5].repaint();

		checkboxes[44] = new JCheckBox();
		panels[5].add(checkboxes[44], 0);
		checkboxes[44].setBounds(182, 15, 25, 25);
		panels[5].repaint();

		checkboxes[45] = new JCheckBox();
		panels[5].add(checkboxes[45], 0);
		checkboxes[45].setBounds(202, 15, 25, 25);
		panels[5].repaint();

		checkboxes[46] = new JCheckBox();
		panels[5].add(checkboxes[46], 0);
		checkboxes[46].setBounds(222, 15, 25, 25);
		panels[5].repaint();

		checkboxes[47] = new JCheckBox();
		panels[5].add(checkboxes[47], 0);
		checkboxes[47].setBounds(242, 15, 25, 25);
		panels[5].repaint();

		// checkboxes for the great pyramid....finally
		checkboxes[48] = new JCheckBox();
		panels[6].add(checkboxes[48], 0);
		checkboxes[48].setBounds(42, 12, 25, 25);
		panels[6].repaint();

		checkboxes[49] = new JCheckBox();
		panels[6].add(checkboxes[49], 0);
		checkboxes[49].setBounds(34, 29, 25, 25);
		panels[6].repaint();

		checkboxes[50] = new JCheckBox();
		panels[6].add(checkboxes[50], 0);
		checkboxes[50].setBounds(50, 29, 25, 25);
		panels[6].repaint();

		checkboxes[51] = new JCheckBox();
		panels[6].add(checkboxes[51], 0);
		checkboxes[51].setBounds(26, 46, 25, 25);
		panels[6].repaint();

		checkboxes[52] = new JCheckBox();
		panels[6].add(checkboxes[52], 0);
		checkboxes[52].setBounds(42, 46, 25, 25);
		panels[6].repaint();

		checkboxes[53] = new JCheckBox();
		panels[6].add(checkboxes[53], 0);
		checkboxes[53].setBounds(58, 46, 25, 25);
		panels[6].repaint();

		checkboxes[54] = new JCheckBox();
		panels[6].add(checkboxes[54], 0);
		checkboxes[54].setBounds(18, 63, 25, 25);
		panels[6].repaint();

		checkboxes[55] = new JCheckBox();
		panels[6].add(checkboxes[55], 0);
		checkboxes[55].setBounds(34, 63, 25, 25);
		panels[6].repaint();

		checkboxes[56] = new JCheckBox();
		panels[6].add(checkboxes[56], 0);
		checkboxes[56].setBounds(50, 63, 25, 25);
		panels[6].repaint();

		checkboxes[57] = new JCheckBox();
		panels[6].add(checkboxes[57], 0);
		checkboxes[57].setBounds(66, 63, 25, 25);
		panels[6].repaint();

		checkboxes[58] = new JCheckBox();
		panels[6].add(checkboxes[58], 0);
		checkboxes[58].setBounds(10, 80, 25, 25);
		panels[6].repaint();

		checkboxes[59] = new JCheckBox();
		panels[6].add(checkboxes[59], 0);
		checkboxes[59].setBounds(26, 80, 25, 25);
		panels[6].repaint();

		checkboxes[60] = new JCheckBox();
		panels[6].add(checkboxes[60], 0);
		checkboxes[60].setBounds(42, 80, 25, 25);
		panels[6].repaint();

		checkboxes[61] = new JCheckBox();
		panels[6].add(checkboxes[61], 0);
		checkboxes[61].setBounds(58, 80, 25, 25);
		panels[6].repaint();

		checkboxes[62] = new JCheckBox();
		panels[6].add(checkboxes[62], 0);
		checkboxes[62].setBounds(74, 80, 25, 25);
		panels[6].repaint();
		
		updateCheckboxes();
	}

	public int getSelectedWorkersForMonument(int monument) {
		int sum = 0;
		switch (monument) {
		case 0:
			for (int i = 0; i <= 2; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 1:
			for (int i = 3; i <= 7; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 2:
			for (int i = 8; i <= 14; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 3:
			for (int i = 15; i <= 23; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 4:
			for (int i = 24; i <= 34; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 5:
			for (int i = 35; i <= 47; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		case 6:
			for (int i = 48; i <= 62; i++) {
				if (checkboxes[i].isSelected() && checkboxes[i].isEnabled())
					sum++;
			}
			break;
		}

		return sum;
	}

	public void updateCheckboxes() {
		for (int i = 0; i < 63; i++) {
			checkboxes[i].setSelected(false);
			checkboxes[i].setEnabled(true);
		}
		int numPlayers = gamestate.getNumPlayers();
		switch (numPlayers){
		case 1:
			for (int i = 8; i <= 14; i++){
				checkboxes[i].setEnabled(false);
			}
			for (int i = 24; i <= 34; i++){
				checkboxes[i].setEnabled(false);
			}
			for (int i = 48; i <= 62; i++){
				checkboxes[i].setEnabled(false);
			}
			break;
		case 2:
			for (int i = 8; i <= 14; i++){
				checkboxes[i].setEnabled(false);
			}
			for (int i = 48; i <= 62; i++){
				checkboxes[i].setEnabled(false);
			}
			break;
		case 3:
			for (int i = 24; i <= 34; i++){
				checkboxes[i].setEnabled(false);
			}
			break;
		}
		for (int i = 0; i < gamestate.getNumPlayers() + 3; i++) {
			int index = i;
			if (gamestate.getNumPlayers() == 1){
				switch (i){
				case 0:
					index = 0;
					break;
				case 1:
					index = 1;
					break;
				case 2:
					index = 3;
					break;
				case 3:
					index = 5;
					break;						
				}
			}
			else if (gamestate.getNumPlayers() == 2){
				switch (i){
				case 0:
					index = 0;
					break;
				case 1:
					index = 1;
					break;
				case 2:
					index = 3;
					break;
				case 3:
					index = 4;
					break;
				case 4:
					index = 5;
					break;
				}
			}
			else if (gamestate.getNumPlayers() == 3){
				switch (i){
				case 0:
					index = 0;
					break;
				case 1:
					index = 1;
					break;
				case 2:
					index = 2;
					break;
				case 3:
					index = 3;
					break;
				case 4:
					index = 5;
					break;
				case 5:
					index = 6;
					break;
				}
			}
			
			
			int population = gamestate.getPlayer(gamestate.getCurrentPlayer())
					.getMonument(i).currentPopulation();
			for (int p = 0; p < population; p++) {
				switch (index) {
				case 0:
					checkboxes[p].setSelected(true);
					checkboxes[p].setEnabled(false);
					break;
				case 1:
					checkboxes[p+3].setSelected(true);
					checkboxes[p+3].setEnabled(false);
					break;
				case 2:
					checkboxes[p+8].setSelected(true);
					checkboxes[p+8].setEnabled(false);
					break;
				case 3:
					checkboxes[p+15].setSelected(true);
					checkboxes[p+15].setEnabled(false);
					break;
				case 4:
					checkboxes[p+24].setSelected(true);
					checkboxes[p+24].setEnabled(false);
					break;
				case 5:
					checkboxes[p+35].setSelected(true);
					checkboxes[p+35].setEnabled(false);
					break;
				case 6:
					checkboxes[p+48].setSelected(true);
					checkboxes[p+48].setEnabled(false);
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		String[] names = { "Matt", "Candice", "Alex", "Lauren"};
		Game game = new Game(names);
		MonumentPanel mpanel = new MonumentPanel(game);
		JPanel outerpanel = new JPanel();
		outerpanel.setLayout(new BorderLayout());
		outerpanel.add(mpanel, BorderLayout.CENTER);
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.white);
		outerpanel.add(buttonpanel, BorderLayout.SOUTH);
		JButton buybutton = new JButton("Buy Monuments");
		buybutton.addActionListener(mpanel.new BuyListener());
		buttonpanel.add(buybutton);
		name = new JLabel(game.getPlayer(game.getCurrentPlayer()).getName()
				+ "'s turn");
		buttonpanel.add(name);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(outerpanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public class BuyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (gamestate.getNumPlayers()){
			case 1:
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(0), 0);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(1), 1);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(3), 2);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(5), 3);
				break;
			case 2:
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(0), 0);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(1), 1);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(3), 2);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(4), 3);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(5), 4);
				break;
			case 3:
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(0), 0);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(1), 1);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(2), 2);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(3), 3);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(5), 4);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(6), 5);
				break;
			default:
				for(int i=0; i<7; i++){
					gamestate.getPlayer(gamestate.getCurrentPlayer()).buyMonumentWorkers(getSelectedWorkersForMonument(i), i);
				}
			}
			
			gamestate.nextTurn();
			name.setText(gamestate.getPlayer(gamestate.getCurrentPlayer()).getName() +"'s turn");
			updateCheckboxes();	
		}	
	}
}
