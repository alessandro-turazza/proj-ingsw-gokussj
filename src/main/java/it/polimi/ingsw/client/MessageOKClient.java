package it.polimi.ingsw.client;

public class MessageOKClient implements MessageClient {

    public MessageOKClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
