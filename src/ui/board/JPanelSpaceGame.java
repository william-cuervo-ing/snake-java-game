package ui.board;

import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameEventListener;
import logic.controllers.GameMode;
import logic.models.Prize;
import logic.models.Snake;
import logic.models.Vertabra;
import ui.UIConstants;

import javax.swing.*;
import java.awt.*;

public class JPanelSpaceGame extends JPanel implements Runnable {

    private final GameEventListener gameEventListener;
    private Snake snakeOne;
    private Snake snakeTwo;
    private boolean running;
    private boolean paused;
    private Prize prize;

    public JPanelSpaceGame(GameEventListener gameEventListener) {
        setName(this.getClass().getName());
        this.gameEventListener = gameEventListener;
        init();
    }

    private void init() {
        this.setLayout(null);
        this.setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        this.setBackground(UIConstants.BACKGROUND_GAME_BOARD_COLOR);
        this.running = true;
        this.paused = false;
    }

    public void paint(Graphics g) {
        super.paint(g);
        paintSnake(g, snakeOne, UIConstants.BACKGROUND_COLOR_SNAKE_ONE);
        if (GameController.gameMode == GameMode.TWO_PLAYERS)
            paintSnake(g, snakeTwo, UIConstants.BACKGROUND_COLOR_SNAKE_TWO);
        paintPrize(g);
        evaluateIfScored();
    }

    private void paintSnake(Graphics g, Snake snake, Color colo) {
        g.setColor(colo);
        if(snake != null){
            for(Vertabra vertabra : snake.getBody()){
                g.fillRect(vertabra.getX(), vertabra.getY(), GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE);
            }
        }
//        window.repaint(); //Error repaint
    }

    public void run() {
        while (running) {
            this.repaint();
            synchronized (this) {
                System.out.println("paused" + paused);
                if (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("ERROR");
                e.printStackTrace();
            }
        }
    }

    public synchronized void terminate() {
        this.running = false;
        this.notify();
    }

    public synchronized void pause() {
        this.paused = true;
        this.notify();
    }

    public synchronized void resume() {
        this.paused = false;
        this.notify();
    }

    private void paintPrize(Graphics g) {
        g.setColor(new Color(139, 2, 0));
        g.fillOval(prize.getX(), prize.getY(), GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE);
    }

    private void evaluateIfScored(){
        if ((snakeOne.getBody().get(0).getX() == prize.getX()) && (snakeOne.getBody().get(0).getY() == prize.getY())) {
            gameEventListener.addScorePlayerOne();
        }
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            if ((snakeTwo.getBody().get(0).getX() == prize.getX()) && (snakeTwo.getBody().get(0).getY() == prize.getY())) {
                gameEventListener.addScorePlayerTwo();
            }
        }
    }

    public Snake getSnakeOne() {
        return snakeOne;
    }

    public void setSnakeOne(Snake snakeOne) {
        this.snakeOne = snakeOne;
    }

    public Snake getSnakeTwo() {
        return snakeTwo;
    }

    public void setSnakeTwo(Snake snakeTwo) {
        this.snakeTwo = snakeTwo;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}