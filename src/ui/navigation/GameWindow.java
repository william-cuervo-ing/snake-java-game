package ui.navigation;

import logic.GameConstants;
import ui.board.PanelGame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener {

    private final PanelMainMenu panelMainMenu;
    private final PanelGame panelGame;

    public GameWindow() {
        setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        setLayout(null);
        getContentPane().setLayout(null);
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        panelMainMenu = new PanelMainMenu(this);
        panelGame = new PanelGame(this);
        showMainMenu();
        setVisible(true);
        packWindow();
    }

    public void showMainMenu() {
        showComponent(panelMainMenu);
    }

    /**
     * Force the window to shows the full Size of the contentPane Panel
     */
    private void packWindow(){
        SwingUtilities.invokeLater(() -> {
            getContentPane().setPreferredSize(new Dimension(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT));
            pack();
        });
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case GameConstants.ACTION_COMMAND_ONE_PLAYER:
                startGameOnePlayer();
                break;
            case GameConstants.ACTION_COMMAND_TWO_PLAYERS:
                startGameTwoPlayers();
                break;
            case GameConstants.ACTION_COMMAND_EXIT:
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
        panelGame.requestFocus();
    }

    /**
     * Show a component in the view. It hides every panel and make visible only the component passed
     * @param component component to view
     */
    private void showComponent(Component component) {
        for (int i = 0; i < getContentPane().getComponents().length; i++) {
            getContentPane().getComponents()[i].setVisible(false);
            getContentPane().remove(i);
            i = 0;
        }
        getContentPane().add(component);
        component.setVisible(true);
    }

    public void exit() {
        System.exit(0);
    }
}