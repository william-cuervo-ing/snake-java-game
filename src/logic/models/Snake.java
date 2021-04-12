package logic.models;

import logic.SnakeDirectionEnum;
import logic.GameConstants;
import logic.controllers.GameEventListener;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Thread {

    /**
     * The Snake direction
     */
    private SnakeDirectionEnum direction;
    /**
     * Describes if the Snake is alive or not
     */
    private boolean alive;

    /**
     * Defines if the Snake is in pause state
     */
    private boolean paused;

    /**
     * Defines if the Snake is growing
     */
    private boolean growing;

    /**
     * Snake Score
     */
    private int score = 0;
    /**
     * Each Snake Vertebra
     */
    private final List<Point> body;

    /**
     * Last HEAD position
     */
    private Point previousHeadPosition;

    /**
     * Listens if the Snake has die.
     */
    private final GameEventListener listener;

    /**
     * Previouse Snake Direction
     */
    private SnakeDirectionEnum previousDirection;
    /**
     * Number of vertebras that the Snake should grow
     */
    private int vertabrasToGrow = GameConstants.VERTABRAS_TO_GROW;


    public Snake(GameEventListener listener) {
        this.listener = listener;
        this.body = new ArrayList<>();
        this.alive = true;
        this.paused = false;
        this.growing = false;

        int x = GameConstants.INITIAL_SNAKE_VERTABRAS_SIZE * 2;
        int y = (int)(Math.random() * (GameConstants.POSTISIONS_AVAILABLE_PER_ROW - 1));
        direction = SnakeDirectionEnum.RIGHT;
        previousDirection = direction;
        for (int i = 0; i < GameConstants.INITIAL_SNAKE_VERTABRAS_SIZE; i++) {
            body.add(new Point(x, y));
            x--;
        }
    }

    @Override
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

    private void move() {
        moveHead();
        moveBody();
        evaluateIfShouldDie();
    }

    private void moveHead() {
        previousHeadPosition = new Point(body.get(0).getX(), body.get(0).getY());
        switch (direction) {
            case LEFT:
                if (!previousDirection.equals(SnakeDirectionEnum.RIGHT))
                    moveLeft();
                else
                    moveRight();
                break;
            case RIGHT:
                if (!previousDirection.equals(SnakeDirectionEnum.LEFT))
                    moveRight();
                else
                    moveLeft();
                break;
            case UP:
                if (!previousDirection.equals(SnakeDirectionEnum.DOWN))
                    moveUp();
                else
                    moveDown();
                break;
            case DOWN:
                if (!previousDirection.equals(SnakeDirectionEnum.UP))
                    moveDown();
                else
                    moveUp();
                break;
        }
    }

    public void moveLeft() {
        body.get(0).setX(body.get(0).getX() - 1);
        if (body.get(0).getX() < 0)
            body.get(0).setX(GameConstants.POSTISIONS_AVAILABLE_PER_ROW - 1);
        previousDirection = SnakeDirectionEnum.LEFT;
    }

    public void moveRight() {
        body.get(0).setX(body.get(0).getX() + 1);
        if (body.get(0).getX() > GameConstants.POSTISIONS_AVAILABLE_PER_ROW - 1)
            body.get(0).setX(0);
        previousDirection = SnakeDirectionEnum.RIGHT;
    }

    public void moveUp() {
        body.get(0).setY(body.get(0).getY() - 1);
        if (body.get(0).getY() < 0)
            body.get(0).setY(GameConstants.POSTISIONS_AVAILABLE_PER_ROW - 1);
        previousDirection = SnakeDirectionEnum.UP;
    }

    public void moveDown() {
        body.get(0).setY(body.get(0).getY() + 1);
        if (body.get(0).getY() > GameConstants.POSTISIONS_AVAILABLE_PER_ROW - 1)
            body.get(0).setY(0);
        previousDirection = SnakeDirectionEnum.DOWN;
    }

    private void moveBody() {
        int x = previousHeadPosition.getX(), y = previousHeadPosition.getY(), xAux, yAux;
        int vertabrasToMove = growing ? (body.size() - vertabrasToGrow) : body.size();
        for (int i = 1; i < vertabrasToMove; i++) {
            xAux = body.get(i).getX();
            yAux = body.get(i).getY();

            body.get(i).setX(x);
            body.get(i).setY(y);

            x = xAux;
            y = yAux;
        }
        if (growing) {
            vertabrasToGrow--;
            if (vertabrasToGrow == 0) {
                growing = false;
                vertabrasToGrow = GameConstants.VERTABRAS_TO_GROW;
            }
        }
    }

    private void evaluateIfShouldDie() {
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

    public void setDirection(SnakeDirectionEnum direction) {
        this.direction = direction;
    }

    public void grow() {
        Point lastVertabra;
        for (int i = 0; i < vertabrasToGrow; i++) {
            lastVertabra = body.get(body.size() - 1);
            body.add(new Point(lastVertabra.getX(), lastVertabra.getY()));
        }
        growing = true;
    }

    public List<Point> getBody() {
        return body;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}