package it.polimi.ingsw.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.message.*;
import it.polimi.ingsw.server.message.DropStructure;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ClientMessageHandler {

    private Client client;

    public ClientMessageHandler(Client client){
        this.client = client;

    }

    public MessageClient handleMessage(JSONObject obj){
        String response = obj.get("response").toString();

        if(response.equals("OK_CONNECTION"))
            return new MessageOKConnectionClient(client, Integer.parseInt(obj.get("object").toString()));
        if(response.equals("KO_CONNECTION"))
            return new MessageKOConnectionClient(client, obj.get("object").toString());
        if(response.equals("NEW_TURN")){
            System.out.println("messaggio arrivato");
            return new MessageNewTurnClient(client,obj);}
        if(response.equals("OK_DED"))
            return new MessageOKDedClient();
        if(response.equals("KO_DED"))
            return new MessageKODedClient();
        if(response.equals("END_GAME"))
            return new MessageEndGameClient(client,obj);

        return null;
    }

    public JSONObject sendCreateGame(int numPlayers, String name){
        JSONObject obj = new JSONObject();
        obj.put("command","new_game");
        obj.put("name",name);
        obj.put("numPlayers",  numPlayers);
        return obj;
    }

    public JSONObject sendJoinGame(int idGame, String name){
        JSONObject obj = new JSONObject();
        obj.put("command","enter_in_game");
        obj.put("name",name);
        obj.put("idGame",  idGame);
        return obj;
    }

    public JSONObject sendDragAndDrop(ArrayList<CellPlank> cellPlanks, int column){
        JSONObject obj = new JSONObject();
        obj.put("command", "drag_and_drop");

        DropStructure ds = new DropStructure();

        for(CellPlank c: cellPlanks){
            ds.getRows().add(c.getRow());
            ds.getColumns().add(c.getColumn());
        }

        ds.setColumn(column);

        obj.put("data", new Gson().toJson(ds));

        return obj;
    }


}
