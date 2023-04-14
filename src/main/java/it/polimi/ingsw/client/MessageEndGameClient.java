package it.polimi.ingsw.client;

public class MessageEndGameClient implements MessageClient {

    public MessageEndGameClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
