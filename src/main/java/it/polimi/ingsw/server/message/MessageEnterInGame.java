package it.polimi.ingsw.server.message;

import it.polimi.ingsw.server.ServerThread;
import it.polimi.ingsw.server.visitor.VisitorServer;
import it.polimi.ingsw.server.model.user.User;
import org.json.simple.JSONObject;

/*Class that represent the message of joining into a game in visitor pattern*/

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
