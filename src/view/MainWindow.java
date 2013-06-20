package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private Game g;
	public MainWindow() {
		//create window
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//title picture
		this.add(new JLabel(new ImageIcon("Images/title.jpg")),BorderLayout.NORTH);

		//create game
		g = new Game(new String[] {"1","2","3","4"});
		
		//dice/resources/developments/turn panel
		JPanel center = new JPanel();
		this.add(center,BorderLayout.CENTER);
		center.setLayout(new BorderLayout());
		center.add(new DicePanel(g),BorderLayout.NORTH);
		center.add(new ResourcesPanel(),BorderLayout.WEST);
		center.add(new DevelopmentsPanel(g),BorderLayout.CENTER);
		center.add(new PartOfTurnPanel(g),BorderLayout.SOUTH);
		
		//cities/monuments panel
		JPanel right = new JPanel();
		this.add(right,BorderLayout.EAST);
		right.setBackground(Color.RED);
	}
	
	public static void main(String[] args){
		MainWindow window = new MainWindow();
		window.pack();
	}
}
