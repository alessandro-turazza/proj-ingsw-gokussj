package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.object_card.Color;

public class Colors {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String LIGHT_BLUE = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[97m";

    public static final String BLACK_BACKGROUND = "\u001B[100m";
    public static final String RED_BACKGROUND = "\u001B[101m";
    public static final String GREEN_BACKGROUND = "\u001B[102m";
    public static final String YELLOW_BACKGROUND = "\u001B[103m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[105m";
    public static final String CYAN_BACKGROUND = "\u001B[106m";
    public static final String WHITE_BACKGROUND = "\u001B[107m";

    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;97m";


    public static String colorChar(Color color){
        if(color == Color.YELLOW)
            return YELLOW_BACKGROUND + "   " + COLOR_RESET;
        if(color == Color.WHITE)
            return WHITE_BACKGROUND + "   " + COLOR_RESET;
        if(color == Color.PINK)
            return PURPLE_BACKGROUND + "   " + COLOR_RESET;
        if(color == Color.BLUE)
            return BLUE_BACKGROUND + "   " + COLOR_RESET;
        if(color == Color.LIGHT_BLUE)
            return CYAN_BACKGROUND + "   " + COLOR_RESET;
        else
            return GREEN_BACKGROUND + "   " + COLOR_RESET;
    }

}
