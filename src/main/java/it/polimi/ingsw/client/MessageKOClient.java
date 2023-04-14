package it.polimi.ingsw.client;

public class MessageKOClient implements MessageClient {

    public MessageKOClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
