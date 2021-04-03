package ui;

import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelGameOver extends JPanel {

    private JLabel labelScorePlayer1, labelScorePlayer2;

    public PanelGameOver(ActionListener listener) {
        setLayout(null);
        setBounds(GameConstants.FULL_SCREEN_RECTANGLE);
        setBackground(GameConstants.BACKGROUND_END_GAME);
        initialize(listener);
    }

    public void initialize(ActionListener listener) {
        JButton btnReturnToMenu = UIUtils.buildButton("Main Menu", listener, GameConstants.ACTION_COMMAND_MAIN_MENU);
        btnReturnToMenu.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 280, GameConstants.BUTTON_WIDTH, 40);
        add(btnReturnToMenu);

        JButton btnExit = UIUtils.buildButton("Exit", listener, GameConstants.ACTION_COMMAND_EXIT);
        btnExit.setBounds(GameConstants.X_POSITION_BUTTON_CENTERED, 340, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
        add(btnExit);

        Font fontGameOver = new Font(GameConstants.DEFAULT_FONT_NAME, Font.BOLD, 56);
        JLabel jlblGameOver = new JLabel("!GAME OVER!");
        jlblGameOver.setBounds(GameConstants.WINDOW_WIDTH / 2 - 200, 70, 420, 80);
        jlblGameOver.setFont(fontGameOver);
        jlblGameOver.setForeground(Color.white);
        add(jlblGameOver);

        labelScorePlayer1 = new JLabel();
        labelScorePlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        labelScorePlayer1.setBounds(0, 160, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        labelScorePlayer1.setFont(GameConstants.FONT_SCORE_GAME_OVER);
        labelScorePlayer1.setForeground(GameConstants.COLOR_SCORE_GAME_OVER_LABEL);
        add(labelScorePlayer1);

        labelScorePlayer2 = new JLabel();
        labelScorePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        labelScorePlayer2.setBounds(0, 210, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        labelScorePlayer2.setFont(GameConstants.FONT_SCORE_GAME_OVER);
        labelScorePlayer2.setForeground(GameConstants.COLOR_SCORE_GAME_OVER_LABEL);
        add(labelScorePlayer2);
    }

    public void setScorePlayer1(int score) {
        String prefix = GameController.gameMode == GameMode.ONE_PLAYER ? "Score: " : "Score Player 1: ";
        labelScorePlayer1.setText(prefix + score + " points.");
        labelScorePlayer2.setVisible(GameController.gameMode == GameMode.TWO_PLAYERS);
    }

    public void setScorePlayer2(int scorePlayer1, int scorePlayer2) {
        setScorePlayer1(scorePlayer1);
        labelScorePlayer2.setText("Score Player 2: " + scorePlayer2 + " points.");
    }
}
