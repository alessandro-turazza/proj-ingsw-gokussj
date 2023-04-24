package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageOKConnectionClient implements MessageClient {

    public MessageOKConnectionClient() {
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
