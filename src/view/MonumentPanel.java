package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
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
			new ImageIcon("Images/greatPyramid.jpg") 
	};
	
	/** Holds information about the state of the game */
	private Game gamestate;
	
	public MonumentPanel(Game game){
		gamestate = game;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints c = new GridBagConstraints();
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel[] panels = new JPanel[7];
		panels[0] = new JPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[0],c);
		
		panels[1] = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[1],c);
		
		panels[2] = new JPanel();
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[2],c);
		
		panels[3] = new JPanel();
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		this.add(panels[3],c);
		
		panels[4] = new JPanel();
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[4],c);
		
		panels[5] = new JPanel();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		this.add(panels[5],c);
		
		panels[6] = new JPanel();
		c.gridx = 5;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(panels[6],c);
		
		for (int i = 0; i<7; i++){
			JLabel label = new JLabel(MONUMENT_IMAGES[i]);
			panels[i].setLayout(null);
			panels[i].add(label);
			label.setBounds(0, 0, MONUMENT_IMAGES[i].getIconWidth(), MONUMENT_IMAGES[i].getIconHeight());
			panels[i].setPreferredSize(new Dimension(MONUMENT_IMAGES[i].getIconWidth(), MONUMENT_IMAGES[i].getIconHeight()));
			panels[i].repaint();
		}
		
	}
	
	public static void main(String[] args){
		String[] names = {"Matt","Candice"};
		Game game = new Game(names);
		MonumentPanel mpanel = new MonumentPanel(game);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mpanel);
		frame.pack();
		frame.setVisible(true);
	}
}
