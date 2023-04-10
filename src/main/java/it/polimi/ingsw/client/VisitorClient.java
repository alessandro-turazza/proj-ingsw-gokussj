package it.polimi.ingsw.client;

public interface VisitorClient {
    void visit(MessageEndGameClient element, Object obj);
    void visit(MessageGameStateClient element, Object obj);
    void visit(MessageNewTurnClient element, Object obj);
    void visit(MessageOKClient element, Object obj);
    void visit(MessageKOClient element, Object obj);

}
