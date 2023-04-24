package it.polimi.ingsw.server;

public interface VisitorServer {
    void visit(MessageStartGameServer m);
    void visit(MessageEnterInGame m);
    void visit(MessageDragAndDropServer m);
}
