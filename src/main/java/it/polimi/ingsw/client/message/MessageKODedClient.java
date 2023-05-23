package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageKODedClient implements MessageClient{ // Create to contain the message of a negative response of the server about a move of the plyer
    private Client client;

    public MessageKODedClient(Client client){
        this.client=client;
    }
    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }//call the visitor

    public Client getClient() {
        return client;
    }
}
