package ui;

import logic.controllers.GameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements UICommon, ActionListener {

    private JPanelMainMenu panelMainMenu;

    public GameWindow() {
        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        getContentPane().setLayout(null);
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        createScreens();
        showMainMenu();
    }

    private void createScreens() {
        panelMainMenu = new JPanelMainMenu(this);
    }

    public void showMainMenu() {
        showComponent(this.getContentPane(), panelMainMenu);
    }


    public void startGameModeOnePlayer() {
        new GameController().init();
    }

    public void startGameModeTwoPlayers() {
        new GameController().init();
    }


    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case UIConstants.ACTION_COMMAND_ONE_PLAYER:
                startGameModeOnePlayer();
                break;
            case UIConstants.ACTION_COMMAND_TWO_PLAYERS:
                startGameModeTwoPlayers();
                break;
            case UIConstants.ACTION_COMMAND_MAIN_MENU:
                showMainMenu();
                break;
            case UIConstants.ACTION_COMMAND_EXIT:
                exit();
                break;
        }
    }

    public void exit() {
        System.exit(0);
    }
}
