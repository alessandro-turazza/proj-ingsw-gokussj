package it.polimi.ingsw.server.visitor;

import it.polimi.ingsw.server.message.MessageChatServer;
import it.polimi.ingsw.server.message.MessageCloseConnection;
import it.polimi.ingsw.server.message.MessageDragAndDropServer;
import it.polimi.ingsw.server.message.MessageEnterInGame;
import it.polimi.ingsw.server.message.MessageStartGameServer;
/**
 * This is the interface for the visitor messages
 * */
public interface VisitorServer {
    void visit(MessageStartGameServer m);
    void visit(MessageEnterInGame m);
    void visit(MessageDragAndDropServer m);
    void visit(MessageChatServer m);
    void visit(MessageCloseConnection m);
}
