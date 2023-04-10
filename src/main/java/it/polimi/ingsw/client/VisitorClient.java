package it.polimi.ingsw.client;

public interface VisitorClient {
    void visit(MessageEndGameClient element);
    void visit(MessageGameStateClient element);
    void visit(MessageStartTurnClient element);
    void visit(MessageVerifyDragClient element);
    void visit(MessageVerifyDropClient element);

}
