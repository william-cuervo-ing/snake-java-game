package presentation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class JPanelEndGame extends JPanel{

	private Window window;
	private JButton jbtnMainWindow, jbtnExit;
	private JLabel jlblSscorePlayer1, jlblSscorePlayer2, jlblGameOver;
	public static final String MAINWINDOW = "MainWindow";
	private int gameMode;
	
	public JPanelEndGame(Window window, int gamemode) {
		this.gameMode = gamemode;
		this.window = window;
		this.setLayout(null);
		this.setBounds(0, 0, window.getWidth(), window.getHeight());
		this.setBackground(new Color(27,173,204));
		this.initialize();
	}
	
	public void initialize(){
		Font font = new Font("Helvetica", Font.BOLD, 20);
		Font fontGameOver = new Font("Helvetica", Font.BOLD, 56);
		Border border = BorderFactory.createLineBorder(Color.white, 1);

		jbtnMainWindow = new JButton("Main Menu");
		jbtnMainWindow.setBounds(100, 280, 240, 40);
		jbtnMainWindow.setBackground(new Color(47,101,117));
		jbtnMainWindow.setFont(font);
		jbtnMainWindow.setForeground(Color.white);
		jbtnMainWindow.setBorder(border);
		jbtnMainWindow.setFocusPainted(false);
		jbtnMainWindow.setActionCommand(MAINWINDOW);
		this.add(jbtnMainWindow);
		
		jbtnExit = new JButton("Exit");
		jbtnExit.setBounds(100, 340, 240, 40);
		jbtnExit.setBackground(new Color(47,101,117));
		jbtnExit.setFont(font);
		jbtnExit.setForeground(Color.white);
		jbtnExit.setBorder(border);
		jbtnExit.setActionCommand(JPanelMainMenu.EXIT);
		jbtnExit.setFocusPainted(false);
		this.add(jbtnExit);
		
		jlblGameOver = new JLabel("¡GAME OVER!");
		jlblGameOver.setBounds(40, 70, 420, 80);
		jlblGameOver.setFont(fontGameOver);
		jlblGameOver.setForeground(Color.white);
		this.add(jlblGameOver);
	}
	public void modeOnePlayer(int scorePlayer1){
		Font fontScore = new Font("Helvetica", Font.BOLD, 34);
		
		jlblSscorePlayer1 = new JLabel("Score: " + scorePlayer1);
		jlblSscorePlayer1.setBounds(140, 160, 300, 40);
		jlblSscorePlayer1.setFont(fontScore);
		jlblSscorePlayer1.setForeground(new Color(205,233,241));
		this.add(jlblSscorePlayer1);
	}

	public void modeTwoPlayers(int scorePlayer1, int scorePlayer2){
		Font fontScore = new Font("Helvetica", Font.BOLD, 24);
		
		jlblSscorePlayer1 = new JLabel("Score Player 1: " + scorePlayer1);
		jlblSscorePlayer1.setBounds(110, 160, 300, 40);
		jlblSscorePlayer1.setFont(fontScore);
		jlblSscorePlayer1.setForeground(new Color(205,233,241));
		this.add(jlblSscorePlayer1);
		
		jlblSscorePlayer2 = new JLabel("Score Player 2: " + scorePlayer2);
		jlblSscorePlayer2.setBounds(110, 210, 300, 40);
		jlblSscorePlayer2.setFont(fontScore);
		jlblSscorePlayer2.setForeground(new Color(205,233,241));
		this.add(jlblSscorePlayer2);

	}

	public JButton getJbtnMainWindow() {
		return jbtnMainWindow;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}
	
	
	
}
