package it.polimi.ingsw.client;

import it.polimi.ingsw.client.message.*;
import org.json.simple.JSONObject;

public class ClientMessageHandler {

    private Client client;

    private int dragXCoordinate;

    private int dragYCoordinate;

    private int dropColumn;

    public ClientMessageHandler(Client client){
        this.client = client;

    }

    public MessageClient handleMessage(JSONObject obj){
        String response = obj.get("response").toString();

        if(response.equals("OK_CONNECTION"))
            return new MessageOKConnectionClient(client, Integer.parseInt(obj.get("object").toString()));
        if(response.equals("KO_CONNECTION"))
            return new MessageKOConnectionClient(client, obj.get("object").toString());
        if(response.equals("NEW_TURN"))
            return new MessageNewTurnClient(client,obj);
        if(response.equals("OK_DED"))
            return new MessageOKDedClient();
        if(response.equals("KO_DED"))
            return new MessageKODedClient();
        if(response.equals("END_GAME"))
            return new MessageEndGameClient();

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

    public JSONObject sendDragAndDrop(){
        JSONObject obj = new JSONObject();
        obj.put("command", "drag_and_drop");
        obj.put("x_cordinate", dragXCoordinate);
        obj.put("y_coordinate", dragYCoordinate);
        obj.put("column", dropColumn);
        return obj;
    }

}
