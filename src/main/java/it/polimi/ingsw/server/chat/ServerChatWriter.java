package it.polimi.ingsw.server.chat;

import it.polimi.ingsw.server.ServerSender;

import java.io.IOException;
import java.net.Socket;

public class ServerChatWriter{

    private int idGame;

    private Socket socket;

    private ServerSender ss;

    private String playerName;

    private String message;



    public ServerChatWriter(Socket socket, int idGame) throws IOException {
        this.socket = socket;
        ss = new ServerSender(socket);
        this.idGame=idGame;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdGame() {
        return idGame;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ServerSender getSs() {
        return ss;
    }
}
