package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import logic.Prize;
import logic.Snake;
import logic.Vertabra;

public class Window extends JFrame implements KeyListener, ActionListener{

	private Snake snake1;
	private Snake snake2;
	private Prize prize;
	private JPanelEndGame jPanelEndGame;
	private JPanelSpaceGame jPanelSpaceGame;
	private JPanelScore jPanelScoreMenu;
	private JPanelMainMenu jPanelMainMenu;
	public static final int MODE_ONE_PLAYER = 0;
	public static final int MODE_TWO_PLAYERS = 1;
	private int gameMode;
	private JPanelPauseMenu jPanelPauseMenu;
	
	public Window() {
		this.addKeyListener(this);
		this.setLayout(null);
		this.setTitle("Snake Game");
		this.setSize(457, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.createMainMenu();
	}
	
	public void createMainMenu(){
		jPanelMainMenu = new JPanelMainMenu(this);
		jPanelMainMenu.setVisible(true);
		this.add(jPanelMainMenu);

		this.requestFocusInWindow();     //------------------------Pregunta al profesor
		jPanelMainMenu.getJbtn1Player().addActionListener(this);
		jPanelMainMenu.getJbtn2Players().addActionListener(this);
		jPanelMainMenu.getJbtnExit().addActionListener(this);
	}
	
	public void beginGame(){
		if (gameMode == MODE_ONE_PLAYER) {
			snake1 = new Snake();
			jPanelScoreMenu = new JPanelScore(this, MODE_ONE_PLAYER);
			jPanelScoreMenu.setVisible(true);
			this.add(jPanelScoreMenu);

			jPanelSpaceGame = new JPanelSpaceGame(this,snake1);
			snake1.createSnake(jPanelSpaceGame);
			jPanelSpaceGame.setVisible(true);
			
			Thread thread = new Thread(jPanelSpaceGame);
			thread.start();
			snake1.start();
			this.add(jPanelSpaceGame);
			this.createPrize();			
		}
		if (gameMode == MODE_TWO_PLAYERS) {
			snake1 = new Snake();
			snake2 = new Snake();
			
			jPanelScoreMenu = new JPanelScore(this, MODE_TWO_PLAYERS);
			jPanelScoreMenu.setVisible(true);
			this.add(jPanelScoreMenu);

			jPanelSpaceGame = new JPanelSpaceGame(this,snake1,snake2);
			snake1.createSnake(jPanelSpaceGame);
			snake2.createSnake(jPanelSpaceGame);
			jPanelSpaceGame.setVisible(true);
			
			Thread thread = new Thread(jPanelSpaceGame);
			thread.start();
			snake1.start();
			snake2.start();
			this.add(jPanelSpaceGame);
			this.createPrize();			
		}
	}

	
	public void addScoreSnake1(){
		jPanelSpaceGame.setThereIsAPrize(false);
		snake1.setScore(snake1.getScore() + 50);
		jPanelScoreMenu.setScorePlayer1(""+snake1.getScore());
		createPrize();
	}
	
	public void addScoreSnake2(){
		jPanelSpaceGame.setThereIsAPrize(false);
		snake2.setScore(snake2.getScore() + 50);
		jPanelScoreMenu.setScorePlayer2(""+snake2.getScore());
		createPrize();
	}
	
	public void createPrize(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		
		if (gameMode == MODE_ONE_PLAYER) {
			Vertabra[] body = snake1.getBody();
			
			x = (random.nextInt(14) + 1) * Vertabra.SIZE;
			y = (random.nextInt(14) + 1) * Vertabra.SIZE;
			
			for (int i = 0; i < body.length; i++) {

				if ( (x == body[i].getX()) && ( y == body[i].getY())) {
					x = (random.nextInt(14) + 1) * Vertabra.SIZE;
					y = (random.nextInt(14) + 1) * Vertabra.SIZE;
					i = 0;
				}
			}
		}
		else{
			Vertabra[] body = snake1.getBody();
			Vertabra[] body2 = snake2.getBody();
			boolean correctSnake1 = false;
			boolean correctSnake2 = false;
			
			
			x = (random.nextInt(14) + 1) * Vertabra.SIZE;
			y = (random.nextInt(14) + 1) * Vertabra.SIZE;
			
			while(!correctSnake1 || !correctSnake2){
				for (int i = 0; i < body.length; i++) {
					if ( (x == body[i].getX() && ( y == body[i].getY()))) {
						x = (random.nextInt(14) + 1) * Vertabra.SIZE;
						y = (random.nextInt(14) + 1) * Vertabra.SIZE;
						i = 0;
					}
				}
				for (int i = 0; i < body2.length; i++) {
					if ( (x == body2[i].getX() && ( y == body2[i].getY()))) {
						x = (random.nextInt(14) + 1) * Vertabra.SIZE;
						y = (random.nextInt(14) + 1) * Vertabra.SIZE;
						i = 0;
					}
				}
				for (int i = 0; i < body.length; i++) {
					if ( (x == body[i].getX() && ( y == body[i].getY()))) {
						correctSnake1 = false;
						break;
					}
					correctSnake1 = true;
				}
				for (int i = 0; i < body2.length; i++) {
					if ( (x == body2[i].getX() && ( y == body2[i].getY()))) {
						correctSnake2 = false;
						break;
					}
					correctSnake2 = true;
				}
			}
		}

		prize = new Prize(x, y);
		jPanelSpaceGame.createPrize(prize.getX(), prize.getY());
	}
	
	public void gameOver(){
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.getStackTrace();
			}
			switch (gameMode) {
			case MODE_ONE_PLAYER:
				snake1.die();
				jPanelSpaceGame.terminate();
				jPanelSpaceGame.setVisible(false);
				remove(jPanelSpaceGame);
				remove(jPanelScoreMenu);
				
				jPanelEndGame = new JPanelEndGame(this, gameMode);
				jPanelEndGame.setVisible(true);
				jPanelEndGame.modeOnePlayer(snake1.getScore());
				jPanelEndGame.getJbtnExit().addActionListener(this);
				jPanelEndGame.getJbtnMainWindow().addActionListener(this);
				
				this.repaint();
				this.add(jPanelEndGame);
				break;
			case MODE_TWO_PLAYERS:
					snake1.die();
					snake2.die();
					jPanelSpaceGame.terminate();
					jPanelSpaceGame.setVisible(false);
					remove(jPanelSpaceGame);
					remove(jPanelScoreMenu);
					
					jPanelEndGame = new JPanelEndGame(this, gameMode);
					jPanelEndGame.setVisible(true);
					jPanelEndGame.modeTwoPlayers(snake1.getScore(),snake2.getScore());
					jPanelEndGame.getJbtnExit().addActionListener(this);
					jPanelEndGame.getJbtnMainWindow().addActionListener(this);
					
					this.repaint();
					this.add(jPanelEndGame);
					break;
				}
	}

	
	public void keyPressed(KeyEvent e) {
		int key = e.getExtendedKeyCode();
		if (gameMode == MODE_ONE_PLAYER) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				snake1.setDirection(snake1.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake1.setDirection(snake1.RIGHT);
				break;
			case KeyEvent.VK_UP:
				snake1.setDirection(snake1.UP);
				break;
			case KeyEvent.VK_DOWN:
				snake1.setDirection(snake1.DOWN);
				break;
			case KeyEvent.VK_ESCAPE:
				jPanelSpaceGame.stoped();
				jPanelSpaceGame.setVisible(false);
				jPanelScoreMenu.setVisible(false);
				jPanelPauseMenu = new JPanelPauseMenu(this);
				jPanelPauseMenu.getJbtnExit().addActionListener(this);
				jPanelPauseMenu.getJbtnResume().addActionListener(this);
				jPanelPauseMenu.setVisible(true);

				snake1.stopSnake();
				this.add(jPanelPauseMenu);
				break;
			}
		}
		else{
			switch (key) {
			case KeyEvent.VK_LEFT:
				snake1.setDirection(snake1.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake1.setDirection(snake1.RIGHT);
				break;
			case KeyEvent.VK_UP:
				snake1.setDirection(snake1.UP);
				break;
			case KeyEvent.VK_DOWN:
				snake1.setDirection(snake1.DOWN);
				break;
			case KeyEvent.VK_A:
				snake2.setDirection(snake1.LEFT);
				break;
			case KeyEvent.VK_D:
				snake2.setDirection(snake1.RIGHT);
				break;
			case KeyEvent.VK_W:
				snake2.setDirection(snake1.UP);
				break;
			case KeyEvent.VK_S:
				snake2.setDirection(snake1.DOWN);
				break;
			case KeyEvent.VK_ESCAPE:
				jPanelSpaceGame.stoped();
				jPanelSpaceGame.setVisible(false);
				jPanelScoreMenu.setVisible(false);
				jPanelPauseMenu = new JPanelPauseMenu(this);
				jPanelPauseMenu.getJbtnExit().addActionListener(this);
				jPanelPauseMenu.getJbtnResume().addActionListener(this);
				jPanelPauseMenu.setVisible(true);

				snake1.stopSnake();
				snake2.stopSnake();				
				this.add(jPanelPauseMenu);
				break;

			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(JPanelMainMenu.ONE_PLAYER)) {
			this.gameMode = MODE_ONE_PLAYER;
			this.beginGame();
			jPanelMainMenu.setVisible(false);
			this.remove(jPanelMainMenu);
			this.requestFocusInWindow();
		}
		else
			if (e.getActionCommand().equals(JPanelMainMenu.TWO_PLAYERS)) {
				this.gameMode = MODE_TWO_PLAYERS;
				this.beginGame();
				jPanelMainMenu.setVisible(false);
				this.remove(jPanelMainMenu);
				this.requestFocusInWindow();
			}
			else
				if (e.getActionCommand().equals(JPanelMainMenu.EXIT)) {
					System.exit(0);
				}
				else
					if (e.getActionCommand().equals(JPanelEndGame.MAINWINDOW)) {
						jPanelEndGame.setVisible(false);
						remove(jPanelEndGame);
						this.createMainMenu();
						this.repaint();
					}
					else
						if (e.getActionCommand().equals(JPanelPauseMenu.RESUME)) {
							snake1.resumeSnake();
							jPanelSpaceGame.resume();
							jPanelSpaceGame.setVisible(true);
							remove(jPanelPauseMenu);

							if (gameMode == Window.MODE_TWO_PLAYERS) {
								snake2.resumeSnake();								
							}
							jPanelScoreMenu.setVisible(true);
							this.repaint();
							this.requestFocus();
						}
		
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	
	
	
	
	
}