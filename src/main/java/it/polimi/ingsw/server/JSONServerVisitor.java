package it.polimi.ingsw.server;

import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;

public class JSONServerVisitor implements VisitorServer{

    @Override
    public void visitMessageNewGame(MessageStartGameServer m, JSONObject obj) {
        m.setUser(new User(obj.get("name").toString()));
        m.setNumPlayer(Integer.parseInt(obj.get("numPlayers").toString()));
    }
    @Override
    public void visitMessageAddPlayer(MessageEnterInGame m, JSONObject obj) {
        m.setUser(new User(obj.get("name").toString()));
        m.setIdGame(Integer.parseInt(obj.get("idGame").toString()));
    }
}
