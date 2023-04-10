package it.polimi.ingsw.server;

import org.json.simple.JSONObject;

public interface VisitorServer {
    void visitMessageNewGame(MessageStartGameServer m, JSONObject obj);
    void visitMessageAddPlayer(MessageEnterInGame m, JSONObject obj);
    void visitMessageDragAndDrop(MessageDragAndDropServer m, JSONObject obj);
}
