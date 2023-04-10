package it.polimi.ingsw.client;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender {

    private PrintWriter out;

    private int dragXCoordinate;

    private int dragYCoordinate;

    private int dropColumn;

    public ClientSender(Socket s)throws IOException {
        this.out = new PrintWriter(s.getOutputStream(), true);

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
    }

}
