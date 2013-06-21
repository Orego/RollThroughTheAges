package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		g = new Game(new String[] { "1", "2", "3", "4" });
		
		//TODO: take this out later
//		for (int i=0; i<g.getNumPlayers(); i++){
//			for (int j=3; j<7; j++){
//				g.getPlayer(i).buyCityWorkers(15, j);
//			}
//		}

		turnObservers = new ArrayList<TurnObserver>();

		// dice/resources/developments/turn panel
		JPanel center = new JPanel();
		this.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout());
		dice = new DicePanel(g);
		turnObservers.add(dice);
		center.add(dice, BorderLayout.NORTH);
		resources = new ResourcesPanel(g);
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
		workersLeft = new JLabel("Workers left: 0");
		right.add(workersLeft,BorderLayout.CENTER);
		
		turnObservers.add(cities);
		this.add(right, BorderLayout.EAST);

		// set title and button text
		updatePanel();
	}

	public void updatePanel() {
		continueBtn.setText(Game.TURN_END_TEXT[g.getCurrentTurnPart()]);

		int turnPartEnding = g.getCurrentTurnPart() - 1;

		// TODO: put discarding goods in
		if (turnPartEnding == -1) {
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
			updateResources();
			updateTotalScore();
		} else if (turnPartEnding == Game.DEVELOPMENT) {
			developments.buyDevelopment();
			developments.turnPartIsThis(false);
			updateTotalScore();
		} else if (turnPartEnding == Game.BUILD) {
			cities.buyWorkers();
			cities.turnPartIsThis(false);
			developments.turnPartIsThis(true);
			// TODO: monuments.buyWorkers();
			workersLeft.setText("Workers left: 0");
			updateTotalScore();
		} else
			updateResources();
	}
	
	private void updateTotalScore(){
		totalScore.setText("Player score: "+g.getPlayer(g.getCurrentPlayer()).getTotalScore());
	}

	private void updateResources() {
		resources.updateResources();
	}

	private void doNewTurnThings() {
		this.setTitle("Roll Through the Ages - Player: "
				+ g.getPlayer(g.getCurrentPlayer()).getName());
		totalScore.setText("Player score: "+g.getPlayer(g.getCurrentPlayer()).getTotalScore());
		for (TurnObserver to : turnObservers) {
			to.doNewTurnThings();
		}
	}

	public class ContinueListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			g.nextTurnPart();
			updatePanel();
		}
	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.pack();
	}

	public void updateWorkersAvailable() {
		//TODO: subtract monument workers also
		int workersAvailable = g.getPlayer(g.getCurrentPlayer())
				.getWorkersAvailable()-cities.getTotalSelectedWorkers();
		
		workersLeft.setText("Workers left: "
				+ workersAvailable );
		
		cities.setWorkersLeftZero(workersAvailable == 0);
		//TODO: monuments.setWorkersLeftZero(workersAvailable==0);
	}
}
