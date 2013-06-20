package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class PartOfTurnPanel extends JPanel {

	/** */
	private static final long serialVersionUID = 1L;
	
	/** Holds the state of the game */
	private Game gamestate;
	
	/** Holds the player who's turn it is */
	private JLabel name;
	
	/** Holds the part of turn that the player is on */
	private JLabel part;
	
	private JButton continueBtn;
	
	public PartOfTurnPanel(Game game){
		gamestate = game;
		
	}
	
	private void setUpTurnPanel(){
		JPanel southPanel = new JPanel();
		JPanel innerpanel = new JPanel();
		innerpanel.setLayout(new GridLayout(2,2));
		
		JLabel label = new JLabel("Player's turn:");
		innerpanel.add(label);
		
		name = new JLabel(gamestate.getPlayer(gamestate.getCurrentPlayer()).getName());
		innerpanel.add(name);
		
		label = new JLabel("Turn Part:");
		innerpanel.add(label);
		
		part = new JLabel(Game.TURN_PARTS[gamestate.getCurrentTurnPart()]);
		innerpanel.add(part);
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(innerpanel, BorderLayout.CENTER);
		continueBtn = new JButton("Continue");
		continueBtn.addActionListener(new ContinueListener());
		southPanel.add(continueBtn, BorderLayout.SOUTH);
	}
	
	public void updatePanel(){
		name.setText(gamestate.getPlayer(gamestate.getCurrentPlayer()).getName());
		part.setText(Game.TURN_PARTS[gamestate.getCurrentTurnPart()]);
		continueBtn.setText(Game.TURN_PARTS[gamestate.getCurrentTurnPart()]);
	}
	
	public static void main(String[] args){
		String[] names = {"Him", "Her","You","Them"};
		Game game = new Game(names);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PartOfTurnPanel panel = new PartOfTurnPanel(game);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public class ContinueListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gamestate.nextTurnPart();
			updatePanel();
		}
		
	}

}
