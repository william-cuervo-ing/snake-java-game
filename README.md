# Multi-Player Snake Java Game!

Multiplayer Snake Game built in **Java SE 8**.

The Classic Snake Game, but... **Multiplayer!**. 2 players can play simultaneously in the same PC.  Also, it lets to play individually.

## Preview

- Main Menu
  ![Main Menu screenshoot](https://github.com/william-cuervo-ing/snake-java-game/blob/main/screenshoots/main_menu.png?raw=true)
- One Player Mode
  ![One player mode screenshoot](https://github.com/william-cuervo-ing/snake-java-game/blob/main/screenshoots/1_player_mode.png?raw=true)
- Two Players mode
  ![Two players mode screenshoot](https://github.com/william-cuervo-ing/snake-java-game/blob/main/screenshoots/2_players_mode.png?raw=true)

## Built Using

Every line uses **Java SE 8** and the project expose mainly the next aspects:
- MVC Pattern.
- Threads.
- AWT and Swing

## Folder Structure

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
/ ui
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

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)