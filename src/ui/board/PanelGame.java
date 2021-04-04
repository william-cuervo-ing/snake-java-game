package ui.board;

import logic.SnakeDirectionEnum;
import logic.GameConstants;
import logic.controllers.GameController;
import logic.GameModeEnum;
import logic.models.Point;
import logic.models.Snake;
import ui.navigation.GameWindow;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements ActionListener, KeyListener {

    private PanelGameOver panelGameOver;
    private PanelPauseMenu panelPauseMenu;

    private PanelScoreGame panelScore;
    private PanelGameBoard panelGameBoard;

    private final GameController controller;
    private final GameWindow gameWindow;

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
        panelGameBoard = new PanelGameBoard(controller);
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
        if (GameController.gameMode == GameModeEnum.TWO_PLAYERS) {
            panelScore.setScorePlayer2(snakeTwo.getScore());
        }
    }

    public void showPauseMenu() {
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
        if (GameController.gameMode == GameModeEnum.TWO_PLAYERS) {
            panelGameOver.setScorePlayer2(snakeOne.getScore(), snakeTwo.getScore());
        }
        showComponent(panelGameOver);
        panelGameBoard.terminate();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.controller.setDirectionSnakeOne(SnakeDirectionEnum.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.controller.setDirectionSnakeOne(SnakeDirectionEnum.RIGHT);
                break;
            case KeyEvent.VK_UP:
                this.controller.setDirectionSnakeOne(SnakeDirectionEnum.UP);
                break;
            case KeyEvent.VK_DOWN:
                this.controller.setDirectionSnakeOne(SnakeDirectionEnum.DOWN);
                break;
            case KeyEvent.VK_ESCAPE:
                tooglePause();
                break;
        }
        if (GameController.gameMode == GameModeEnum.TWO_PLAYERS) {
            switch (key) {
                case KeyEvent.VK_A:
                    this.controller.setDirectionSnakeTwo(SnakeDirectionEnum.LEFT);
                    break;
                case KeyEvent.VK_D:
                    this.controller.setDirectionSnakeTwo(SnakeDirectionEnum.RIGHT);
                    break;
                case KeyEvent.VK_W:
                    this.controller.setDirectionSnakeTwo(SnakeDirectionEnum.UP);
                    break;
                case KeyEvent.VK_S:
                    this.controller.setDirectionSnakeTwo(SnakeDirectionEnum.DOWN);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    private void showComponent(Component component) {
        hideComponents();
        component.setVisible(true);
    }

    private void hideComponents() {
        for (Component componentAdded : getComponents()) {
            componentAdded.setVisible(false);
        }
    }

    public void setSnakes(Snake snakeOne, Snake snakeTwo) {
        panelGameBoard.setSnakeOne(snakeOne);
        panelGameBoard.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Point prize) {
        panelGameBoard.setPrize(prize);
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
