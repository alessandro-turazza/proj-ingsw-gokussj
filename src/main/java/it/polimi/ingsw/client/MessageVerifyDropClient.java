package it.polimi.ingsw.client;

public class MessageVerifyDropClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
