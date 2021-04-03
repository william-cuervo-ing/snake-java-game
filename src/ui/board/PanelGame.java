package ui.board;

import logic.DirectionSnakeEnum;
<<<<<<< HEAD
import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.models.Point;
import logic.models.Snake;
import ui.PanelGameOver;
import ui.navigation.GameWindow;

import javax.swing.JPanel;
import java.awt.Component;
=======
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
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

<<<<<<< HEAD
public class PanelGame extends JPanel implements ActionListener, KeyListener {

    private PanelGameOver panelGameOver;
    private PanelPauseMenu panelPauseMenu;

    private PanelScoreGame panelScore;
    private panelGameBoard panelGameBoard;

=======
public class PanelGame extends JPanel implements KeyListener, UICommon {

    private JPanelEndGame panelEndGame;
    private JPanelSpaceGame panelSpaceGame;
    private PanelScoreGame panelScoreGameMenu;
    private JPanelPauseMenu panelPauseMenu;
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
    private final GameController controller;
    private final GameWindow gameWindow;

<<<<<<< HEAD
    public PanelGame(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.controller = new GameController(this);
        setLayout(null);
        setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
        addKeyListener(this);
        setFocusable(true);
        init();
        requestFocusInWindow();
    }

    private void init() {
        panelGameBoard = new panelGameBoard(controller);
        add(panelGameBoard);

        panelScore = new PanelScoreGame();
        add(panelScore);

        panelPauseMenu = new PanelPauseMenu(this);
        add(panelPauseMenu);

        panelGameOver = new PanelGameOver(this);
        add(panelGameOver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case GameConstants.ACTION_COMMAND_MAIN_MENU:
                controller.finalizeGame();
                gameWindow.showMainMenu();
                break;
            case GameConstants.ACTION_COMMAND_RESUME_GAME:
                resume();
                break;
            case GameConstants.ACTION_COMMAND_EXIT:
                gameWindow.exit();
                break;
        }
    }

    public void startGameModeOnePlayer() {
        this.controller.startGameModeOnePlayer();
    }

    public void startGameModeTwoPlayers() {
        this.controller.startGameModeTwoPlayers();
    }

    public void printScores(Snake snakeOne, Snake snakeTwo) {
        panelScore.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            panelScore.setScorePlayer2(snakeTwo.getScore());
=======
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
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
        }
    }

    public void showPauseMenu() {
<<<<<<< HEAD
        showComponent(panelPauseMenu);
        panelGameBoard.pause();
    }

    public void startGame() {
        showGameBoard();
        Thread thread = new Thread(panelGameBoard);
        thread.start();
    }

    private void showGameBoard() {
        hideComponents();
        panelScore.setVisible(true);
        panelGameBoard.setVisible(true);
        panelGameBoard.resume();
    }

    public void showGameOver(Snake snakeOne, Snake snakeTwo) {
        panelGameOver.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            panelGameOver.setScorePlayer2(snakeOne.getScore(), snakeTwo.getScore());
        }
        showComponent(panelGameOver);
        panelGameBoard.terminate();
=======
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
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
<<<<<<< HEAD
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
                tooglePause();
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
=======
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
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

<<<<<<< HEAD
    private void showComponent(Component component) {
        hideComponents();
        component.setVisible(true);
    }

    private void hideComponents() {
        for (Component componentAdded : getComponents()) {
            componentAdded.setVisible(false);
=======
    private void hidePanels() {
        for (Component component : getComponents()) {
            component.setVisible(false);
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
        }
    }

    public void setSnakes(Snake snakeOne, Snake snakeTwo) {
<<<<<<< HEAD
        panelGameBoard.setSnakeOne(snakeOne);
        panelGameBoard.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Point prize) {
        panelGameBoard.setPrize(prize);
=======
        panelSpaceGame.setSnakeOne(snakeOne);
        panelSpaceGame.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Prize prize) {
        panelSpaceGame.setPrize(prize);
>>>>>>> fbc6431a6ed5891b9802d4cec20b6c126ab9be41
    }

    private void tooglePause() {
        if (controller.isPaused()) {
            resume();
        } else {
            pause();
        }
    }

    private void pause() {
        showPauseMenu();
        controller.pause();
    }

    private void resume() {
        showGameBoard();
        controller.resume();
    }
}
