package it.polimi.ingsw.server.chat;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerChatWriter{

    private int idGame;

    private Socket socket;


    public ServerChatWriter(Socket socket, int idGame) throws IOException {
        this.socket = socket;
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public void sendMessage(JSONObject obj) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(obj.toJSONString());

    }
}
