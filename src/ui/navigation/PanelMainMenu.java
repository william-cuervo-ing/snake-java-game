package ui.navigation;

import logic.GameConstants;
import ui.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelMainMenu extends JPanel {

    private final ActionListener listener;

    public PanelMainMenu(ActionListener listener) {
        setName(this.getClass().getName());
        this.listener = listener;
        setLayout(null);
        setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
        setBackground(GameConstants.BACKGROUND_MENU_COLOR);
        initialize();
    }

    private void initialize() {
        addHelpInfo();
        addButtons();
    }

    private void addHelpInfo() {
        addLabel("Player 1: ", new Rectangle(20, 20, 100, 20));
        String controlsText = "Move up:   UP\n";
        controlsText += "Move down:   DOWN\n";
        controlsText += "Move left:   LEFT\n";
        controlsText += "Move right:   RIGHT";
        addControlInstruction(controlsText, new Rectangle(80, 20, 150, 80));

        addLabel("Player 2: ", new Rectangle(280, 20, 100, 20));
        controlsText = "Move up:   W\n";
        controlsText += "Move Down:   A\n";
        controlsText += "Move Left:   S\n";
        controlsText += "Move Right:   D";
        addControlInstruction(controlsText, new Rectangle(340, 20, 150, 80));

        addLabel("Pause:    Esc", new Rectangle(20, 100, 100, 20));
        addLabel("Developed by William Cuervo", new Rectangle(0, 470, GameConstants.WINDOW_WIDTH, 20), true);
    }

    private void addLabel(String text, Rectangle bounds) {
        addLabel(text, bounds, false);
    }

    private void addLabel(String text, Rectangle bounds, boolean centerLabel) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        if(centerLabel){
            label.setHorizontalAlignment(SwingUtilities.CENTER);
        }
        addComponent(label, bounds);
    }

    private void addControlInstruction(String text, Rectangle bounds) {
        JTextArea label = new JTextArea();
        label.setText(text);
        label.setBackground(null);
        label.setEditable(false);
        label.setForeground(Color.WHITE);
        addComponent(label, bounds);
    }

    private void addButtons() {
        JButton btnOnePlayer = UIUtils.buildButton("1 Player", listener, GameConstants.ACTION_COMMAND_ONE_PLAYER);
        addComponent(btnOnePlayer, new Rectangle(GameConstants.X_POSITION_BUTTON_CENTERED, 180, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT));

        JButton btnTwoPlayers = UIUtils.buildButton("2 Players", listener, GameConstants.ACTION_COMMAND_TWO_PLAYERS);
        addComponent(btnTwoPlayers, new Rectangle(GameConstants.X_POSITION_BUTTON_CENTERED, 280, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT));

        JButton btnExit = UIUtils.buildButton("Exit", listener, GameConstants.ACTION_COMMAND_EXIT);
        addComponent(btnExit, new Rectangle(GameConstants.X_POSITION_BUTTON_CENTERED, 380, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT));
    }

    private void addComponent(JComponent component, Rectangle bounds) {
        component.setBounds(bounds);
        add(component);
    }
}
