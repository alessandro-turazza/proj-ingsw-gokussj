package it.polimi.ingsw.server;


import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;

public class MessageStartGameServer implements MessageServer{
    private User user;
    private int numPlayer;

    private int idGame;

    private ServerThread serverThread;

    public MessageStartGameServer(ServerThread serverThread, JSONObject obj) {
        this.serverThread=serverThread;
        user= new User(obj.get("name").toString());
        numPlayer = Integer.parseInt(obj.get("numPlayers").toString());
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

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    @Override
    public void accept(VisitorServer v) {
        v.visit(this);

    }

    public ServerThread getServerThread() {
        return serverThread;
    }
}
