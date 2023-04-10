package it.polimi.ingsw.client;

public class MessageKOClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor, Object obj) {
        visitor.visit(this, obj);
    }
}
