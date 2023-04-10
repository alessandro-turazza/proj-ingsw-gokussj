package it.polimi.ingsw.client;

public class MessageStartTurnClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
