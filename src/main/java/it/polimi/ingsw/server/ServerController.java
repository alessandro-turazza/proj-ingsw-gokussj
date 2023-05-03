package it.polimi.ingsw.server;

import it.polimi.ingsw.server.message.*;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.state_game.StateGame;
import org.json.simple.JSONObject;

import java.io.IOException;

public class ServerController {
    private ServerThread serverThread;

    public ServerController(ServerThread serverThread) throws IOException {
        this.serverThread = serverThread;
    }

    public MessageServer handleMessage(JSONObject obj) {

        String command = (String) obj.get("command");

        if (command.equals("new_game"))

            return new MessageStartGameServer(serverThread, obj);

        if (command.equals("enter_in_game"))

            return new MessageEnterInGame(serverThread, obj);

        if(command.equals("drag_and_drop"))
            return new MessageDragAndDropServer(serverThread, obj);

        if(command.equals("close_connection"))
            return new MessageCloseConnection(serverThread);


        return null;
    }

    public JSONObject sendOkConnection(String object){
        JSONObject obj = new JSONObject();
        obj.put("response", "OK_CONNECTION");
        obj.put("object", object);
        return obj;
    }

    public JSONObject sendKoConnection(String object){
        JSONObject obj = new JSONObject();
        obj.put("response", "KO_CONNECTION");
        obj.put("object", object);
        return obj;
    }

    public JSONObject sendOkDED(){
        JSONObject obj = new JSONObject();
        obj.put("response", "OK_DED");
        return obj;
    }

    public JSONObject sendKoDED(){
        JSONObject obj = new JSONObject();
        obj.put("response", "KO_DED");
        return obj;
    }


    public JSONObject sendStateGame(GameManager gm){
        JSONObject obj = new JSONObject();
        StateGame stateGame=new StateGame(gm);
        obj.put("response","NEW_TURN");
        obj.put("state_game", stateGame.messageStateGame());
        System.out.println(stateGame.messageStateGame());

        return obj;
    }

    public JSONObject sendEndOfGame(GameManager gm){
        JSONObject obj = new JSONObject();
        StateGame stateGame=new StateGame(gm);
        obj.put("response","END_GAME");
        obj.put("state_game", stateGame.messageStateGame());
        return obj;
    }

}
