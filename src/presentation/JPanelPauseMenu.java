package presentation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JPanelPauseMenu extends JPanel{

	private JButton jbtnResume, jbtnExit;
	public static final String RESUME = "Resume";
	
	public JPanelPauseMenu(Window window) {
		this.setLayout(null);
		this.setBounds(0, 0, window.getWidth(), window.getHeight());
		this.setBackground(new Color(27,173,204));
		this.initialize();
	}
	
	public void initialize(){
		Font font = new Font("Helvetica", Font.BOLD, 20);
		Border border = BorderFactory.createLineBorder(Color.white, 1);
		
		jbtnResume = new JButton("Resume");
		jbtnResume.setBounds(100, 220, 240, 40);
		jbtnResume.setFont(font);
		jbtnResume.setBackground(new Color(47,101,117));
		jbtnResume.setForeground(Color.white);
		jbtnResume.setFocusPainted(false);
		jbtnResume.setBorder(border);
		jbtnResume.setActionCommand(RESUME);
		this.add(jbtnResume);
		
		jbtnExit = new JButton("Exit");
		jbtnExit.setBounds(100, 320, 240, 40);
		jbtnExit.setFont(font);
		jbtnExit.setBackground(new Color(47,101,117));
		jbtnExit.setForeground(Color.white);
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(border);
		jbtnExit.setActionCommand(JPanelMainMenu.EXIT);
		this.add(jbtnExit);
	}

	public JButton getJbtnResume() {
		return jbtnResume;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}
	
	
	
	
}
