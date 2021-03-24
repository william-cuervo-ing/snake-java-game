package ui;

import logic.DirectionSnakeEnum;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import ui.board.JPanelPauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener, ActionListener {

    private JPanelEndGame panelEndGame;
    private JPanelMainMenu panelMainMenu;
    private JPanelPauseMenu panelPauseMenu;

    private final GameController controller;

    public GameWindow(GameController controller) {
        this.controller = controller;
        this.addKeyListener(this);
        this.setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        this.setLayout(null);
        this.getContentPane().setLayout(null);
        this.setTitle("Snake Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createScreens();
        this.setVisible(true);
        this.requestFocusInWindow();
    }

    private void createScreens() {
        panelMainMenu = new JPanelMainMenu(this);
        panelPauseMenu = new JPanelPauseMenu(this);
        panelEndGame = new JPanelEndGame(this);
    }

    public void showMainMenu() {
        this.showComponent(panelMainMenu);
    }

    public void printScores(Snake snakeOne, Snake snakeTwo) {
        panelScoreGameMenu.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            panelScoreGameMenu.setScorePlayer2(snakeTwo.getScore());
        }
    }

    public void showPauseMenu() {
        this.showComponent(panelPauseMenu);
        panelSpaceGame.pause();
    }

    public void startGame() {
        System.out.println("window startGame");
        showGameBoard();
//        Thread thread = new Thread(panelSpaceGame);
//        thread.start();
    }

    public void showGameBoard() {
        System.out.println("showGameBoard ");
//        panelSpaceGame.resume();
        this.showComponent(panelSpaceGame);
    }

    public void showGameOver(Snake snakeOne, Snake snakeTwo) {
        this.showComponent(panelEndGame);
        panelEndGame.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
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
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
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

    private void showComponent(Component component) {
        for (int i = 0; i < getContentPane().getComponents().length; i++) {
            getContentPane().getComponents()[i].setVisible(false);
            getContentPane().remove(i);
            i = 0;
        }
        getContentPane().add(component);
        component.setVisible(true);
        System.out.println("Finaliza showComponent"  + getContentPane().getComponents().length);
    }

    public void setSnakes(Snake snakeOne, Snake snakeTwo) {
        panelSpaceGame.setSnakeOne(snakeOne);
        panelSpaceGame.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Prize prize) {
        panelSpaceGame.setPrize(prize);
    }
}