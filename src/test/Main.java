package test;

import logic.Snake;
import presentation.JPanelMainMenu;
import presentation.JPanelSpaceGame;
import presentation.Window;


public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		window.setVisible(true);
	}
	
	public static void testMainMenu(){
		Window window = new Window();
		window.setVisible(true);
		JPanelMainMenu jPanelMainMenu = new JPanelMainMenu(window);
		jPanelMainMenu.setVisible(true);
		window.add(jPanelMainMenu);
	}
	
	public static void testGrow(){
		Snake snake = new Snake();
		Window window = new Window();
		JPanelSpaceGame jPanelSpaceGame = new JPanelSpaceGame(window, snake);
		snake.createSnake(jPanelSpaceGame);
		snake.positionsVertabras();
		snake.grow();
		snake.positionsVertabras();
	}
}
