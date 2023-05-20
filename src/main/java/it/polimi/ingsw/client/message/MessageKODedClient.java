package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageKODedClient implements MessageClient{
    private Client client;

    public MessageKODedClient(Client client){
        this.client=client;
    }
    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }

    public Client getClient() {
        return client;
    }
}
