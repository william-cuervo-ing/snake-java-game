package ui.board;

import logic.GameConstants;
import ui.UIUtils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelPauseMenu extends JPanel{
	
	public PanelPauseMenu(ActionListener listener) {
		setLayout(null);
		setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
		setBackground(GameConstants.BACKGROUND_MENU_COLOR);
		initialize(listener);
	}
	
	public void initialize(ActionListener listener){
		JButton jbtnResume = UIUtils.buildButton("Resume", listener, GameConstants.ACTION_COMMAND_RESUME_GAME);
		jbtnResume.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 140, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		add(jbtnResume);

		JButton jbtnMainMenu = UIUtils.buildButton("Main Menu", listener, GameConstants.ACTION_COMMAND_MAIN_MENU);
		jbtnMainMenu.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 240, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		add(jbtnMainMenu);

		JButton jbtnExit = UIUtils.buildButton("Exit", listener, GameConstants.ACTION_COMMAND_EXIT);
		jbtnExit.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 340, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		add(jbtnExit);
	}
}
