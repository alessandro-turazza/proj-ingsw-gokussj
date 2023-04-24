package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageGameStateClient implements MessageClient {

    public MessageGameStateClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);

    }
}
