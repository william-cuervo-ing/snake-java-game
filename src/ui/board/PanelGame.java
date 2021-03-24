package ui.board;

import logic.DirectionSnakeEnum;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import ui.GameWindow;
import ui.JPanelEndGame;
import ui.JPanelMainMenu;
import ui.UICommon;
import ui.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements KeyListener, UICommon {

    private JPanelEndGame panelEndGame;
    private JPanelSpaceGame panelSpaceGame;
    private PanelScoreGame panelScoreGameMenu;
    private JPanelPauseMenu panelPauseMenu;
    private final GameController controller;

    public PanelGame(ActionListener listener){
        controller = new GameController();
        setLayout(null);
        setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        addKeyListener(this);
        setLayout(null);
        createScreens(listener);
    }

    private void createScreens(ActionListener listener) {
        panelSpaceGame = new JPanelSpaceGame(controller);
        add(panelSpaceGame);

        panelScoreGameMenu = new PanelScoreGame();
        add(panelScoreGameMenu);

        panelPauseMenu = new JPanelPauseMenu(listener);
        add(panelPauseMenu);

        panelEndGame = new JPanelEndGame(listener);
        add(panelEndGame);
    }

    public void printScores(Snake snakeOne, Snake snakeTwo) {
        panelScoreGameMenu.setScorePlayer1(snakeOne.getScore());
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            panelScoreGameMenu.setScorePlayer2(snakeTwo.getScore());
        }
    }

    public void showPauseMenu() {
        hidePanels();
        panelPauseMenu.setVisible(true);
        panelSpaceGame.pause();
    }

    public void startGame() {
        Thread thread = new Thread(panelSpaceGame);
        thread.start();
        showGameBoard();
    }

    public void showGameBoard() {
        hidePanels();
        panelScoreGameMenu.setVisible(true);
        panelSpaceGame.resume();
    }

    public void showGameOver(Snake snakeOne, Snake snakeTwo) {
        hidePanels();
        panelEndGame.setVisible(true);
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
                controller.setDirectionSnakeOne(DirectionSnakeEnum.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                controller.setDirectionSnakeOne(DirectionSnakeEnum.RIGHT);
                break;
            case KeyEvent.VK_UP:
                controller.setDirectionSnakeOne(DirectionSnakeEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                controller.setDirectionSnakeOne(DirectionSnakeEnum.DOWN);
                break;
            case KeyEvent.VK_ESCAPE:
                controller.pause();
                break;
        }
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            switch (key) {
                case KeyEvent.VK_A:
                    controller.setDirectionSnakeTwo(DirectionSnakeEnum.LEFT);
                    break;
                case KeyEvent.VK_D:
                    controller.setDirectionSnakeTwo(DirectionSnakeEnum.RIGHT);
                    break;
                case KeyEvent.VK_W:
                    controller.setDirectionSnakeTwo(DirectionSnakeEnum.UP);
                    break;
                case KeyEvent.VK_S:
                    controller.setDirectionSnakeTwo(DirectionSnakeEnum.DOWN);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    private void hidePanels() {
        for (Component component : getComponents()) {
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
