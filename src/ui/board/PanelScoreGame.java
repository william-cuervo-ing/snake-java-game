package ui.board;

import logic.GameConstants;
import logic.controllers.GameController;
import logic.controllers.GameMode;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

/**
 * Panel with Game Score in Game Panel
 */
public class PanelScoreGame extends JPanel {

    private final Font font = new Font(GameConstants.DEFAULT_FONT_NAME, Font.BOLD, 18);
    private JLabel scorePlayer1, scorePlayer2;

    public PanelScoreGame() {
        this.setLayout(null);
        this.setBounds(-1, -1, GameConstants.WINDOW_WIDTH, GameConstants.SCORE_BOARD_HEIGHT);
        this.setBackground(GameConstants.BACKGROUND_SCORE_COLOR);
        Border border = BorderFactory.createLineBorder(GameConstants.BACKGROUND_SCORE_COLOR.darker(), 1);
        this.setBorder(border);
        setupScoreLabels();
    }

    public void setupScoreLabels() {
        addLabel(scorePlayer1 = new JLabel());
        addLabel(scorePlayer2 = new JLabel());
    }

    private void addLabel(JLabel label) {
        label.setForeground(Color.white);
        label.setSize(250, 45);
        label.setFont(font);
        add(label);
    }

    public void setScorePlayer1(int score) {
        if (GameController.gameMode == GameMode.ONE_PLAYER) {
            scorePlayer2.setVisible(false);
            scorePlayer1.setText("Score: " + score);
            scorePlayer1.setLocation(210, 0);
        } else {
            scorePlayer1.setText("Player One: " + score);
            scorePlayer1.setLocation(40, 0);
        }
    }

    public void setScorePlayer2(int score) {
        if (GameController.gameMode == GameMode.TWO_PLAYERS) {
            scorePlayer2.setText("Player Two: " + score);
            scorePlayer2.setLocation(300, 0);
            scorePlayer2.setVisible(true);
        }
    }

}
