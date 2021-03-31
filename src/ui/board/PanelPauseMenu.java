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
		jbtnResume.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 220, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		add(jbtnResume);

		JButton jbtnExit = UIUtils.buildButton("Exit", listener, GameConstants.ACTION_COMMAND_EXIT);
		jbtnExit.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 320, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
		add(jbtnExit);
	}
}
