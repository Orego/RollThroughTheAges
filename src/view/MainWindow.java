package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	/** Holds the state of the game */
	private Game g;

	private DicePanel dice;

	private CitiesPanel cities;

	private DevelopmentsPanel developments;

	private ResourcesPanel resources;

	private JButton continueBtn;

	private List<TurnObserver> turnObservers;
	
	private JLabel workersLeft, totalScore;
	

	private MonumentPanel monuments;


	public MainWindow() {
		// create window
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setBackground(Color.WHITE);

		// title picture/total score
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel(new ImageIcon("Images/title.jpg")));
		totalScore = new JLabel("Player score: 0");
		titlePanel.add(totalScore);
		this.add(titlePanel,BorderLayout.NORTH);

		// create game
		g = new Game(new String[] { "1", "2"});//, "3", "4" });
		

		turnObservers = new ArrayList<TurnObserver>();

		// dice/resources/developments/turn panel
		JPanel center = new JPanel();
		this.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout());
		dice = new DicePanel(g);
		turnObservers.add(dice);
		center.add(dice, BorderLayout.NORTH);
		resources = new ResourcesPanel(g, this);
		turnObservers.add(resources);
		center.add(resources, BorderLayout.WEST);
		developments = new DevelopmentsPanel(g);
		turnObservers.add(developments);
		center.add(developments, BorderLayout.CENTER);
		continueBtn = new JButton();
		continueBtn.addActionListener(new ContinueListener());
		center.add(continueBtn, BorderLayout.SOUTH);

		// cities/monuments panel
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		cities = new CitiesPanel(g,this);
		right.add(cities, BorderLayout.NORTH);
		workersLeft = new JLabel("Workers left: 0",JLabel.CENTER);
		right.setBorder(BorderFactory.createEmptyBorder(0,12,12,12));//top, left, bottom, right);)
		right.add(workersLeft,BorderLayout.CENTER);
		monuments = new MonumentPanel(g, this);
		right.add(monuments,BorderLayout.SOUTH);
		turnObservers.add(monuments);
		turnObservers.add(cities);
		this.add(right, BorderLayout.EAST);

		// set title and button text
		updatePanel();
	}

	public void updatePanel() {
		continueBtn.setText(Game.TURN_END_TEXT[g.getCurrentTurnPart()]);

		int turnPartEnding = g.getCurrentTurnPart() - 1;

		if (turnPartEnding == -1) {
			
			List<Integer> ints = resources.getSelectedResources();
			int oldPlayer = g.getCurrentPlayer() -1;
			if (oldPlayer == -1) oldPlayer = g.getNumPlayers() - 1;
			System.out.println("player "+ oldPlayer);
			for (int i = 0; i < ints.size(); i++){
				g.getPlayer(oldPlayer).removeResource(ints.get(i));
			}
			
			doNewTurnThings();
			dice.turnPartIsThis(true);
		} else if (turnPartEnding == Game.ROLL_DICE) {
			dice.turnPartIsThis(false);
			// dice recorded, so update resources
			updateResources();
			workersLeft.setText("Workers left: "+g.getPlayer(g.getCurrentPlayer()).getWorkersAvailable());
		} else if (turnPartEnding == Game.FEED_DISASTERS) {
			// dice recorded, so update resources
			g.feedCitiesProcessDisasters();
			cities.turnPartIsThis(true);
			monuments.turnPartIsThis(true);
			updateResources();
			updateTotalScore();
		} else if (turnPartEnding == Game.DEVELOPMENT) {
			List<Integer> ints = resources.getSelectedResources();
			developments.buyDevelopment(ints);
			developments.turnPartIsThis(false);
			updateTotalScore();
			updateResources();
			resources.updateTurnMoney();
		} else if (turnPartEnding == Game.BUILD) {
			cities.buyWorkers();
			cities.turnPartIsThis(false);
			developments.turnPartIsThis(true);
			monuments.buyWorkers();
			monuments.turnPartIsThis(false);
			workersLeft.setText("Workers left: 0");
			updateTotalScore();
		} else if (turnPartEnding == Game.DISCARD){
			
		}else
			updateResources();
	}
	
	private void updateTotalScore(){
		totalScore.setText("Player score: "+g.getPlayer(g.getCurrentPlayer()).getTotalScore());
	}

	private void updateResources() {
		resources.updateResources();
	}
	
	public void updateDevelopments(){
		if (g.getCurrentTurnPart() == Game.DEVELOPMENT){
			developments.setDevelopmentChecks(true);
			developments.updateDevelopmentChecks();
		}
	}

	private void doNewTurnThings() {
		this.setTitle("Roll Through the Ages - Player: "
				+ g.getPlayer(g.getCurrentPlayer()).getName());
		totalScore.setText("Player score: "+g.getPlayer(g.getCurrentPlayer()).getTotalScore());
		for (TurnObserver to : turnObservers) {
			to.doNewTurnThings();
		}
		resources.updateSelected();
	}

	public class ContinueListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!g.nextTurnPart()){
				//end of game
				JOptionPane.showMessageDialog(null,"The game has ended.  The winner is player: "+g.getWinner());
				System.exit(0);
			}
			updatePanel();
		}
	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.pack();
	}

	public void updateWorkersAvailable() {
		int workersAvailable = g.getPlayer(g.getCurrentPlayer())
				.getWorkersAvailable()-cities.getTotalSelectedWorkers()-monuments.getTotalSelectedWorkers();
		
		workersLeft.setText("Workers left: "
				+ workersAvailable );
		
		cities.setWorkersLeftZero(workersAvailable == 0);
		monuments.setWorkersLeftZero(workersAvailable==0);
	}
	
	
}
