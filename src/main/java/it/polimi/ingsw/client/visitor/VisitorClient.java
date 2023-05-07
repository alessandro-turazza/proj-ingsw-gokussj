package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.message.*;

public interface VisitorClient {
    void visit(MessageEndGameClient element) throws Exception;
    void visit(MessageGameStateClient element);
    void visit(MessageNewTurnClient element) throws Exception;
    void visit(MessageOKConnectionClient element);
    void visit(MessageKOConnectionClient element);
    void visit(MessageChat element);
    void visit(MessageDisconnection element);
}
