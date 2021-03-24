package ui.board;

import logic.controllers.GameController;
import ui.UIConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Panel with Game Score in Game Panel
 */
public class PanelScoreGame extends JPanel {

    private final Font font = new Font(UIConstants.DEFAULT_FONT_NAME, Font.BOLD, 18);
    private JLabel labelScorePlayer1;
    private JLabel labelScorePlayer2;

    public PanelScoreGame() {
        this.setLayout(null);
        this.setBounds(-1, -1, UIConstants.WINDOW_WIDTH, UIConstants.SCORE_BOARD_HEIGHT);
        this.setBackground(UIConstants.BACKGROUND_SCORE_COLOR);
        Border border = BorderFactory.createLineBorder(UIConstants.BACKGROUND_SCORE_COLOR.darker(), 1);
        this.setBorder(border);
        this.initialize();
    }

    public void initialize() {
        switch (GameController.gameMode) {
            case ONE_PLAYER:
                this.initializeOnePlayerMode();
                break;
            case TWO_PLAYERS:
                this.initializeTwoPlayersMode();
                break;
        }
    }

    private void initializeOnePlayerMode(){
        JLabel jLabelPlayer1 = new JLabel("Score: ");
        jLabelPlayer1.setBounds(180, 0, 70, 45);
        jLabelPlayer1.setFont(font);
        jLabelPlayer1.setForeground(new Color(242, 242, 242));
        this.add(jLabelPlayer1);

        labelScorePlayer1 = new JLabel("0");
        labelScorePlayer1.setBounds(240, 0, 100, 45);
        labelScorePlayer1.setFont(font);
        labelScorePlayer1.setForeground(new Color(242, 242, 242));
        this.add(labelScorePlayer1);
    }

    private void initializeTwoPlayersMode(){
        JLabel labelPlayer1 = new JLabel("Player 1: ");
        labelPlayer1.setBounds(40, 0, 100, 45);
        labelPlayer1.setFont(font);
        labelPlayer1.setForeground(new Color(242, 242, 242));
        this.add(labelPlayer1);

        labelScorePlayer1 = new JLabel("0");
        labelScorePlayer1.setBounds(140, 0, 100, 45);
        labelScorePlayer1.setFont(font);
        labelScorePlayer1.setForeground(new Color(242, 242, 242));
        this.add(labelScorePlayer1);

        JLabel jlabelPlayer2 = new JLabel("Player 2: ");
        jlabelPlayer2.setBounds(280, 0, 100, 45);
        jlabelPlayer2.setFont(font);
        jlabelPlayer2.setForeground(new Color(242, 242, 242));
        this.add(jlabelPlayer2);

        labelScorePlayer2 = new JLabel("0");
        labelScorePlayer2.setBounds(380, 0, 100, 45);
        labelScorePlayer2.setFont(font);
        labelScorePlayer2.setForeground(new Color(242, 242, 242));
        this.add(labelScorePlayer2);
    }

    public void setScorePlayer1(int score) {
        labelScorePlayer1.setText(score + "");
    }

    public void setScorePlayer2(int score) {
        labelScorePlayer2.setText(score + "");
    }

}
