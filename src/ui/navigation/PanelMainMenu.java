package ui.navigation;

import ui.UIConstants;
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
        setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        setBackground(UIConstants.BACKGROUND_MENU_COLOR);
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

        addLabel("Player 2: ", new Rectangle(240, 20, 100, 20));
        controlsText = "Move up:   W\n";
        controlsText += "Move Down:   A\n";
        controlsText += "Move Left:   S\n";
        controlsText += "Move Right:   D";
        addControlInstruction(controlsText, new Rectangle(300, 20, 150, 80));

        addLabel("Pause:    Esc", new Rectangle(20, 100, 100, 20));
        addLabel("Developed by William Cuervo", new Rectangle(UIConstants.WINDOW_HEIGHT - 60, 470, 140, 20));
    }

    private void addLabel(String text, Rectangle bounds) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
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
        JButton btnOnePlayer = UIUtils.buildButton("1 Player", listener, UIConstants.ACTION_COMMAND_ONE_PLAYER);
        addComponent(btnOnePlayer, new Rectangle(100, 150, 240, 40));

        JButton btnTwoPlayers = UIUtils.buildButton("2 Players", listener, UIConstants.ACTION_COMMAND_TWO_PLAYERS);
        addComponent(btnTwoPlayers, new Rectangle(100, 250, 240, 40));

        JButton btnExit = UIUtils.buildButton("Exit", listener, UIConstants.ACTION_COMMAND_TWO_PLAYERS);
        addComponent(btnExit, new Rectangle(100, 350, 240, 40));
    }

    private void addComponent(JComponent component, Rectangle bounds) {
        component.setBounds(bounds);
        add(component);
    }
}
