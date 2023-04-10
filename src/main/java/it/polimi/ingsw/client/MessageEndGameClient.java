package it.polimi.ingsw.client;

public class MessageEndGameClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor, Object obj) {
        visitor.visit(this, obj);
    }
}
