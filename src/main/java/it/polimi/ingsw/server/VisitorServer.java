package it.polimi.ingsw.server;

import org.json.simple.JSONObject;

public interface VisitorServer {
    void visitMessageNewGame(MessageStartGameServer m);
    void visitMessageAddPlayer(MessageEnterInGame m);
    void visitMessageDragAndDrop(MessageDragAndDropServer m);
}
