package it.polimi.ingsw.client;

public class MessageOKConnectionClient implements MessageClient {

    public MessageOKConnectionClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
