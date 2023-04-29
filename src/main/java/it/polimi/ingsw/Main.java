package it.polimi.ingsw;

import it.polimi.ingsw.client.view.Colors;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        String s = Colors.YELLOW_BACKGROUND + Colors.LIGHT_BLUE + "SFONDO BIANCO" + Colors.COLOR_RESET;
        System.out.println(s);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}