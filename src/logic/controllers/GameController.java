package logic.controllers;

import logic.DirectionSnakeEnum;
import logic.GameConstants;
import logic.models.Prize;
import logic.models.Snake;
import logic.models.Vertabra;
import ui.board.PanelGame;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController implements GameEventListener {

    public static GameMode gameMode = GameMode.ONE_PLAYER;

    private Snake snakeOne;
    private Snake snakeTwo;
    private final PanelGame panelGame;
    private boolean isPaused;

    public GameController(PanelGame panelGame){
        this.panelGame = panelGame;
    }

    public void startGame() {
        this.initSnakes();
        this.createPrize();
        panelGame.startGame();
    }

    private void initSnakes() {
        snakeOne = new Snake(this);
        snakeTwo = GameController.gameMode == GameMode.TWO_PLAYERS ?
            new Snake(this) : null;
        snakeOne.start();
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            snakeTwo.start();
        }
        this.panelGame.setSnakes(this.snakeOne, this.snakeTwo);
    }

    private void createPrize() {
        List<Vertabra> vertabras = (this.snakeTwo == null ? this.snakeOne.getBody().stream() :
            Stream.concat(
                this.snakeOne.getBody().stream(),
                this.snakeTwo.getBody().stream()
            )
        ).collect(Collectors.toList());

        Random random = new Random();
        int x = 0, y = 0;
        boolean validPosition = false;
        while(!validPosition){
            x = random.nextInt(GameConstants.SCALAR_POSITION_GAME) * GameConstants.VERTABRA_SIZE;
            y = random.nextInt(GameConstants.SCALAR_POSITION_GAME) * GameConstants.VERTABRA_SIZE;
            validPosition = true;
            for (Vertabra vertabra : vertabras) {
                if(vertabra.getX() == x || vertabra.getY() == y){
                    validPosition = false;
                    break;
                }
                // TODO: What happens if all screen is full of snakes?
            }
        }
        this.panelGame.setPrize(new Prize(x, y));
    }

    @Override
    public void addScorePlayerOne() {
        snakeOne.setScore(snakeOne.getScore() + GameConstants.SCORE_BY_PRIZE);
        panelGame.printScores(snakeOne, snakeTwo);
        createPrize();
    }

    @Override
    public void addScorePlayerTwo() {
        snakeOne.setScore(snakeOne.getScore() + GameConstants.SCORE_BY_PRIZE);
        snakeTwo.setScore(snakeTwo.getScore() + GameConstants.SCORE_BY_PRIZE);
        panelGame.printScores(snakeOne, snakeTwo);
        createPrize();
    }

    @Override
    public void dieSnake(Snake snake) {
        snake.die();
        panelGame.showGameOver(snakeOne, snakeTwo);
    }

    public void startGameModeOnePlayer() {
        GameController.gameMode = GameMode.ONE_PLAYER;
        startGame();
    }

    public void startGameModeTwoPlayers() {
        GameController.gameMode = GameMode.TWO_PLAYERS;
        startGame();
    }

    @Override
    public void setDirectionSnakeOne(DirectionSnakeEnum direction){
        this.snakeOne.setDirection(direction);
    }

    @Override
    public void setDirectionSnakeTwo(DirectionSnakeEnum direction){
        this.snakeOne.setDirection(direction);
    }

    public void pause() {
        snakeOne.pause();
        if(gameMode == GameMode.TWO_PLAYERS){
            snakeTwo.pause();
        }
        this.isPaused = true;
    }

    public void resume() {
        snakeOne.resumeSnake();
        if(gameMode == GameMode.TWO_PLAYERS){
            snakeTwo.resumeSnake();
        }
        this.isPaused = false;
    }

    public void finalizeGame() {
        snakeOne.die();
        snakeTwo.die();
    }

    public boolean isPaused() {
        return isPaused;
    }
}
