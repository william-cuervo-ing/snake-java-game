package presentation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class JPanelMainMenu extends JPanel{

	private Window window;
	private JLabel jlblCredits, jlblPause, jlblHelpPlayer1,jlblHelpPlayer2;
	private JTextArea jtxaHelpPlayer1,jtxaHelpPlayer2;
	private JButton jbtn1Player, jbtn2Players, jbtnExit;
	private Border border;
	public static final String ONE_PLAYER = "1Player";
	public static final String TWO_PLAYERS = "2Playeres";
	public static final String EXIT = "Exit";
	
	public JPanelMainMenu(Window window) {
		this.setLayout(null);
		this.setBounds(0, 00, 451, 491);
		this.setBackground(new Color(27,173,204));
		this.initialize();
	}
	
	public void initialize(){
		Font font = new Font("Helvetica", Font.BOLD, 20);
		border = BorderFactory.createLineBorder(Color.white, 1);
		
		jbtn1Player = new JButton("1 Player");
		jbtn1Player.setBounds(100, 150, 240, 40);
		jbtn1Player.setBackground(new Color(47,101,117));
		jbtn1Player.setFont(font);
		jbtn1Player.setForeground(Color.white);
		jbtn1Player.setBorder(border);
		jbtn1Player.setFocusPainted(false);
		jbtn1Player.setActionCommand(ONE_PLAYER);
		this.add(jbtn1Player);
		
		jbtn2Players = new JButton("2 Players");
		jbtn2Players.setBounds(100, 250, 240, 40);
		jbtn2Players.setFont(font);
		jbtn2Players.setBackground(new Color(47,101,117));
		jbtn2Players.setForeground(Color.white);
		jbtn2Players.setFocusPainted(false);
		jbtn2Players.setBorder(border);
		jbtn2Players.setActionCommand(TWO_PLAYERS);
		this.add(jbtn2Players);
		
		jbtnExit = new JButton("Exit");
		jbtnExit.setBounds(100, 350, 240, 40);
		jbtnExit.setFont(font);
		jbtnExit.setBackground(new Color(47,101,117));
		jbtnExit.setForeground(Color.white);
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(border);
		jbtnExit.setActionCommand(EXIT);
		this.add(jbtnExit);
		
		jlblHelpPlayer1 = new JLabel("Player 1: ");
		jlblHelpPlayer1.setBounds(20, 20, 100, 20);
		jlblHelpPlayer1.setForeground(Color.white);
		this.add(jlblHelpPlayer1);
		
		jtxaHelpPlayer1 = new JTextArea();
		String aux = "Move   up: up\n";
		aux += "Move Down:   down\n";
		aux += "Move Left:   left\n";
		aux += "Move Right:   right";
		jtxaHelpPlayer1.setText(aux);
		jtxaHelpPlayer1.setBackground(new Color(27,173,204,0));
		jtxaHelpPlayer1.setBounds(80, 20, 150, 150);
		jtxaHelpPlayer1.setEditable(false);
		jtxaHelpPlayer1.setForeground(Color.WHITE);
		this.add(jtxaHelpPlayer1);
		
		jlblHelpPlayer2 = new JLabel("Player 2: ");
		jlblHelpPlayer2.setBounds(240, 20, 100, 20);
		jlblHelpPlayer2.setForeground(Color.white);
		this.add(jlblHelpPlayer2);
		
		jlblPause = new JLabel("Pause:      Esc");
		jlblPause.setBounds(20, 100, 100, 20);
		jlblPause.setForeground(Color.white);
		this.add(jlblPause);

		
		jtxaHelpPlayer2 = new JTextArea();
		aux = "Move up:   w\n";
		aux += "Move Down:   a\n";
		aux += "Move Left:   s\n";
		aux += "Move Right:   d";
		jtxaHelpPlayer2.setText(aux);
		jtxaHelpPlayer1.setSelectionStart(0);
		jtxaHelpPlayer2.setBackground(new Color(27,173,204,0));
		jtxaHelpPlayer2.setBounds(300, 20, 150, 150);
		jtxaHelpPlayer2.setEditable(false);
		jtxaHelpPlayer2.setForeground(Color.WHITE);
		this.add(jtxaHelpPlayer2);
		
		
		jlblCredits = new JLabel("Developed por W.C");
		jlblCredits.setBounds(320, 470, 140, 20);
		jlblCredits.setForeground(Color.white);
		this.add(jlblCredits);
		
	}

	public JButton getJbtn1Player() {
		return jbtn1Player;
	}

	public JButton getJbtn2Players() {
		return jbtn2Players;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}
	
}
