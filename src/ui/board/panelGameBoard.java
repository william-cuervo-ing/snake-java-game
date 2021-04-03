package ui.board;

import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameEventListener;
import logic.controllers.GameMode;
import logic.models.Point;
import logic.models.Snake;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class panelGameBoard extends JPanel implements Runnable {

    private final GameEventListener gameEventListener;
    private Snake snakeOne;
    private Snake snakeTwo;
    private boolean running;
    private boolean paused;
    private Point prize;

    public panelGameBoard(GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
        this.setLayout(null);
        this.setBounds(0, GameConstants.SCORE_BOARD_HEIGHT, GameConstants.GAME_BOARD_SIZE, GameConstants.GAME_BOARD_SIZE);
        this.setBackground(GameConstants.BACKGROUND_GAME_BOARD_COLOR);
        this.running = true;
        this.paused = false;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintSnake(g2, snakeOne, GameConstants.BACKGROUND_COLOR_SNAKE_ONE);
        if (GameController.gameMode == GameMode.TWO_PLAYERS)
            paintSnake(g2, snakeTwo, GameConstants.BACKGROUND_COLOR_SNAKE_TWO);
        paintPrize(g2);
        evaluateIfScored();
    }

    private void paintSnake(Graphics2D g, Snake snake, Color color) {
        int innerWidth = 1;
        if (snake != null) {
            boolean isHead = true;
            for (Point vertabra : snake.getBody()) {
                g.setColor(color);
                g.fillRect(vertabra.getX() * GameConstants.VERTABRA_SIZE, vertabra.getY() * GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE);
                if(isHead){
                    isHead = false;
                } else {
                    g.setColor(color.brighter());
                    g.fillRect(vertabra.getX() * GameConstants.VERTABRA_SIZE + innerWidth, vertabra.getY() * GameConstants.VERTABRA_SIZE + innerWidth, GameConstants.VERTABRA_SIZE - innerWidth * 2, GameConstants.VERTABRA_SIZE - innerWidth * 2);
                }
            }
        }
    }

    public void run() {
        this.running = true;
        while (running) {
            this.repaint();
            synchronized (this) {
                if (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(GameConstants.DELAY_SNAKE_MOVE_TIME);
            } catch (Exception e) {
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

    private void paintPrize(Graphics2D g) {
        g.setColor(GameConstants.BACKGROUND_PRIZE_COLOR);
        g.fillOval(prize.getX() * GameConstants.VERTABRA_SIZE, prize.getY() * GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE);
    }

    private void evaluateIfScored() {
        if ((snakeOne.getBody().get(0).getX() == prize.getX()) && (snakeOne.getBody().get(0).getY() == prize.getY())) {
            gameEventListener.addScorePlayerOne();
        }
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            if ((snakeTwo.getBody().get(0).getX() == prize.getX()) && (snakeTwo.getBody().get(0).getY() == prize.getY())) {
                gameEventListener.addScorePlayerTwo();
            }
        }
    }

    public void setSnakeOne(Snake snakeOne) {
        this.snakeOne = snakeOne;
    }


    public void setSnakeTwo(Snake snakeTwo) {
        this.snakeTwo = snakeTwo;
    }

    public void setPrize(Point prize) {
        this.prize = prize;
    }
}