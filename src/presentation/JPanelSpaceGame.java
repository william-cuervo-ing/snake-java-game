package presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import logic.Prize;
import logic.Snake;
import logic.Vertabra;

public class JPanelSpaceGame extends JPanel implements Runnable{
	
	private Snake snake = null;
	private Snake snake2 = null;
	private boolean running;
	private int gameMode;
	private boolean stoped;
	private boolean thereIsAPrize;
	private Prize prize;
	private Window window;
	
	public JPanelSpaceGame(Window window,Snake snake) {
		this.thereIsAPrize = false;
		this.gameMode = Window.MODE_ONE_PLAYER;
		this.setLayout(null);
		this.setBounds(0, 40, 451, 451);
		this.setBackground(new Color(56,180,194));
		this.running = true;
		this.stoped = false;
		this.snake = snake;
		this.window = window;
	}
	
	public JPanelSpaceGame(Window window,Snake snake, Snake snake2) {	
		this.gameMode = Window.MODE_TWO_PLAYERS;
		this.thereIsAPrize = false;
		this.setLayout(null);
		this.setBounds(0, 40, 451, 451);
		this.setBackground(new Color(56,180,194));
		this.running = true;
		this.stoped = false;
		this.snake = snake;
		this.snake2 = snake2;
		this.window = window;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if (gameMode == Window.MODE_ONE_PLAYER)
			paintSnake(g);
		else{
			paintSnake(g);
			paintSnake(g, snake2);
		}
		if (thereIsAPrize) {
			paintPrize(g);
		}
	}
	
	public void paintSnake(Graphics g){
		Vertabra[] body = snake.getBody();
		g.setColor(new Color(26,115,0));
		for (int i = 0; i < body.length; i++) {
			g.fillRect(body[i].getX(), body[i].getY(), Vertabra.SIZE, Vertabra.SIZE);
		}
		window.repaint(); //Error repaint
	}
	
	public void paintSnake(Graphics g, Snake snake2){
		g.setColor(new Color(107,0,56));
		Vertabra[] body = snake2.getBody();
		for (int i = 0; i < body.length; i++) {
			g.fillRect(body[i].getX(), body[i].getY(), Vertabra.SIZE, Vertabra.SIZE);
		}
		window.repaint(); //Error repaint
	}

	public void run() {
		while(running){
			this.repaint();
			synchronized (this) {
				if (stoped) {
					System.out.println("Stoped");
					try {
						wait();
					} catch (InterruptedException e) {
						e.getStackTrace();
					}					
				}
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("ERROR");
			}
		}
	}
	
	public synchronized void terminate(){
		this.running = false;
		this.notify();
	}
	
	public synchronized void stoped(){
		this.stoped = true;
		this.notify();
	}

	public synchronized void resume(){
		this.stoped = false;
		this.notify();
	}


	public boolean isThereIsAPrize() {
		return thereIsAPrize;
	}
	public void gameOver(){
		window.gameOver();
	}

	public void setThereIsAPrize(boolean thereIsAPrize) {
		this.thereIsAPrize = thereIsAPrize;
	}
	
	public void createPrize(int x, int y){
		prize = new Prize(x, y);
		thereIsAPrize = true;
	}
	
	public void paintPrize(Graphics g){
		g.setColor(new Color(139,2,0));
		g.fillOval(prize.getX(), prize.getY(), Vertabra.SIZE, Vertabra.SIZE);
		if ( (snake.getBody()[0].getX() == prize.getX()) && (snake.getBody()[0].getY() == prize.getY()) ){
			window.addScoreSnake1();
			snake.grow();
		}
		if (gameMode == Window.MODE_TWO_PLAYERS) {
			if ( (snake2.getBody()[0].getX() == prize.getX()) && (snake2.getBody()[0].getY() == prize.getY()) ){
				window.addScoreSnake2();
				snake2.grow();
			}
		}	
	}
}