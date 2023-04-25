package it.polimi.ingsw.client.chat;

import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientChatWriter{

    private final int PORT = 4502;
    private final String ipServer= "localhost";

    private String playerName;

    public ClientChatWriter(String playerName) {
        this.playerName = playerName;
    }

    public void sendMessage(String message){
        try{
            Socket socket = new Socket(ipServer, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            JSONObject obj= new JSONObject();
            obj.put("namePlayer", playerName);
            obj.put("message", message);
            out.println(obj.toJSONString());
        }catch (Exception e){throw new RuntimeException();}

    }

}
