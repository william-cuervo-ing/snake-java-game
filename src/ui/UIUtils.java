package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIUtils {

    public static JButton buildButton(String buttonText, ActionListener listener, String actionCommand){
        Border borderButton = BorderFactory.createLineBorder(Color.white, 1);

        JButton btnReturnToMenu = new JButton(buttonText);
        btnReturnToMenu.setBackground(UIConstants.BACKGROUND_BUTTON_COLOR);
        btnReturnToMenu.setFont(UIConstants.FONT_BUTTON);
        btnReturnToMenu.setForeground(Color.white);
        btnReturnToMenu.setBorder(borderButton);
        btnReturnToMenu.setFocusPainted(false);
        btnReturnToMenu.setActionCommand(actionCommand);
        btnReturnToMenu.addActionListener(listener);
        return btnReturnToMenu;
    }
}
