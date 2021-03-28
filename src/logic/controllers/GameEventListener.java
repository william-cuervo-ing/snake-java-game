package logic.controllers;

import logic.DirectionSnakeEnum;
import logic.models.Snake;

public interface GameEventListener {

    void addScorePlayerOne();

    void addScorePlayerTwo();

    void dieSnake(Snake snake);

    void setDirectionSnakeOne(DirectionSnakeEnum direction);

    void setDirectionSnakeTwo(DirectionSnakeEnum direction);
}
