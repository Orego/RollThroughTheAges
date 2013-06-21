package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.DevelopmentList;
import model.Game;

public class DevelopmentsPanel extends JPanel implements TurnObserver{

	/** */
	private static final long serialVersionUID = 1L;

	/** holds all of the developements' checkboxes */
	private JCheckBox[] developments;

	/** Holds the name of the player who is playing */
	private static JLabel name;
	
	private Game gamestate;
	
	/** Initializes all of the graphics */
	public DevelopmentsPanel(Game game) {
		gamestate = game;
		ImageIcon developmentIcon = new ImageIcon("Images/developmentsicon.jpg");

		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.white);

		// Get all of the checkboxes in an array
		developments = new JCheckBox[13];
		for (int i = 0; i < 13; i++) {
			developments[i] = new JCheckBox();
			developments[i].addItemListener(new CheckboxListener());
		}

		JLabel label;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		label = new JLabel(developmentIcon);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		add(label, c);

		label = new JLabel("Cost");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		add(label, c);

		label = new JLabel("Name");
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		add(label, c);

		label = new JLabel("Pts");
		c.fill = GridBagConstraints.NONE;
		c.gridx = 3;
		c.gridy = 1;
		add(label, c);

		label = new JLabel("Effect");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		add(label, c);

		for (int i = 0; i < 13; i++) {
			label = new JLabel("" + DevelopmentList.DEVELOPMENT_COSTS[i]);
			c.gridx = 0;
			c.gridy = i + 2;
			add(label, c);

			c.gridx = 1;
			c.gridy = i + 2;
			add(developments[i], c);

			label = new JLabel(DevelopmentList.DEVELOPMENT_NAMES[i]);
			c.gridx = 2;
			c.gridy = i + 2;
			add(label, c);

			label = new JLabel("" + DevelopmentList.DEVELOPMENT_POINTS[i]);
			c.fill = GridBagConstraints.NONE;
			c.gridx = 3;
			c.gridy = i + 2;
			add(label, c);

			label = new JLabel(DevelopmentList.DEVELOPMENT_EFFECTS[i]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 4;
			c.gridy = i + 2;
			add(label, c);
		}
		updateDevelopmentChecks();
	}
	
	protected void updateDevelopmentChecks(){
		for(int i = 0; i < developments.length; i++ ){
			DevelopmentList devs = gamestate.getPlayersDevelopmentList(gamestate.getCurrentPlayer());
			if (devs.isDevelopmentBought(i)){
				developments[i].setSelected(true);
				developments[i].setEnabled(false);
			}
			else{
				developments[i].setSelected(false);
				developments[i].setEnabled(true);
			}
			if (DevelopmentList.DEVELOPMENT_COSTS[i]>gamestate.getPlayer(gamestate.getCurrentPlayer()).getTurnMoney()){
				developments[i].setEnabled(false);
			}
		}
	}
	
	protected void setDevelopmentChecks(boolean enabled){
		for (int i=0; i<developments.length; i++)
			developments[i].setEnabled(enabled);
	}
	
	private class BuyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buyDevelopment();
			gamestate.nextTurn();
			name.setText(gamestate.getPlayer(gamestate.getCurrentPlayer()).getName() + "'s turn");
			updateDevelopmentChecks();
		}
		
	}
	
	public void buyDevelopment(){
		for (int i = 0; i < developments.length; i++){
			if (developments[i].isSelected() && developments[i].isEnabled()){
				gamestate.getPlayersDevelopmentList(gamestate.getCurrentPlayer()).buyDevelopment(i);
				gamestate.getPlayer(gamestate.getCurrentPlayer()).bought(DevelopmentList.DEVELOPMENT_COSTS[i]);
			}
		}
	}

	public static void main(String[] args) {
		String[] names = {"a","b","c","d"};
		Game game = new Game(names);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DevelopmentsPanel devpane = new DevelopmentsPanel(game);
		JPanel outerpane = new JPanel();
		outerpane.setLayout(new BorderLayout());
		outerpane.add(devpane);
		JPanel buttonPane = new JPanel();
		JButton buyBtn = new JButton("Buy");
		buyBtn.addActionListener(devpane.new BuyListener());
		name = new JLabel(game.getPlayer(game.getCurrentPlayer()).getName() + "'s turn");
		buttonPane.add(buyBtn);
		buttonPane.add(name);
		buttonPane.setBackground(Color.white);
		frame.add(outerpane, BorderLayout.CENTER);
		frame.add(buttonPane, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	public class CheckboxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED) {
				for (int i = 0; i < developments.length; i++) {
					if (e.getItemSelectable() != developments[i]
							&& developments[i].isSelected() && developments[i].isEnabled()) {
						developments[i].setSelected(false);
					}
				}
			}
		}

	}

	@Override
	public void doNewTurnThings() {
		updateDevelopmentChecks();
		setDevelopmentChecks(false);
	}

	@Override
	public void turnPartIsThis(boolean thisTurnPart) {
		if (thisTurnPart){
			updateDevelopmentChecks();
		}
		else
			setDevelopmentChecks(false);
	}
	
}
