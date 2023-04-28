package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements View{

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;


    @Override
    public JSONObject lobby() {
        JSONObject userDatas = new JSONObject();

        System.out.println("----------------------------");

        System.out.println("Inserisci il nome utente");
        Scanner in = new Scanner(System.in);

        userDatas.put("username", in.nextLine());

        System.out.println("Premi C per creare una nuova partita");
        System.out.println("Premi J per unirti ad una partita esistente");

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        while(choose != 'C' && choose != 'J'){
            System.out.println(Colors.RED + "Carattere invalido, reinserisci la scelta" + Colors.COLOR_RESET);
            choose = Character.toUpperCase(in.nextLine().charAt(0));
        }

        switch (choose){
            case 'C':

                userDatas.put("type", "create");
                int nPlayers;

                do{
                    System.out.println("Inerisci il numero dei giocatori (da 2 a 4)");
                    nPlayers = in.nextInt();

                }while(nPlayers < MIN_PLAYERS || nPlayers > MAX_PLAYERS);

                userDatas.put("numPlayers", nPlayers);
                break;
            case 'J':
                System.out.println("Inserisci l'ID della partita");
                userDatas.put("type", "join");
                userDatas.put("idGame", in.nextInt());
                break;
        }

        System.out.println("----------------------------");

        return userDatas;
    }

    @Override
    public void showNormalMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showCorrectMessage(String message) {
        System.out.println(Colors.GREEN + message + Colors.COLOR_RESET);
    }
    @Override
    public void showErrorMessage(String message) {
        System.out.println(Colors.RED + message + Colors.COLOR_RESET);
    }

    @Override
    public void showPlank() {

    }

    @Override
    public void showBookshelf() {

    }

    @Override
    public void showPersonalGoal() {

    }

    @Override
    public void showCommonGoal() {

    }

    @Override
    public ArrayList<CellPlank> drag() {
        return null;
    }

    @Override
    public int drop() {
        return 0;
    }

    @Override
    public void showEndGame() {

    }
}
