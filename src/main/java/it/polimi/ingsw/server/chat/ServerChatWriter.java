package it.polimi.ingsw.server.chat;

import it.polimi.ingsw.server.ServerSender;

import java.io.IOException;
import java.net.Socket;

public class ServerChatWriter extends Thread{

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

    @Override
    public void run() {
        while (true) {
            //si mette in wait e attende che gli vengano passati i dati del messaggio
            ss.sendMessage(playerName, message);
        }
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
}
