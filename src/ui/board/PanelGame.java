package ui.board;

import logic.controllers.GameController;
import ui.UIConstants;

import javax.swing.*;

public class PanelGame extends JPanel {

    private JPanelSpaceGame panelSpaceGame;
    private PanelScoreGame panelScoreGameMenu;
    private final GameController controller;

    public PanelGame(GameController controller){
        this.controller = controller;
        setLayout(null);
        setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        init();
    }

    private void init() {
        panelSpaceGame = new JPanelSpaceGame(controller);
        panelScoreGameMenu = new PanelScoreGame();
    }

}
