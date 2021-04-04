# Multi-Player Snake Java Game

A Multiplayer Snake Game built with Java. Two players on the same machine can play simultaneously.
It's the classic Snake Game, built in Java SE 8, using MVC Pattern, Threads, and AWT and Swing for User Interfaces.

## Run Game

Type the next line in your console, or just doble-click to the _out/snake-game.jar_ file.

```bash
java -jar out/snake-game.jar
```

## How is it build?

It's built using MVC pattern, where a _GameController_ handles the Game logic, and some _UI Components_ handle the User Interface.

The Project structure is:

```
/ logic
  / controllers
    GameController
    GameEventListener
  / models
    Point
    Snake
  GameMode
  SnakeDirectionEnum
  GameConstants
- ui
  / board
    PanelGame
    PanelGameBoard
    PanelGameOver
    PanelPauseMenu
    PanelScoreGame
  / navigation
    GameWindow
    PanelMainMenu
  UIUtils
/ main
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)