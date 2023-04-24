package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageKOConnectionClient implements MessageClient {

    public MessageKOConnectionClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
