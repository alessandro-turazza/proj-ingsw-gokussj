package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.server.Server;

import java.util.Scanner;

public class RunApp {
    public static void main(String[] args) throws Exception {
        System.out.println("----------------------------");
        System.out.println(Colors.WHITE_BOLD + "Welcome to MyShelfie" + Colors.COLOR_RESET);
        System.out.println("Premi C per essere client");
        System.out.println("Premi S per essere server");

        Scanner in = new Scanner(System.in);

        String choose = in.nextLine();

        while(!choose.equalsIgnoreCase("C") && !choose.equalsIgnoreCase("S")){
            System.out.println(Colors.RED + "Errore, carattere invalido" + Colors.COLOR_RESET);
            System.out.println("Premi C per essere client");
            System.out.println("Premi S per essere server");
            choose = in.nextLine();
        }

        char chooseChar = Character.toUpperCase(choose.charAt(0));

        switch (chooseChar){
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
