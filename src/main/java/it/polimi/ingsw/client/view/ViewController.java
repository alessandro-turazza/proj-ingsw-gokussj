package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ViewController {
    private View view;
    private Client client;

    private ArrayList<String> actions = new ArrayList<>(Arrays.asList("DRAG","DROP","BOOKSHELF"));

    public ViewController(Client client){
        this.client = client;
    }
    public View getView() {
        return view;
    }
    public ArrayList<String> getActions() {
        return actions;
    }

    public void startViewController(){
        char choose;

        do{
            System.out.println("----------------------------");
            System.out.println("Premi C per usare l'interfaccia CLI");
            System.out.println("Premi G per usare l'interfaccia GUI");

            Scanner in = new Scanner(System.in);
            choose = Character.toUpperCase(in.nextLine().charAt(0));

        }while(choose != 'C' && choose != 'G');

        switch(choose){
            case 'C':
                view = new CLI(client);
                break;
            case 'G':
                //view = new GUI();
                break;
        }

        System.out.println("----------------------------");

    }

    public void setClientDatas(){
        JSONObject userDatas;
        userDatas = view.lobby();
        String nomeClient = userDatas.get("username").toString();

        client.getModel().setMyName(nomeClient);

        if(userDatas.get("type").toString().equals("create")){
            int numPlayers = Integer.parseInt(userDatas.get("numPlayers").toString());
            this.client.getMessager().sendMessage(this.client.getMessager().getMessageHandler().sendCreateGame(numPlayers,nomeClient));
        }else{
            int idGame = Integer.parseInt(userDatas.get("idGame").toString());
            this.client.getMessager().sendMessage(this.client.getMessager().getMessageHandler().sendJoinGame(idGame,nomeClient));
        }
    }

    public void handleAction() throws Exception {
        boolean myTurn = client.getModel().getMyName().equals(client.getModel().getActiveUser());
        String action = "";

        action = view.catchAction(myTurn);
        String[] act = action.split(" ");

        if(action.equals(actions.get(0))){
            //drag
        }else if(action.equals(actions.get(1))){
            //drop
        }else if(act[0].equals(actions.get(2))){
            String username = act[1];
            view.showBookshelf(username);
        }

    }


}
