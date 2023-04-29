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
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";


    public static String colorChar(Color color){
        if(color == Color.YELLOW)
            return YELLOW + "Y" + COLOR_RESET;
        if(color == Color.WHITE)
            return WHITE + "W" + COLOR_RESET;
        if(color == Color.PINK)
            return PURPLE + "P" + COLOR_RESET;
        if(color == Color.BLUE)
            return BLUE + "B" + COLOR_RESET;
        if(color == Color.LIGHT_BLUE)
            return LIGHT_BLUE + "L" + COLOR_RESET;
        else
            return GREEN + "G" + COLOR_RESET;
    }

}
