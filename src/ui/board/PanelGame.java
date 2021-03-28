package ui.board;

import logic.DirectionSnakeEnum;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import ui.PanelEndGame;
import ui.UIConstants;
import ui.navigation.NavigationListener;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements ActionListener, KeyListener {

    private PanelEndGame panelEndGame;
    private JPanelPauseMenu panelPauseMenu;

    private PanelScoreGame panelScore;
    private JPanelSpaceGame panelSpaceGame;

    private final GameController controller;
    private final NavigationListener navigationListener;

    public PanelGame(ActionListener listener, NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
        this.controller = new GameController(this);
        setLayout(null);
        setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        init(listener);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void init(ActionListener listener) {
        panelSpaceGame = new JPanelSpaceGame(controller);
        add(panelSpaceGame);

        panelScore = new PanelScoreGame();
        add(panelScore);

        panelPauseMenu = new JPanelPauseMenu(listener);
        add(panelPauseMenu);

        panelEndGame = new PanelEndGame(listener);
        add(panelEndGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case UIConstants.ACTION_COMMAND_MAIN_MENU:
                controller.finalizeGame();
                navigationListener.showMainMenu();
                break;
            case UIConstants.ACTION_COMMAND_RESUME_GAME:
                resume();
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
        }
    }

    public void showPauseMenu() {
        showComponent(panelPauseMenu);
        panelSpaceGame.pause();
    }

    public void startGame() {
        showGameBoard();
        Thread thread = new Thread(panelSpaceGame);
        thread.start();
    }

    private void showGameBoard() {
        hideComponents();
        panelScore.setVisible(true);
        panelSpaceGame.setVisible(true);
    }

    public void showGameOver(Snake snakeOne, Snake snakeTwo) {
        panelEndGame.setScorePlayer1(snakeOne.getScore());
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            panelEndGame.setScorePlayer1(snakeTwo.getScore());
        }
        showComponent(panelEndGame);
        panelSpaceGame.terminate();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        System.out.println("key: " + key);
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
        panelSpaceGame.setSnakeOne(snakeOne);
        panelSpaceGame.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Prize prize) {
        panelSpaceGame.setPrize(prize);
    }

    private void tooglePause() {
        if (controller.isPaused()) {
            resume();
        } else {
            pause();
        }
    }

    private void pause() {
        controller.pause();
        showPauseMenu();
    }

    private void resume() {
        controller.pause();
        showGameBoard();
    }
}
