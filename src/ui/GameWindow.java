package ui;

import logic.DirectionSnakeEnum;
import logic.controllers.GameController;
import logic.controllers.GameMode;
import logic.controllers.NavigationController;
import logic.models.Prize;
import logic.models.Snake;
import ui.board.JPanelPauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener, ActionListener {

    private JPanelEndGame panelEndGame;
    private JPanelMainMenu panelMainMenu;
    private JPanelPauseMenu panelPauseMenu;

    private final NavigationController navigationController;

    public GameWindow(NavigationController controller) {
        this.navigationController = controller;
        this.addKeyListener(this);
        this.setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        this.setLayout(null);
        this.getContentPane().setLayout(null);
        this.setTitle("Snake Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createScreens();
        this.setVisible(true);
        this.requestFocusInWindow();
    }

    private void createScreens() {
        panelMainMenu = new JPanelMainMenu(this);
        panelPauseMenu = new JPanelPauseMenu(this);
        panelEndGame = new JPanelEndGame(this);
    }

    public void showMainMenu() {
        this.showComponent(panelMainMenu);
    }


    private void showComponent(Component component) {
        for (int i = 0; i < getContentPane().getComponents().length; i++) {
            getContentPane().getComponents()[i].setVisible(false);
            getContentPane().remove(i);
            i = 0;
        }
        getContentPane().add(component);
        component.setVisible(true);
        System.out.println("Finaliza showComponent"  + getContentPane().getComponents().length);
    }

    public void setSnakes(Snake snakeOne, Snake snakeTwo) {
        panelSpaceGame.setSnakeOne(snakeOne);
        panelSpaceGame.setSnakeTwo(snakeTwo);
    }

    public void setPrize(Prize prize) {
        panelSpaceGame.setPrize(prize);
    }
}