package it.polimi.ingsw.server;

import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;

public class MessageStartGameServer implements MessageServer{
    private User user;
    private int numPlayer;

    private ServerThread serverThread;

    public MessageStartGameServer(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    @Override
    public void accept(VisitorServer v, JSONObject obj) {
        v.visitMessageNewGame(this, obj);
        Server.insertNewGame(serverThread, user, numPlayer);
    }
}
