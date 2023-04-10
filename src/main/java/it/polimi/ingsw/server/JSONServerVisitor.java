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

    public  void visitMessageDragAndDrop(MessageDragAndDropServer m, JSONObject obj){
        m.setColumn((Integer) obj.get("column"));
        m.setX_coordinate((Integer) obj.get("x_coordinate"));
        m.setY_coordinate((Integer) obj.get("y_coordinate"));
    }
}
