package it.polimi.ingsw.server;

import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;

public class MessageEnterInGame implements MessageServer{
    private int idGame;
    private User user;

    private ServerThread serverThread;

    public MessageEnterInGame(ServerThread st, JSONObject obj){
        user = new User(obj.get("name").toString());
        idGame=Integer.parseInt(obj.get("idGame").toString());
        this.serverThread = st;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void accept(VisitorServer v) {
        v.visit(this);
    }

    public ServerThread getServerThread() {
        return serverThread;
    }
}
