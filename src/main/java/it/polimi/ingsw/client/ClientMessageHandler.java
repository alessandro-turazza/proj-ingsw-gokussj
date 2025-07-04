package it.polimi.ingsw.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.message.*;
import it.polimi.ingsw.server.message.DropStructure;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * This class handles each type of message that a client can receive using the visitor pattern
 */

public class ClientMessageHandler {

    private Client client;

    public ClientMessageHandler(Client client){
        this.client = client;

    }

    /**
     * This method receives a jsonObject that contains the message from the server in the following format:
     * response, datas and creates the correct type of the message for the visitor
     */
    public MessageClient handleMessage(JSONObject obj){
        String response = obj.get("response").toString();
        if (response.equals("DISCONNECTION"))
            return  new MessageDisconnection(obj, client);
        if(response.equals("CHAT_MESSAGE"))
            return new MessageChat(client, obj);
        if(response.equals("OK_CONNECTION"))
            return new MessageOKConnectionClient(client, Integer.parseInt(obj.get("object").toString()));
        if(response.equals("KO_CONNECTION"))
            return new MessageKOConnectionClient(client, obj.get("object").toString());
        if(response.equals("NEW_TURN")){
            return new MessageNewTurnClient(client,obj);}
        if(response.equals("KO_DED"))
            return new MessageKODedClient(client);
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

    public JSONObject sendMessageChat(String message, String playerName){
            JSONObject obj= new JSONObject();
            obj.put("command", "chat_message");
            obj.put("namePlayer", playerName);
            obj.put("message", message);
            return obj;
    }
    public JSONObject sendCloseConnection(){
        JSONObject obj = new JSONObject();
        obj.put("command","close_connection");
        return obj;
    }

}
