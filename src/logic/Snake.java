package logic;

import java.util.Random;
import presentation.JPanelSpaceGame;

public class Snake extends Thread{
	
	private boolean alive;
	private boolean stoped;
	private boolean isGrowing;
	private Vertabra[] body;
	private final int FIRST_LONG = 5;
	public final int LEFT = 0;
	public final int RIGHT = 1;
	public final int UP = 2;
	public final int DOWN = 3;
	private int direction;
	private int lastDirection; //Variable usada para estipular la última dirección de la serpiente
	private int score = 0;
	private final int VERTABRAS_TO_GROW = 2;
	private int auxVertabrasGrowed = VERTABRAS_TO_GROW;
	private JPanelSpaceGame jPanelSpaceGame;
	
	public void run(){
		while(alive){
			movement();
			synchronized (this) {
				if (stoped) {
					try {
						wait();				
					} catch (InterruptedException e) {
						e.getStackTrace();
					}					
				}
			}
			try {
				Thread.sleep(150);				
			} catch (InterruptedException e) {
				e.getStackTrace();
			}
		}
	}
	
	public Snake() {
		this.body = new Vertabra[FIRST_LONG];
		this.alive = true;
		this.stoped = false;
		this.isGrowing = false;
	}
	
	public synchronized void die(){
		this.alive = false;
		this.notify();
	}
	
	public synchronized void stopSnake(){
		this.stoped = true;
		this.notify();
	}
	
	public synchronized void resumeSnake(){
		this.stoped = false;
		this.notify();
	}
	
	public void createSnake(JPanelSpaceGame jPanelSpaceGame){
		this.jPanelSpaceGame = jPanelSpaceGame;
		Random random = new Random();
		int x = random.nextInt(15) * Vertabra.SIZE;
		int y = random.nextInt(15) * Vertabra.SIZE;
		direction = RIGHT;

		switch (direction) {
		case LEFT:
			for (int i = 0; i < body.length; i++) {
				body[i] = new Vertabra(x, y, Vertabra.SIZE, Vertabra.SIZE);
				x = x + Vertabra.SIZE;
				lastDirection = LEFT;
			}
			break;
		case RIGHT:
			for (int i = 0; i < body.length; i++) {
				body[i] = new Vertabra(x, y, Vertabra.SIZE, Vertabra.SIZE);
				x = x - Vertabra.SIZE;
				lastDirection = RIGHT;
			}
			break;
		case UP:
			for (int i = 0; i < body.length; i++) {
				body[i] = new Vertabra(x, y, Vertabra.SIZE, Vertabra.SIZE);
				y = y + Vertabra.SIZE;
				lastDirection = UP;
			}
			break;
		case DOWN:
			for (int i = 0; i < body.length; i++) {
				body[i] = new Vertabra(x, y, Vertabra.SIZE, Vertabra.SIZE);
				y = y - Vertabra.SIZE;
			}
			lastDirection = DOWN;
			break;
		}
	}			
	
	public void movement(){
		int x = body[0].getX();
		int y = body[0].getY();
		int xAux = 0;
		int yAux = 0;
		
		switch (direction) {
		case LEFT:
			if (lastDirection != RIGHT)
				moveLeft(x);
			else
				moveRight(x);
			break;
		case RIGHT:
			if (lastDirection != LEFT)
				moveRight(x);
			else
				moveLeft(x);
			break;
		case UP:
			if (lastDirection != DOWN)
				moveUp(y);
			else
				moveDown(y);
			break;
		case DOWN:
			if (lastDirection != UP)
				moveDown(y);
			else
				moveUp(y);
			break;
		}
		if (!isGrowing) {
			for (int i = 0; i < body.length; i++) {
				if (body.length != (i + 1)) {
					xAux = body[i + 1].getX();
					yAux = body[i + 1].getY();
					body[i + 1].setX(x);
					body[i + 1].setY(y);
				}
				x = xAux;
				y = yAux;
			}			
		}
		else{
			for (int i = 0; i < body.length - auxVertabrasGrowed; i++) {
				if (body.length != (i + 1)) {
					xAux = body[i + 1].getX();
					yAux = body[i + 1].getY();
					body[i + 1].setX(x);
					body[i + 1].setY(y);
				}
				x = xAux;
				y = yAux;
			}
			auxVertabrasGrowed--;
			if (auxVertabrasGrowed == 0) {
				isGrowing = false;
				auxVertabrasGrowed = VERTABRAS_TO_GROW;
			}
		}
		for (int i = 1; i < body.length; i++) {
			if ((body[0].getX() == body[i].getX()) && (body[0].getY() == body[i].getY())) {
				jPanelSpaceGame.gameOver();
			}
		}
	}
	
	public void moveLeft(int x){
		body[0].setX(x - Vertabra.SIZE);		
		if (x < 30)
			body[0].setX(jPanelSpaceGame.getWidth() - (Vertabra.SIZE + 1));
		lastDirection = LEFT;
	}
	
	public void moveRight(int x){
		body[0].setX(x + Vertabra.SIZE);
		if (x > jPanelSpaceGame.getWidth() - 40) 
			body[0].setX(0);
		lastDirection = RIGHT;
	}
	
	public void moveUp(int y){
		body[0].setY(y - Vertabra.SIZE);
		if (y < 30)
			body[0].setY(jPanelSpaceGame.getHeight() - (Vertabra.SIZE + 1));
		lastDirection = UP;

	}
	
	public void moveDown(int y){
		body[0].setY(y + Vertabra.SIZE);
		if (y > jPanelSpaceGame.getHeight() - 40)
			body[0].setY(0);
		lastDirection = DOWN;
	}
	
	public void positionsVertabras(){
		System.out.println("------------------------------------------");
		for (int i = 0; i < body.length; i++) {
			System.out.println("x : " + body[i].getX() + " y: " + body[i].getY() + 
					" height: " + body[i].getHeight() + " width: " + body[i].getWidth());
		}
	}

	public Vertabra[] getBody() {
		return body;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void grow(){
		Vertabra[] bodyAux = new Vertabra[body.length + VERTABRAS_TO_GROW];
		for (int i = 0; i < body.length; i++) {
			bodyAux[i] = body[i];
		}
		for (int i = body.length - 1; i < bodyAux.length; i++) {
			bodyAux[i] = new Vertabra(body[body.length - 1].getX(), body[body.length - 1].getY(), Vertabra.SIZE, Vertabra.SIZE);
		}
		body = bodyAux;
		isGrowing = true;
	}
}