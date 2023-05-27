package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.message.*;

import java.io.IOException;

/**
 * This is the interface for visitor pattern client
 */

public interface VisitorClient {
    void visit(MessageEndGameClient element) throws Exception;
    void visit(MessageNewTurnClient element) throws Exception;
    void visit(MessageOKConnectionClient element) throws IOException;
    void visit(MessageKOConnectionClient element) throws IOException;
    void visit(MessageChat element);
    void visit(MessageDisconnection element);
    void visit(MessageKODedClient element);
}
