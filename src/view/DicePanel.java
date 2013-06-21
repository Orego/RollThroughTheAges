package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Die;
import model.Game;
import model.RollTracker;

/** The panel that displays all the dice. */
public class DicePanel extends JPanel implements TurnObserver{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	/** The list of all graphic dice. */
	private List<GraphicDie> graphicDieList;
	
	/** The current state of the game */
	private Game gamestate;
	
	/** Informs the user of rolls left. */
	private JLabel rollCount;
	
	private JButton reroll;

	/**
	 * Sets up the dice display
	 * @param dieList The list of active dice to display.
	 * @throws TooManyDiceException No more than seven dice are allowed
	 */
	public DicePanel (Game game) throws TooManyDiceException {
		gamestate = game;
		int currPlayer = gamestate.getCurrentPlayer();
		graphicDieList = new ArrayList<GraphicDie>();
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridLayout(1,7));
		for (int i=0; i<7; i++){
			if (i<gamestate.getPlayersDice(currPlayer).size())
				graphicDieList.add(new GraphicDie(gamestate.getPlayersDice(currPlayer).get(i).getTopFace(),true));
			else
				graphicDieList.add(new GraphicDie((int)(Math.random()*6),false));
			dicePanel.add(graphicDieList.get(i));
		}
		this.add(dicePanel, BorderLayout.CENTER);
		JPanel rerollPanel = new JPanel();
		reroll = new JButton("Reroll dice");
		reroll.addActionListener(new RerollListener());
		rollCount = new JLabel("Rerolls left: "+2);
		rerollPanel.add(rollCount);
		rerollPanel.add(reroll);
		this.add(rerollPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(86*7,83+50));

	}

	/** Updates the graphic dice. Should be called after dice are rolled. */
	public void updateGraphicDice() {
		int currPlayer = gamestate.getCurrentPlayer();
		List<Die> dice = gamestate.getPlayersDice(currPlayer);
		for (int i=0; i<dice.size(); i++){
			graphicDieList.get(i).changeFace(dice.get(i).getTopFace());
			graphicDieList.get(i).setEnabled(true);
		}
	}
	
	/**
	 * Get the indices of the selected dice.
	 * @return
	 */
	public List<Integer> getSelectedIndices() {
		ArrayList<Integer> ints = new ArrayList<Integer> ();
		for (int i=0; i<gamestate.getPlayersDice(gamestate.getCurrentPlayer()).size(); i++){
			if (graphicDieList.get(i).dieIsSelected())
				ints.add(i);
		}
		return ints;
	}
	
	/** The exception that is thrown when too many active dice are passed at creation. */
	private class TooManyDiceException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	
	// shows how dice look
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		String[] names = {"a","b","c","d"};
		DicePanel dicePanel = new DicePanel( new Game(names));
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(dicePanel,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton roll = new JButton ("Next player's turn");
		roll.addActionListener(dicePanel.new NextTurnListener());
		buttonPanel.add(roll);
		//JButton reroll = new JButton ("Reroll selected");
		//reroll.addActionListener(dicePanel.new RerollListener());
		//rollCount = new JLabel("Rerolls left: "+2);
		//buttonPanel.add(reroll);
		//buttonPanel.add(rollCount);
		outerPanel.add(buttonPanel,BorderLayout.SOUTH);
		outerPanel.setPreferredSize(new Dimension(86*7,120+50));
		frame.add(outerPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//lets you roll the active dice from main method
	private class NextTurnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gamestate.nextTurn();
			doNewTurnThings();
		}
		
	}
	
	//lets you reroll the selected dice from main method
	private class RerollListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gamestate.rerollPlayersDice(gamestate.getCurrentPlayer(), getSelectedIndices());
			rollCount .setText("Rerolls left: "+gamestate.getPlayersNumRerolls(gamestate.getCurrentPlayer()));
			if (gamestate.getPlayersNumRerolls(gamestate.getCurrentPlayer())==0){
				reroll.setEnabled(false);
			}
			updateGraphicDice();
			repaint();
		}
		
	}

	@Override
	public void doNewTurnThings() {
		gamestate.rollPlayersDice(gamestate.getCurrentPlayer());
		//rollCount .setText("Rerolls left: "+gamestate.getPlayersNumRerolls(gamestate.getCurrentPlayer()));
		updateGraphicDice();
		repaint();
		reroll.setEnabled(true);
		rollCount .setText("Rerolls left: "+gamestate.getPlayersNumRerolls(gamestate.getCurrentPlayer()));
	}

	@Override
	public void turnPartIsThis(boolean thisTurnPart) {
		reroll.setEnabled(thisTurnPart);
		if (!thisTurnPart){
			//update resources
			gamestate.doneRolling();
		}
	}
}
