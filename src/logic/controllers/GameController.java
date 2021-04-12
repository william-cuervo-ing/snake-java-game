package logic.controllers;

import logic.SnakeDirectionEnum;
import logic.GameConstants;
import logic.GameModeEnum;
import logic.models.Point;
import logic.models.Snake;
import ui.board.PanelGame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController implements GameEventListener {

    public static GameModeEnum gameMode = GameModeEnum.ONE_PLAYER;
    final boolean[][] boardPositions = new boolean[GameConstants.POSTISIONS_AVAILABLE_PER_ROW][GameConstants.POSTISIONS_AVAILABLE_PER_ROW];

    private Snake snakeOne;
    private Snake snakeTwo;
    private boolean isPaused;
    private final PanelGame panelGame;
    private final Random random = new Random();

    public GameController(PanelGame panelGame) {
        this.panelGame = panelGame;
    }

    public void startGame() {
        initSnakes();
        createPrize();
        panelGame.startGame();
        panelGame.printScores(this.snakeOne, this.snakeTwo);
    }

    private void initSnakes() {
        snakeOne = new Snake(this);
        snakeTwo = GameController.gameMode == GameModeEnum.TWO_PLAYERS ?
            new Snake(this) : null;
        snakeOne.start();
        if (GameController.gameMode == GameModeEnum.TWO_PLAYERS) {
            snakeTwo.start();
        }
        this.panelGame.setSnakes(this.snakeOne, this.snakeTwo);
    }

    private void createPrize() {
        for (int i = 0; i < boardPositions.length; i++) {
            boolean[] aux = new boolean[GameConstants.POSTISIONS_AVAILABLE_PER_ROW];
            Arrays.fill(aux, false);
            boardPositions[i] = aux;
        }
        List<Point> vertabras = gameMode == GameModeEnum.ONE_PLAYER ? this.snakeOne.getBody() :
            new ArrayList<Point>() {{
                addAll(snakeOne.getBody());
                addAll(snakeTwo.getBody());
            }};
        vertabras.forEach((v) -> boardPositions[v.getX()][v.getY()] = true);
        List<Point> posicionesLibres = new ArrayList<>();
        for (int i = 0; i < boardPositions.length; i++) {
            for (int j = 0; j < boardPositions[i].length; j++) {
                if (!boardPositions[i][j]) {
                    posicionesLibres.add(new Point(i, j));
                }
            }
        }
        Point randomPosition = posicionesLibres.get(random.nextInt(posicionesLibres.size()));
        this.panelGame.setPrize(new Point(randomPosition.getX(), randomPosition.getY()));
    }

    @Override
    public void addScorePlayerOne() {
        snakeOne.setScore(snakeOne.getScore() + GameConstants.SCORE_BY_PRIZE);
        panelGame.printScores(snakeOne, snakeTwo);
        createPrize();
        snakeOne.grow();
    }

    @Override
    public void addScorePlayerTwo() {
        snakeTwo.setScore(snakeTwo.getScore() + GameConstants.SCORE_BY_PRIZE);
        panelGame.printScores(snakeOne, snakeTwo);
        createPrize();
        snakeTwo.grow();
    }

    @Override
    public void dieSnake(Snake snake) {
        snake.die();
        panelGame.showGameOver(snakeOne, snakeTwo);
    }

    public void startGameModeOnePlayer() {
        GameController.gameMode = GameModeEnum.ONE_PLAYER;
        startGame();
    }

    public void startGameModeTwoPlayers() {
        GameController.gameMode = GameModeEnum.TWO_PLAYERS;
        startGame();
    }

    @Override
    public void setDirectionSnakeOne(SnakeDirectionEnum direction) {
        this.snakeOne.setDirection(direction);
    }

    @Override
    public void setDirectionSnakeTwo(SnakeDirectionEnum direction) {
        this.snakeTwo.setDirection(direction);
    }

    public void pause() {
        snakeOne.pause();
        if (gameMode == GameModeEnum.TWO_PLAYERS) {
            snakeTwo.pause();
        }
        this.isPaused = true;
    }

    public void resume() {
        snakeOne.resumeSnake();
        if (gameMode == GameModeEnum.TWO_PLAYERS) {
            snakeTwo.resumeSnake();
        }
        this.isPaused = false;
    }

    public void finalizeGame() {
        snakeOne.die();
        if (gameMode == GameModeEnum.TWO_PLAYERS) {
            snakeTwo.die();
        }
    }

    public boolean isPaused() {
        return isPaused;
    }
}
