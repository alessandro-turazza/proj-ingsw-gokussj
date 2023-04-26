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
        System.out.println("Press C to start CLI interface");
        System.out.println("Press G to start GUI interface");

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

    }

    public JSONObject setClientDatas(){
        JSONObject userDatas;
        userDatas = view.lobby();
        JSONObject messOut;
        String nomeClient = userDatas.get("username").toString();

        client.setUsername(nomeClient);

        if(userDatas.get("type").toString().equals("create")){
            int numPlayers = Integer.parseInt(userDatas.get("numPlayers").toString());
            messOut = this.client.getController().sendCreateGame(numPlayers,nomeClient);
        }else{
            int idGame = Integer.parseInt(userDatas.get("idGame").toString());
            messOut = this.client.getController().sendJoinGame(idGame, nomeClient);
        }

        return messOut;
    }
}
