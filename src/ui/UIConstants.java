package ui;

import java.awt.*;

public class UIConstants {

    // Fonts
    public static final String DEFAULT_FONT_NAME = "Helvetica";
    public static final Font FONT_SCORE_GAME_OVER = new Font(UIConstants.DEFAULT_FONT_NAME, Font.BOLD, 34);
    public static final Font FONT_BUTTON = new Font(UIConstants.DEFAULT_FONT_NAME, Font.BOLD, 20);

    // Colors
    public static final Color BACKGROUND_SCORE_COLOR = new Color(237, 118, 0);
    public static final Color BACKGROUND_MENU_COLOR = new Color(27,173,204);
    public static final Color BACKGROUND_GAME_BOARD_COLOR = new Color(56,180,194);
    public static final Color BACKGROUND_BUTTON_COLOR = new Color(47,101,117);

    public static final Color BACKGROUND_COLOR_SNAKE_ONE = new Color(26, 115, 0);
    public static final Color BACKGROUND_COLOR_SNAKE_TWO = new Color(107, 0, 56);



    public static final Color COLOR_SCORE_GAME_OVER_LABEL = new Color(205,233,241);

    // Dimensions
    public static final short SCORE_BOARD_HEIGHT = 40;
    public static final short WINDOW_WIDTH = 460;
    public static final short WINDOW_HEIGHT = WINDOW_WIDTH + SCORE_BOARD_HEIGHT;

    public static final short GAME_BOARD_SIZE = WINDOW_WIDTH;

    public static final short BUTTON_WIDTH = 240;
    public static final short BUTTON_HEIGHT = 40;

    public static final Rectangle FULL_SCREEN_RECTANGLE = new Rectangle(0, 0, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);


    // ACTION COMMANDS
    public static final String ACTION_COMMAND_MAIN_MENU = "ACTION_COMMAND_MAIN_MENU";
    public static final String ACTION_COMMAND_RESUME_GAME = "ACTION_COMMAND_RESUME_GAME";

    public static final String ACTION_COMMAND_ONE_PLAYER = "ACTION_COMMAND_ONE_PLAYER";
    public static final String ACTION_COMMAND_TWO_PLAYERS = "ACTION_COMMAND_TWO_PLAYERS";
    public static final String ACTION_COMMAND_EXIT = "ACTION_COMMAND_EXIT";
}
