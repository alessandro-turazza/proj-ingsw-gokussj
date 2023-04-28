package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.object_card.Color;

public class Colors {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static char colorChar(Color color){
        if(color == Color.YELLOW)
            return 'Y';
        if(color == Color.WHITE)
            return 'W';
        if(color == Color.PINK)
            return 'P';
        if(color == Color.BLUE)
            return 'B';
        if(color == Color.LIGHT_BLUE)
            return 'L';
        else
            return 'G';
    }

}
