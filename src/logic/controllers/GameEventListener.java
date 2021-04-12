package logic.controllers;

import logic.SnakeDirectionEnum;
import logic.models.Snake;

/**
 * Represents the events that GameController must handle
 */
public interface GameEventListener {

    void addScorePlayerOne();

    void addScorePlayerTwo();

    void dieSnake(Snake snake);

    void setDirectionSnakeOne(SnakeDirectionEnum direction);

    void setDirectionSnakeTwo(SnakeDirectionEnum direction);
}
