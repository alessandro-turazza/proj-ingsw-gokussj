package it.polimi.ingsw.client;

public class MessageGameStateClient implements MessageClient {

    public MessageGameStateClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);

    }
}
