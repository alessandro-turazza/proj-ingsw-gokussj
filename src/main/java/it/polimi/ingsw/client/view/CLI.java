package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements View{
    @Override
    public JSONObject lobby() {
        JSONObject userDatas = new JSONObject();

        System.out.println("Insert a username");
        Scanner in = new Scanner(System.in);

        userDatas.put("username", in.nextLine());

        System.out.println("Press C to create a new game");
        System.out.println("Press J to join into an existent game");

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        while(choose != 'C' && choose != 'J'){
            System.out.println("Invalid character, retype your chosen");
            choose = Character.toUpperCase(in.nextLine().charAt(0));
        }

        switch (choose){
            case 'C':
                System.out.println("Insert the number of the players in game");
                userDatas.put("type", "create");
                userDatas.put("numPlayers", in.nextInt());
                break;
            case 'J':
                System.out.println("Insert the ID of a game");
                userDatas.put("type", "join");
                userDatas.put("idGame", in.nextInt());
                break;
        }

        return userDatas;
    }

    @Override
    public void showMessage(String message) {

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
