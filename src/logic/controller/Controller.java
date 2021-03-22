package logic.controller;

import logic.DirectionSnakeEnum;
import logic.GameConstants;
import logic.models.Prize;
import logic.models.Snake;
import logic.models.Vertabra;
import ui.GameWindow;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements GameEventListener {

    private Snake snakeOne;
    private Snake snakeTwo;
    private Prize prize;

    public static GameMode gameMode;
    private final GameWindow window;

    public Controller(){
        window = new GameWindow(this);
    }

    public void init(){
        this.window.showMainMenu();
    }

    public void startGame() {
        this.initSnakes();
        this.createPrize();
        this.window.startGame();
    }

    private void initSnakes() {
        snakeOne = new Snake(this);
        snakeTwo = Controller.gameMode == GameMode.TWO_PLAYERS ?
            new Snake(this) : null;
        snakeOne.start();
        if (Controller.gameMode == GameMode.TWO_PLAYERS) {
            snakeTwo.start();
        }
        this.window.setSnakes(this.snakeOne, this.snakeTwo);
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
        this.prize = new Prize(x, y);
        this.window.setPrize(this.prize);
    }

    @Override
    public void addScorePlayerOne() {
        snakeOne.setScore(snakeOne.getScore() + GameConstants.SCORE_BY_PRIZE);
        window.printScores(snakeOne, snakeTwo);
    }

    @Override
    public void addScorePlayerTwo() {
        snakeOne.setScore(snakeOne.getScore() + GameConstants.SCORE_BY_PRIZE);
        snakeTwo.setScore(snakeTwo.getScore() + GameConstants.SCORE_BY_PRIZE);
        window.printScores(snakeOne, snakeTwo);
    }

    @Override
    public void dieSnake(Snake snake) {
        snake.die();
        window.showGameOver(snakeOne, snakeTwo);
    }

    @Override
    public void startGameModeOnePlayer() {
        Controller.gameMode = GameMode.ONE_PLAYER;
        window.startGame();
    }

    @Override
    public void startGameModeTwoPlayers() {
        Controller.gameMode = GameMode.TWO_PLAYERS;
        window.startGame();
    }

    @Override
    public void setDirectionSnakeOne(DirectionSnakeEnum direction){
        this.snakeOne.setDirection(direction);
    }

    @Override
    public void setDirectionSnakeTwo(DirectionSnakeEnum direction){
        this.snakeOne.setDirection(direction);
    }

    @Override
    public void pause() {
        window.showPauseMenu();
        snakeOne.pause();
        if(gameMode == GameMode.TWO_PLAYERS){
            snakeTwo.pause();
        }
    }

    @Override
    public void resume() {
        window.showGameBoard();
        snakeOne.resumeSnake();
        if(gameMode == GameMode.TWO_PLAYERS){
            snakeTwo.resumeSnake();
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void returnToMainMenu() {
        snakeOne.die();
        snakeTwo.die();
        window.showMainMenu();
    }
}
