package ui.navigation;

import ui.UIConstants;
import ui.board.PanelGame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener, NavigationListener {

    private final PanelMainMenu panelMainMenu;
    private final PanelGame panelGame;

    public GameWindow() {
        this.setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        this.setLayout(null);
        this.getContentPane().setLayout(null);
        this.setTitle("Snake Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        panelMainMenu = new PanelMainMenu(this);
        panelGame = new PanelGame(this, this);
        this.showMainMenu();
        this.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            getContentPane().setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT));
            pack();
            System.out.println(getContentPane().getSize());
        });
    }

    @Override
    public void showMainMenu() {
        this.showComponent(panelMainMenu);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void showComponent(Component component) {
        for (int i = 0; i < getContentPane().getComponents().length; i++) {
            getContentPane().getComponents()[i].setVisible(false);
            getContentPane().remove(i);
            i = 0;
        }
        getContentPane().add(component);
        component.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case UIConstants.ACTION_COMMAND_ONE_PLAYER:
                startGameOnePlayer();
                break;
            case UIConstants.ACTION_COMMAND_TWO_PLAYERS:
                startGameTwoPlayers();
                break;
            case UIConstants.ACTION_COMMAND_EXIT:
                exit();
                break;
        }
    }

    public void startGameOnePlayer() {
        showComponent(panelGame);
        panelGame.startGameModeOnePlayer();
        panelGame.requestFocus();
    }

    public void startGameTwoPlayers() {
        showComponent(panelGame);
        panelGame.startGameModeTwoPlayers();
    }
}