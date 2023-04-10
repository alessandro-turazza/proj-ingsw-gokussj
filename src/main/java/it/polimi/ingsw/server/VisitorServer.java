package it.polimi.ingsw.server;

import org.json.simple.JSONObject;

public interface VisitorServer {
    public void visitMessageNewGame(MessageStartGameServer m, JSONObject obj);
    public void visitMessageAddPlayer(MessageEnterInGame m, JSONObject obj);
}
