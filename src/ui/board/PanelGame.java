package ui.board;

import logic.DirectionSnakeEnum;
import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import ui.PanelEndGame;
import ui.navigation.NavigationListener;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements ActionListener, KeyListener {

    private PanelEndGame panelEndGame;
    private PanelPauseMenu panelPauseMenu;

    private PanelScoreGame panelScore;
    private panelGameBoard panelGameBoard;

    private final GameController controller;
    private final NavigationListener navigationListener;

    public PanelGame(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
        this.controller = new GameController(this);
        setLayout(null);
        setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
        init(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void init(ActionListener listener) {
        panelGameBoard = new panelGameBoard(controller);
        add(panelGameBoard);

        panelScore = new PanelScoreGame();
        add(panelScore);

        panelPauseMenu = new PanelPauseMenu(listener);
        add(panelPauseMenu);

        panelEndGame = new PanelEndGame(listener);
        add(panelEndGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case GameConstants.ACTION_COMMAND_MAIN_MENU:
                controller.finalizeGame();
                navigationListener.showMainMenu();
                break;
            case GameConstants.ACTION_COMMAND_RESUME_GAME:
                resume();
                break;
            case GameConstants.ACTION_COMMAND_EXIT:
                navigationListener.exit();
                break;
        }
    }

    public void startGameModeOnePlayer() {
        this.controller.startGameModeOnePlayer();
        this.panelScore.setupScoreLabels();
    }

    public void startGameModeTwoPlayers() {
        this.controller.startGameModeTwoPlayers();
        this.panelScore.setupScoreLabels();
    }

    public void printScores(Snake snakeOne, Snake snakeTwo) {
        panelScore.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
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
        panelEndGame.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            panelEndGame.setScorePlayer1(snakeTwo.getScore());
        }
        showComponent(panelEndGame);
        panelGameBoard.terminate();
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

    public void setPrize(Prize prize) {
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
