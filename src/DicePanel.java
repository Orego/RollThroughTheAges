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

/** The panel that displays all the dice. */
public class DicePanel extends JPanel{
	
	/** The list of active dice. */
	private List<Die> dieList;
	
	/** The list of all graphic dice. */
	private List<GraphicDie> graphicDieList;
	
	/** Allows roll and reroll to share a rolltracker. */
	private RollTracker roller;
	
	/** Informs the user of rolls left. */
	private static JLabel rollCount;

	/**
	 * Sets up the dice display
	 * @param dieList The list of active dice to display.
	 * @throws TooManyDiceException No more than seven dice are allowed
	 */
	public DicePanel (List<Die> dieList) throws TooManyDiceException {
		if (dieList.size()>7) throw new TooManyDiceException();
		this.dieList = dieList;
		graphicDieList = new ArrayList<GraphicDie>();
		this.setLayout(new GridLayout(1,7));
		for (int i=0; i<7; i++){
			if (i<dieList.size())
				graphicDieList.add(new GraphicDie(dieList.get(i).getTopFace(),true));
			else
				graphicDieList.add(new GraphicDie((int)(Math.random()*6),false));
			this.add(graphicDieList.get(i));
		}
		setPreferredSize(new Dimension(86*7,83));
		roller = new RollTracker();
	}

	/** Updates the graphic dice. Should be called after dice are rolled. */
	public void updateGraphicDice() {
		for (int i=0; i<dieList.size(); i++){
			graphicDieList.get(i).changeFace(dieList.get(i).getTopFace());
		}
	}
	
	/**
	 * Get the indices of the selected dice.
	 * @return
	 */
	public List<Integer> getSelectedIndices() {
		ArrayList<Integer> ints = new ArrayList<Integer> ();
		for (int i=0; i<dieList.size(); i++){
			if (graphicDieList.get(i).dieIsSelected())
				ints.add(i);
		}
		return ints;
	}
	
	/** The exception that is thrown when too many active dice are passed at creation. */
	private class TooManyDiceException extends RuntimeException {}
	
	// shows how dice look
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ArrayList<Die> dice = new ArrayList<Die>();
		for (int i=0; i<3; i++)
			dice.add(new Die());
		DicePanel dicePanel = new DicePanel(dice);
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(dicePanel,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton roll = new JButton ("Fresh roll");
		roll.addActionListener(dicePanel.new RollListener());
		buttonPanel.add(roll);
		JButton reroll = new JButton ("Reroll selected");
		reroll.addActionListener(dicePanel.new RerollListener());
		rollCount = new JLabel("Rerolls left: "+2);
		buttonPanel.add(reroll);
		buttonPanel.add(rollCount);
		outerPanel.add(buttonPanel,BorderLayout.SOUTH);
		outerPanel.setPreferredSize(new Dimension(86*7,120));
		frame.add(outerPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//lets you roll the active dice from main method
	private class RollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			roller.rollDice(dieList);

			rollCount .setText("Rerolls left: "+roller.getRerollsLeft());
			updateGraphicDice();
			repaint();
		}
		
	}
	
	//lets you reroll the selected dice from main method
	private class RerollListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			roller.reroll(getSelectedIndices(), dieList);

			rollCount .setText("Rerolls left: "+roller.getRerollsLeft());
			updateGraphicDice();
			repaint();
		}
		
	}
}
