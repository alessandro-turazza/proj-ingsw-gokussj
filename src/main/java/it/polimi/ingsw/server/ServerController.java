package it.polimi.ingsw.server;

import it.polimi.ingsw.server.message.*;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.state_game.StateGame;
import org.json.simple.JSONObject;

/*This class controls and handles each message received from the server*/
public class ServerController {
    private ServerThread serverThread;

    public ServerController(ServerThread serverThread){
        this.serverThread = serverThread;
    }

    /*This method receives a jsonObject that contains the message from the client in the following format: command, datas and creates the correct type of the message for the visitor*/
    public MessageServer handleMessage(JSONObject obj) {

        String command = (String) obj.get("command");

        if (command.equals("chat_message"))

            return new MessageChatServer(obj, serverThread);
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

    public JSONObject sendChatMessage(JSONObject object){
        JSONObject obj = new JSONObject();
        obj.put("response","CHAT_MESSAGE");
        obj.put("namePlayer", object.get("namePlayer"));
        obj.put("message", object.get("message"));
        return obj;
    }
}
