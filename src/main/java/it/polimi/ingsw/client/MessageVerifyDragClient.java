package it.polimi.ingsw.client;

public class MessageVerifyDragClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
