package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Game;
import model.PlayerResources;
/** This class displays resources graphically. */
public class ResourcesPanel extends JPanel implements TurnObserver {

	/** The images associated with the different resources. */
	private static final ImageIcon[] RESOURCE_IMAGES = {
			new ImageIcon("Images/wood.jpg"),
			new ImageIcon("Images/stone.jpg"),
			new ImageIcon("Images/pottery.jpg"),
			new ImageIcon("Images/cloth.jpg"),
			new ImageIcon("Images/spearhead.jpg"),
			new ImageIcon("Images/food2.jpg") };
	
	/** The images associated with the different selected resources. */
	private static final ImageIcon[] SELECTED_RESOURCE_IMAGES = {
			new ImageIcon("Images/woodselected.jpg"),
			new ImageIcon("Images/stoneselected.jpg"),
			new ImageIcon("Images/potteryselected.jpg"),
			new ImageIcon("Images/clothselected.jpg"),
			new ImageIcon("Images/spearheadselected.jpg"),
			new ImageIcon("Images/food2selected.jpg") };

	/** the game */
	private Game game;
	
	/** These arrays display the amount and worth of goods in each category. */
	private JLabel[] amountLabel, worthLabel;

	/** The total amount of goods. */
	private JLabel totalAmount;
	
	/** The worth of total goods. */
	private JLabel totalWorth;
	
	/** The total amount of money for a turn */
	private JLabel turnMoney;
	
	/** The buttons for resources */
	private JButton resourceBtns[];
	
	private MainWindow main;
	
	/** The selected border. */
	private static final Border selectedBorder = BorderFactory
			.createCompoundBorder(
					BorderFactory.createLineBorder(Color.BLACK, 1),
					BorderFactory.createLineBorder(Color.RED, 2));

	public ResourcesPanel(Game game, MainWindow main) {
		this.game = game;
		this.main = main;
		amountLabel = new JLabel[6];
		worthLabel = new JLabel[6];
		layoutGUI();
	}

	/** Lays out the components of the panel. */
	private void layoutGUI() {
		this.setLayout(new GridLayout(0, 3, 3, 3));// rows, columns
		setPreferredSize(new Dimension(60 * 3, 40 * 7));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));// top,left,bottom,right

		resourceBtns = new JButton[5];
		
		this.add(new JLabel("Goods:"));
		this.add(new JLabel("Amount:"));
		this.add(new JLabel("Worth:"));
		for (int i = 4; i >= 0; i--) {
			resourceBtns[i] = new JButton(RESOURCE_IMAGES[i]);
			resourceBtns[i].setFocusPainted(false);
			resourceBtns[i].setMargin(new Insets(0, 0, 0, 0));
			resourceBtns[i].setContentAreaFilled(false);
			resourceBtns[i].setBorderPainted(false);
			resourceBtns[i].setOpaque(false);
			resourceBtns[i].addActionListener(new ResourceListener());
			this.add(resourceBtns[i]);
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
		
		this.add(new JLabel("Money:"));
		turnMoney = new JLabel("0");
		this.add(turnMoney);
		this.add(new JLabel(""));
		
		this.add(new JLabel(RESOURCE_IMAGES[5]));
		amountLabel[PlayerResources.FOOD] = new JLabel("3");
		this.add(amountLabel[PlayerResources.FOOD]);
		worthLabel[PlayerResources.FOOD] = new JLabel();
		this.add(worthLabel[PlayerResources.FOOD]);
	}

	

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(60 * 3, 40 * 8));
		ResourcesPanel rp = new ResourcesPanel(new Game(new String[] {"1"}), new MainWindow());
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

	@Override
	public void doNewTurnThings() {
		updateResources();
		this.setEnabled(false);
	}
	
	public List<Integer> getSelectedResources(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++){
			if (resourceBtns[i].getIcon() == SELECTED_RESOURCE_IMAGES[i]){
				list.add(i);
			}
		}
		return list;
	}


	/** Updates the displayed amount and worth of resources. */
	public void updateResources() {
		//TODO: get granaries boolean from player
		boolean granaries = false;
		
		PlayerResources r = game.getPlayer(game.getCurrentPlayer()).getPlayerResources();
		int totalAmount = 0, totalWorth = 0;
		for (int i = 0; i < 6; i++) {
			if (i != PlayerResources.FOOD) {
				if (r.getWorth(i) > 0) resourceBtns[i].setEnabled(true);
				else resourceBtns[i].setEnabled(false);
				totalWorth += r.getWorth(i);
				totalAmount += r.getAmount(i);
			}
			amountLabel[i].setText("" + r.getAmount(i));
			if (i != PlayerResources.FOOD || granaries)
				worthLabel[i].setText("" + r.getWorth(i));
		}
		this.totalAmount.setText("" + totalAmount);
		this.totalWorth.setText("" + totalWorth);
		updateTurnMoney();
	}
	
	/** Updates the displayed amount and worth of resources. */
	public void updateResources(int[] amount, int[] worth, boolean granaries) {
		int totalAmount = 0, totalWorth = 0;
		for (int i = 0; i < 6; i++) {
			if (i != PlayerResources.FOOD) {
				if (worth[i] > 0) resourceBtns[i].setEnabled(true);
				else resourceBtns[i].setEnabled(false);
				totalWorth += worth[i];
				totalAmount += amount[i];
			}
			amountLabel[i].setText("" + amount[i]);
			if (i != PlayerResources.FOOD || granaries)
				worthLabel[i].setText("" + worth[i]);
		}
		this.totalAmount.setText("" + totalAmount);
		this.totalWorth.setText("" + totalWorth);
		updateTurnMoney();
	}

	@Override
	public void turnPartIsThis(boolean thisTurnPart) {
		this.setEnabled(thisTurnPart);
	}
	
	public void updateSelected(){
		for (int i = 0; i < 5; i++){
			resourceBtns[i].setIcon(RESOURCE_IMAGES[i]);
		}
	}
	
	public void updateTurnMoney(){
		turnMoney.setText(""+game.getPlayer(game.getCurrentPlayer()).getTurnMoney());
	}

	public class ResourceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			for (int i = 0; i < 5; i++){
				if (btn == resourceBtns[i]) {
					if (btn.getIcon() == RESOURCE_IMAGES[i]){
						btn.setIcon(SELECTED_RESOURCE_IMAGES[i]);
						game.getPlayer(game.getCurrentPlayer()).addResourceToTurnMoney(i);
					}
					else{
						btn.setIcon(RESOURCE_IMAGES[i]);
						game.getPlayer(game.getCurrentPlayer()).removeResourceToTurnMoney(i);
					}
					break;
				}
			}
			updateTurnMoney();
			main.updateDevelopments();
		}
		
	}
	
}
