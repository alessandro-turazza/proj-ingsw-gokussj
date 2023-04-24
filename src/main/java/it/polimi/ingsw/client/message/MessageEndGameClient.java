package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageEndGameClient implements MessageClient {

    public MessageEndGameClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
