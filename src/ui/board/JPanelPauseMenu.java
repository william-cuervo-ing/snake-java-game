package ui.board;

import logic.GameConstants;
import ui.UIUtils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JPanelPauseMenu extends JPanel{
	
	public JPanelPauseMenu(ActionListener listener) {
		setLayout(null);
		setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
		setBackground(GameConstants.BACKGROUND_MENU_COLOR);
		initialize(listener);
	}
	
	public void initialize(ActionListener listener){
		JButton jbtnResume = UIUtils.buildButton("Resume", listener, GameConstants.ACTION_COMMAND_RESUME_GAME);
		jbtnResume.setBounds(100, 220, 240, 40);
		add(jbtnResume);

		JButton jbtnExit = UIUtils.buildButton("Exit", listener, GameConstants.ACTION_COMMAND_EXIT);
		jbtnExit.setBounds(100, 320, 240, 40);
		add(jbtnExit);
	}
}
