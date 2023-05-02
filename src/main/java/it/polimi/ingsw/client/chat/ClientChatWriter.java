package it.polimi.ingsw.client.chat;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientChatWriter{

    private final int PORT = 4502;
    private final String ipServer= "localhost";
    private String playerName;

    private Socket socket;

    private PrintWriter out;

    private boolean connected;

    public ClientChatWriter() {
        connected = false;
    }

    public void Connect() throws IOException {
        socket= new Socket(ipServer, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        connected = true;
    }

    public void sendMessage(String message){
        try{
            JSONObject obj= new JSONObject();
            obj.put("namePlayer", playerName);
            obj.put("message", message);
            out.println(obj.toJSONString());
        }catch (Exception e){throw new RuntimeException();}

    }

    public boolean isConnected(){
        return connected;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
