import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** The panel that displays all the dice. */
public class DicePanel extends JPanel{
	
	/** The list of active dice. */
	private List<Die> dieList;
	
	/** The list of all graphic dice. */
	private List<GraphicDie> graphicDieList;

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
	}

	/** Updates the graphic dice. Should be called after dice are rolled. */
	public void updateGraphicDice() {
		for (int i=0; i<dieList.size(); i++){
			graphicDieList.get(i).changeFace(dieList.get(i).getTopFace());
		}
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
		JButton roll = new JButton ("Roll");
		roll.addActionListener(dicePanel.new RollListener());
		outerPanel.add(roll,BorderLayout.SOUTH);
		outerPanel.setPreferredSize(new Dimension(86*7,120));
		frame.add(outerPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//lets you roll the active dice from main method
	private class RollListener implements ActionListener {
		private RollTracker rolly = new RollTracker();

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rolly.rollDice(dieList);
			updateGraphicDice();
		}
		
	}
}
