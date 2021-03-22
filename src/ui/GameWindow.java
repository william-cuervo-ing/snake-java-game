package ui;

import logic.DirectionSnakeEnum;
import logic.controller.Controller;
import logic.controller.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import ui.board.JPanelPauseMenu;
import ui.board.JPanelSpaceGame;
import ui.board.PanelScoreGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener, ActionListener {


    private JPanelEndGame panelEndGame;
    private JPanelSpaceGame panelSpaceGame;
    private PanelScoreGame panelScoreGameMenu;
    private JPanelMainMenu panelMainMenu;
    private JPanelPauseMenu panelPauseMenu;

    private final Controller controller;

    public GameWindow(Controller controller) {
        this.controller = controller;
        this.addKeyListener(this);
        this.setLayout(null);
        this.setTitle("Snake Game");
        this.setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.createScreens();
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private void createScreens() {
        panelMainMenu = new JPanelMainMenu(this);
        this.add(panelMainMenu);

        panelSpaceGame = new JPanelSpaceGame(controller);
        this.add(panelSpaceGame);

        panelScoreGameMenu = new PanelScoreGame(this);
        this.add(panelScoreGameMenu);

        panelPauseMenu = new JPanelPauseMenu(this);
        this.add(panelPauseMenu);

        panelEndGame = new JPanelEndGame(this);
        this.add(panelMainMenu);
    }

    public void showMainMenu() {
        this.hidePanels();
        panelMainMenu.setVisible(true);
    }

    public void printScores(Snake snakeOne, Snake snakeTwo) {
        panelScoreGameMenu.setScorePlayer1(snakeOne.getScore());
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            panelScoreGameMenu.setScorePlayer2(snakeTwo.getScore());
        }
    }

    public void showPauseMenu() {
        this.hidePanels();
        panelPauseMenu.setVisible(true);
        panelSpaceGame.pause();
    }

    public void startGame() {
        Thread thread = new Thread(panelSpaceGame);
        thread.start();
        showGameBoard();
    }

    public void showGameBoard() {
        this.hidePanels();
        panelScoreGameMenu.setVisible(true);
        panelSpaceGame.resume();
    }

    public void showGameOver(Snake snakeOne, Snake snakeTwo) {
        this.hidePanels();
        panelEndGame.setVisible(true);
        panelEndGame.setScorePlayer1(snakeOne.getScore());
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            panelEndGame.setScorePlayer1(snakeTwo.getScore());
        }
        panelSpaceGame.terminate();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.controller.setDirectionSnakeOne(DirectionSnakeEnum.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.controller.setDirectionSnakeOne(DirectionSnakeEnum.RIGHT);
                break;
            case KeyEvent.VK_UP:
                this.controller.setDirectionSnakeOne(DirectionSnakeEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                this.controller.setDirectionSnakeOne(DirectionSnakeEnum.DOWN);
                break;
            case KeyEvent.VK_ESCAPE:
                controller.pause();
                break;
        }
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            switch (key) {
                case KeyEvent.VK_A:
                    this.controller.setDirectionSnakeTwo(DirectionSnakeEnum.LEFT);
                    break;
                case KeyEvent.VK_D:
                    this.controller.setDirectionSnakeTwo(DirectionSnakeEnum.RIGHT);
                    break;
                case KeyEvent.VK_W:
                    this.controller.setDirectionSnakeTwo(DirectionSnakeEnum.UP);
                    break;
                case KeyEvent.VK_S:
                    this.controller.setDirectionSnakeTwo(DirectionSnakeEnum.DOWN);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case UIConstants.ACTION_COMMAND_ONE_PLAYER:
                controller.startGameModeOnePlayer();
                break;
            case UIConstants.ACTION_COMMAND_TWO_PLAYERS:
                controller.startGameModeTwoPlayers();
                break;
            case UIConstants.ACTION_COMMAND_EXIT:
                controller.exit();
                break;
            case UIConstants.ACTION_COMMAND_MAIN_MENU:
                controller.returnToMainMenu();
                break;
            case UIConstants.ACTION_COMMAND_RESUME_GAME:
                controller.resume();
                break;
        }
    }

    private void hidePanels() {
        for (Component component : this.getComponents()) {
            component.setVisible(false);
        }
    }

    public void setSnakes(Snake snakeOne, Snake snakeTwo) {
        panelSpaceGame.setSnakeOne(snakeOne);
        panelSpaceGame.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Prize prize) {
        panelSpaceGame.setPrize(prize);
    }
}