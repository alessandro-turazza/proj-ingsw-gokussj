package it.polimi.ingsw.client;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class ClientSender {

    private PrintWriter out;

    private int dragXCoordinate;

    private int dragYCoordinate;

    private int dropColumn;

    public ClientSender(PrintWriter out){
        this.out = out;

    }

    public void sendCreateGame(int numPlayers, String name){
        JSONObject obj = new JSONObject();
        obj.put("command","new_game");
        obj.put("name",name);
        obj.put("numPlayers",  numPlayers);
        out.println(obj.toJSONString());
    }

    public void sendJoinGame(int idGame, String name){
        JSONObject obj = new JSONObject();
        obj.put("command","enter_in_game");
        obj.put("name",name);
        obj.put("idGame",  idGame);
        out.println(obj.toJSONString());
    }

    public void sendDragAndDrop(){
        JSONObject obj = new JSONObject();
        obj.put("command", "drag_and_drop");
        obj.put("x_cordinate", dragXCoordinate);
        obj.put("y_coordinate", dragYCoordinate);
        obj.put("column", dropColumn);
        out.println(obj.toJSONString());
    }

    public void sendMessage(String name, String message){
        JSONObject obj = new JSONObject();
        obj.put("player_name", name);
        obj.put("message", message);
        out.println(obj.toJSONString());
    }


}
