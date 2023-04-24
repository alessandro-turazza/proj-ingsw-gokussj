package it.polimi.ingsw.client;

public class MessageKOConnectionClient implements MessageClient {

    public MessageKOConnectionClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
