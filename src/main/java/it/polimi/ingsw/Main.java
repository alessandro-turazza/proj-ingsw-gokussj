package it.polimi.ingsw;

import it.polimi.ingsw.client.view.Colors;
import org.json.simple.parser.ParseException;
import jline.console.ConsoleReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        String s = Colors.YELLOW_BACKGROUND + Colors.LIGHT_BLUE + "SFONDO BIANCO" + Colors.COLOR_RESET;
        System.out.println(s);
       // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //Runtime.getRuntime().exec("cls");
        //System.out.print("\033[H\033[2J");
        //ConsoleReader r = new ConsoleReader();
        //r.clearScreen();

        /*try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            System.out.println("eccezione!");
        }*/
    }
}