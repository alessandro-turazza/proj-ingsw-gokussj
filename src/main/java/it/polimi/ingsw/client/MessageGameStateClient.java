package it.polimi.ingsw.client;

public class MessageGameStateClient implements MessageClient {

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);

    }
}
