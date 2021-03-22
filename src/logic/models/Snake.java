package logic.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import logic.DirectionSnakeEnum;
import logic.GameConstants;
import logic.controller.GameEventListener;
import ui.UIConstants;

public class Snake extends Thread {

    private final List<Vertabra> body;
    private final GameEventListener listener;

    private boolean alive;
    private boolean paused;
    private boolean growing;
    private int score = 0;

    private DirectionSnakeEnum direction;
    private DirectionSnakeEnum lastDirection;
    private int vertabrasToGrow = GameConstants.VERTABRAS_TO_GROW;

    public Snake(GameEventListener listener){
        this.listener = listener;
        this.body = new LinkedList<>();
        this.alive = true;
        this.paused = false;
        this.growing = false;

        Random random = new Random();
        int x = random.nextInt(GameConstants.SCALAR_POSITION_GAME);
        int y = random.nextInt(GameConstants.SCALAR_POSITION_GAME);
        direction = DirectionSnakeEnum.RIGHT;
        lastDirection = DirectionSnakeEnum.RIGHT;
        for (int i = 0; i < body.size(); i++) {
            body.add(new Vertabra((short)x, (short)y, GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE));
            x = x - GameConstants.VERTABRA_SIZE;
        }
    }

    public void run() {
        while (alive) {
            move();
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
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
    }

    public void move() {
        moveHead();
        moveBody();
        evaluateIfShouldDie();
    }

    private void moveHead(){
        switch (direction) {
            case LEFT:
                if (lastDirection.equals(DirectionSnakeEnum.RIGHT))
                    moveLeft();
                else
                    moveRight();
                break;
            case RIGHT:
                if (lastDirection.equals(DirectionSnakeEnum.LEFT))
                    moveRight();
                else
                    moveLeft();
                break;
            case UP:
                if (lastDirection.equals(DirectionSnakeEnum.DOWN))
                    moveUp();
                else
                    moveDown();
                break;
            case DOWN:
                if (lastDirection.equals(DirectionSnakeEnum.UP))
                    moveDown();
                else
                    moveUp();
                break;
        }
    }

    private void moveBody(){
        int x = body.get(0).getX();
        int y =  body.get(0).getY();
        int xAux = 0;
        int yAux = 0;

        int vertabrasToMove = growing ? (body.size() - vertabrasToGrow) : body.size();

        for (int i = 0; i < vertabrasToMove; i++) {
            if (body.size() != (i + 1)) {
                xAux =  body.get(i + 1).getX();
                yAux = body.get(i + 1).getY();
                body.get(i + 1).setX(x);
                body.get(i + 1).setY(y);
            }
            x = xAux;
            y = yAux;
        }
        if(growing){
            vertabrasToGrow--;
            if (vertabrasToGrow == 0) {
                growing = false;
                vertabrasToGrow = GameConstants.VERTABRAS_TO_GROW;
            }
        }
    }

    private void evaluateIfShouldDie(){
        for (int i = 1; i < body.size(); i++) {
            if ((body.get(0).getX() == body.get(i).getX()) && (body.get(0).getY() == body.get(i).getY())) {
                listener.dieSnake(this);
                return;
            }
        }
    }

    public synchronized void die() {
        this.alive = false;
        this.notify();
    }

    public synchronized void pause() {
        this.paused = true;
        this.notify();
    }

    public synchronized void resumeSnake() {
        this.paused = false;
        this.notify();
    }

    public void moveLeft() {
        body.get(0).setX(body.get(0).getX() - GameConstants.VERTABRA_SIZE);
        if (body.get(0).getX() < GameConstants.SNAKE_GAP)
            body.get(0).setX(UIConstants.GAME_BOARD_SIZE - (GameConstants.VERTABRA_SIZE + 1));
        lastDirection = DirectionSnakeEnum.LEFT;
    }

    public void moveRight() {
        body.get(0).setX(body.get(0).getX() + GameConstants.VERTABRA_SIZE);
        if (body.get(0).getX() > UIConstants.GAME_BOARD_SIZE - GameConstants.SNAKE_GAP)
            body.get(0).setX(0);
        lastDirection = DirectionSnakeEnum.RIGHT;
    }

    public void moveUp() {
        body.get(0).setY(body.get(0).getY() - GameConstants.VERTABRA_SIZE);
        if (body.get(0).getY() < GameConstants.SNAKE_GAP)
            body.get(0).setY(UIConstants.SCORE_BOARD_HEIGHT - (GameConstants.VERTABRA_SIZE + 1));
        lastDirection = DirectionSnakeEnum.UP;

    }

    public void moveDown() {
        body.get(0).setY(body.get(0).getY() + GameConstants.VERTABRA_SIZE);
        if (body.get(0).getY() > UIConstants.SCORE_BOARD_HEIGHT - GameConstants.SNAKE_GAP)
            body.get(0).setY(0);
        lastDirection = DirectionSnakeEnum.DOWN;
    }

    public void showPositionVertabras() {
        System.out.println("------------------------------------------");
        for (int i = 0; i < body.size(); i++) {
            System.out.println("x : " + body.get(i).getX() + " y: " + body.get(i).getY() +
                " height: " + body.get(i).getHeight() + " width: " + body.get(i).getWidth());
        }
    }

    public void setDirection(DirectionSnakeEnum direction) {
        this.direction = direction;
    }

    public void grow() {
        Vertabra lastVertabra;
        // TODO: Maybe should use vertabrasToGrow vertabrasToGrow += vertabrasToGrow
        for (int i = 0; i < vertabrasToGrow; i++) {
            lastVertabra = body.get(body.size() - 1);
            body.add(new Vertabra(lastVertabra.getX(), lastVertabra.getY(), GameConstants.VERTABRA_SIZE, GameConstants.VERTABRA_SIZE));
        }
        growing = true;
    }

    public List<Vertabra> getBody() {
        return body;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}