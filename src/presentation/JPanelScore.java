package presentation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JPanelScore extends JPanel{

	private JLabel jLabelPlayer1,jLabelScorePlayer1,jLabelPlayer2,jLabelScorePlayer2;
	private int gameMode;
	
	public JPanelScore(Window window, int gameMode) {
		this.gameMode = gameMode;
		this.setBounds(-1, -1, window.getWidth() + 1, 41);
		this.setLayout(null);
		Border border = BorderFactory.createLineBorder(new Color(150,75,0), 1);
		this.setBorder(border);
		this.setBackground(new Color(237,118,0));
		this.initialize();
	}
	
	public void initialize(){
		Font font = new Font("Helvetica", Font.BOLD, 18);
	
		switch (gameMode) {
		case Window.MODE_ONE_PLAYER:
			jLabelPlayer1 = new JLabel("Score: ");
			jLabelPlayer1.setBounds(180,0,70,45);
			jLabelPlayer1.setFont(font);
			jLabelPlayer1.setForeground(new Color(242,242,242));			this.add(jLabelPlayer1);
			
			jLabelScorePlayer1 = new JLabel("0");
			jLabelScorePlayer1.setBounds(240,0,100,45);
			jLabelScorePlayer1.setFont(font);
			jLabelScorePlayer1.setForeground(new Color(242,242,242));
			this.add(jLabelScorePlayer1);
			break;
		case Window.MODE_TWO_PLAYERS:
			jLabelPlayer1 = new JLabel("Player 1: ");
			jLabelPlayer1.setBounds(40,0,100,45);
			jLabelPlayer1.setFont(font);
			jLabelPlayer1.setForeground(new Color(242,242,242));
			this.add(jLabelPlayer1);
			
			jLabelScorePlayer1 = new JLabel("0");
			jLabelScorePlayer1.setBounds(140,0,100,45);
			jLabelScorePlayer1.setFont(font);
			jLabelScorePlayer1.setForeground(new Color(242,242,242));
			this.add(jLabelScorePlayer1);
			
			jLabelPlayer2 = new JLabel("Player 2: ");
			jLabelPlayer2.setBounds(280,0,100,45);
			jLabelPlayer2.setFont(font);
			jLabelPlayer2.setForeground(new Color(242,242,242));
			this.add(jLabelPlayer2);		
			
			jLabelScorePlayer2 = new JLabel("0");
			jLabelScorePlayer2.setBounds(380,0,100,45);
			jLabelScorePlayer2.setFont(font);
			jLabelScorePlayer2.setForeground(new Color(242,242,242));
			this.add(jLabelScorePlayer2);
			break;
		}

	}
	
	public void setScorePlayer1(String score){
		jLabelScorePlayer1.setText(score);
	}
	
	public void setScorePlayer2(String score){
		jLabelScorePlayer2.setText(score);
	}
	
}
