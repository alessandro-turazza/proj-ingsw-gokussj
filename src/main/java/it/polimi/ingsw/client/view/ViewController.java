package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class ViewController {
    private View view;
    private Client client;

    public ViewController(Client client){
        this.client = client;
    }
    public View getView() {
        return view;
    }

    public void startViewController(){

        System.out.println("----------------------------");
        System.out.println("Premi C per usare l'interfaccia CLI");
        System.out.println("Premi G per usare l'interfaccia GUI");

        Scanner in = new Scanner(System.in);

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        switch(choose){
            case 'C':
                view = new CLI();
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

}
