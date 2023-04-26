package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.Server;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class RunApp {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Welcome to MyShelfie");
        System.out.println("Premi C per essere client");
        System.out.println("Premi S per essere server");

        Scanner in = new Scanner(System.in);

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        while(choose != 'C' && choose != 'S'){
            System.out.println("Carattere invalido, reinserisci la tua scelta");
            choose = Character.toUpperCase(in.nextLine().charAt(0));
        }

        switch (choose){
            case 'S':
                Server server = new Server();
                server.startServer();
                break;
            case 'C':
                Client client = new Client();
                client.startClient();
                break;
        }
    }
}
