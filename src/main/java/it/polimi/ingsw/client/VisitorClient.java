package it.polimi.ingsw.client;

public interface VisitorClient {
    void visit(MessageEndGameClient element);
    void visit(MessageGameStateClient element);
    void visit(MessageNewTurnClient element);
    void visit(MessageOKConnectionClient element);
    void visit(MessageKOConnectionClient element);
}
