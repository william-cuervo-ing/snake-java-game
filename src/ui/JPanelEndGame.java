package ui;

import logic.controllers.GameController;
import logic.controllers.GameMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelEndGame extends JPanel {

    private JLabel labelScorePlayer1, labelScorePlayer2;

    public JPanelEndGame(ActionListener listener) {
        setLayout(null);
        setBounds(UIConstants.FULL_SCREEN_RECTANGLE);
        setBackground(UIConstants.BACKGROUND_MENU_COLOR);
        initialize(listener);
    }

    public void initialize(ActionListener listener) {
        JButton btnReturnToMenu = UIUtils.buildButton("Main Menu", listener, UIConstants.ACTION_COMMAND_MAIN_MENU);
        btnReturnToMenu.setBounds(100, 280, 240, 40);
        add(btnReturnToMenu);

        JButton btnExit = UIUtils.buildButton("Exit", listener, UIConstants.ACTION_COMMAND_EXIT);
        btnExit.setBounds(100, 340, UIConstants.BUTTON_WIDTH, UIConstants.BUTTON_HEIGHT);
        add(btnExit);

        Font fontGameOver = new Font(UIConstants.DEFAULT_FONT_NAME, Font.BOLD, 56);
        JLabel jlblGameOver = new JLabel("!GAME OVER!");
        jlblGameOver.setBounds(40, 70, 420, 80);
        jlblGameOver.setFont(fontGameOver);
        jlblGameOver.setForeground(Color.white);
        add(jlblGameOver);

        labelScorePlayer1 = new JLabel();
        labelScorePlayer1.setBounds(0, 160, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        labelScorePlayer1.setFont(UIConstants.FONT_SCORE_GAME_OVER);
        labelScorePlayer1.setForeground(UIConstants.COLOR_SCORE_GAME_OVER_LABEL);
        add(labelScorePlayer1);

        labelScorePlayer2 = new JLabel();
        labelScorePlayer2.setBounds(0, 210, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        labelScorePlayer2.setFont(UIConstants.FONT_SCORE_GAME_OVER);
        labelScorePlayer2.setForeground(UIConstants.COLOR_SCORE_GAME_OVER_LABEL);
        add(labelScorePlayer2);
    }

    public void setScorePlayer1(int score) {
        String prefix = GameController.gameMode == GameMode.ONE_PLAYER ? "Score: " : "Score Player 1: ";
        labelScorePlayer1.setText(prefix + score);
        labelScorePlayer2.setVisible(GameController.gameMode == GameMode.ONE_PLAYER);
    }

    public void setScorePlayer2(int scorePlayer1, int scorePlayer2) {
        this.setScorePlayer1(scorePlayer1);
        labelScorePlayer2.setText("Score Player 2: " + scorePlayer2);
    }
}
