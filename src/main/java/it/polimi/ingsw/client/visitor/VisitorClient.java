package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.message.*;

import java.io.IOException;

public interface VisitorClient {
    void visit(MessageEndGameClient element) throws Exception;
    void visit(MessageGameStateClient element);
    void visit(MessageNewTurnClient element) throws Exception;
    void visit(MessageOKConnectionClient element) throws IOException;
    void visit(MessageKOConnectionClient element) throws IOException;
    void visit(MessageChat element);
    void visit(MessageDisconnection element);
}
