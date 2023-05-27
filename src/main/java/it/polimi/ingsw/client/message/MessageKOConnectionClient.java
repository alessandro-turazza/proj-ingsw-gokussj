package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;

import java.io.IOException;

/**
 * This class Create to contain the message of a negative response of the server about a connection attempt
 */
public class MessageKOConnectionClient implements MessageClient { // Create to contain the message of a negative response of the server about a connection attempt
    private Client client;
    private String object;
    public MessageKOConnectionClient(Client client, String object) {
        this.client = client;
        this.object = object;
    }

    public Client getClient(){
        return client;
    }

    public String getObject() {
        return object;
    }

    @Override
    public void accept(VisitorClient visitor) throws IOException {
        visitor.visit(this);
    }
}
