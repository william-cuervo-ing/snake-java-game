package logic.controller;

import logic.DirectionSnakeEnum;
import logic.models.Snake;

public interface GameEventListener {

    void addScorePlayerOne();

    void addScorePlayerTwo();

    void dieSnake(Snake snake);

    void startGameModeOnePlayer();

    void startGameModeTwoPlayers();

    void setDirectionSnakeOne(DirectionSnakeEnum direction);

    void setDirectionSnakeTwo(DirectionSnakeEnum direction);

    void pause();

    void resume();

    void exit();

    void returnToMainMenu();
}
